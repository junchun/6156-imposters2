package controllers;

import com.google.gson.Gson;
import io.javalin.Javalin;
import java.io.IOException;
import java.util.Queue;
import models.GameBoard;
import models.Message;
import models.Player;
import org.eclipse.jetty.websocket.api.Session;

class PlayGame {

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;
  
  private static GameBoard gameBoard;
  
  /** Main method of the application.
   * @param args Command line arguments
   */
  public static void main(final String[] args) {
    final Gson gson = new Gson();
    
    app = Javalin.create(config -> {
      config.addStaticFiles("/public");
    }).start(PORT_NUMBER);

    // Test Echo Server
    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });
    
    // GET /newgame
    // Redirect user to UI page
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });
    
    // POST /startgame
    // Initialize game and returns player info and board info as a JSON string to user
    app.post("/startgame", ctx -> {
      String[] temp = ctx.body().split("=");
      char playerType = temp[1].charAt(0); // X or O, the player's character
      
      // Initialize game board　and player chess type and id
      gameBoard = new GameBoard();
      gameBoard.setP1(new Player());
      gameBoard.getP1().setType(playerType);
      gameBoard.getP1().setId(1);
      System.out.println(gson.toJson(gameBoard));
      ctx.result(gson.toJson(gameBoard));
    });
    
    // GET /joingame
    // Set up player 2 info and redirect player 2 to game page
    app.get("/joingame", ctx -> {
      // set player 2 chess type based on player 1 chess type
      char p1Type = gameBoard.getP1().getType();
      gameBoard.setP2(new Player());
      gameBoard.getP2().setId(2);
      if (p1Type == 'X') {
        gameBoard.getP2().setType('O');
      } else {
        gameBoard.getP2().setType('X');
      }
      gameBoard.setGameStarted(true);
      ctx.redirect("/tictactoe.html?p=2");
      sendGameBoardToAllPlayers(gson.toJson(gameBoard));
    });
    
    // POST /move/{userId}
    // Takes user move in request body and update the board accordingly
    // also checks if any player has won/draw the game
    app.post("/move/:id", ctx -> {
      // Template for invalidMessage
      Message invalidMessage = new Message();
      invalidMessage.setCode(403); // code for bad request
      invalidMessage.setMoveValidity(false);
      
      // if the player attempt to place chess before the game starts
      if (!gameBoard.isGameStarted()) {
        invalidMessage.setMessage("Please wait for the other player");
        ctx.result(gson.toJson(invalidMessage));
        return;
      }
      
      // parse player id from request body
      int playerId = Integer.parseInt(ctx.pathParam("id"));
      // if it is not player's turn yet
      if (gameBoard.getTurn() != playerId) {
        invalidMessage.setMessage("It is not your turn yet!");
        ctx.result(gson.toJson(invalidMessage));
        return;
      }
      
      // parse location info from request body
      String[] temp = ctx.body().split("&");
      String[] tempX = temp[0].split("=");
      String[] tempY = temp[1].split("=");
      int x = Integer.parseInt(tempX[1]);
      int y = Integer.parseInt(tempY[1]);
      
      // if the location is already occupied
      if (gameBoard.getBoardState()[x][y] != '\0') {
        invalidMessage.setMessage("Invalid place to put chess!");
        ctx.result(gson.toJson(invalidMessage));
        return;
      }
      
      // get the player chess type and place chess
      char playerType;
      if (playerId == gameBoard.getP1().getId()) {
        playerType = gameBoard.getP1().getType();
      } else {
        playerType = gameBoard.getP2().getType();
      }
      
      // the move is valid, update the board and generate message
      gameBoard.setBoardState(x, y, playerType);
      gameBoard.setTurn(playerId == 1 ? 2 : 1);
      // check if the board is a win or a draw
      if (checkWin(gameBoard.getBoardState(), playerType, x, y)) {
        gameBoard.setWinner(playerId);
      } else if (checkFull(gameBoard.getBoardState())) {
        gameBoard.setDraw(true);
      }
      // construct valid move message
      Message validMessage = new Message();
      validMessage.setCode(100); // code for OK
      validMessage.setMoveValidity(true);
      validMessage.setMessage("Nice Move");
      ctx.result(gson.toJson(validMessage));
      sendGameBoardToAllPlayers(gson.toJson(gameBoard));
    });
    
    // Web sockets - DO NOT DELETE or CHANGE
    app.ws("/gameboard", new UiWebSocket());
  }
  
  /** Check if the board is eligible for a win for certain player.
   * @param board The 3x3 board
   * @param type The chess type to check win for
   * @param x x-coord of the previous play
   * @param y y-coord of the previous play
   * @return boolean whether the board is a win or not
   */
  private static boolean checkWin(char[][] board, char type, int x, int y) {
    int counterX = 0;
    int counterY = 0;
    // check vertical and horizontal
    for (int i = 0; i < 3; ++i) {
      counterY += board[x][i] == type ? 1 : 0;
      counterX += board[i][y] == type ? 1 : 0;
    }
    if (counterX == 3 || counterY == 3) {
      return true;
    }
    // check diagonal
    if (x == y || x == 2 - y) {
      counterX = 0;
      counterY = 0;
      for (int i = 0; i < 3; ++i) {
        counterX += board[i][i] == type ? 1 : 0;
        counterY += board[i][2 - i] == type ? 1 : 0;
      }
      if (counterX == 3 || counterY == 3) {
        return true;
      }
    }
    return false;
  }
  
  /** Check if the board is full.
   * @param board The 3x3 board
   * @return boolean whether the board is full
   */
  private static boolean checkFull(char[][] board) {
    int count = 0;
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        if (board[i][j] != '\0') {
          count += 1;
        }
      }
    }
    return count == 9;
  }
  
  /** Send message to all players.
   * @param gameBoardJson Gameboard JSON
   * @throws IOException Websocket message send IO Exception
   */
  private static void sendGameBoardToAllPlayers(final String gameBoardJson) {
    Queue<Session> sessions = UiWebSocket.getSessions();
    for (Session sessionPlayer : sessions) {
      try {
        sessionPlayer.getRemote().sendString(gameBoardJson);
      } catch (IOException e) {
        // Add logger here
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
