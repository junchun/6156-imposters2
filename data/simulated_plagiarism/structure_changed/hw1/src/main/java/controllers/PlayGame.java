package controllers;

import com.google.gson.Gson;
import io.javalin.Javalin;
import java.io.IOException;
import java.util.Queue;
import models.GameBoard;
import models.Message;
import models.Move;
import models.Player;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PlayGame {
	
  private static Logger logger = LoggerFactory.getLogger(PlayGame.class);

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;
  
  private static Player player1;
  
  private static Player player2;
  
  private static GameBoard gameBoard;

  /** Main method of the application.
   * @param args Command line arguments
   */
  public static void main(final String[] args) {

    app = Javalin.create(config -> {
      config.addStaticFiles("/public");
    }).start(PORT_NUMBER);

    // Test Echo Server
    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });
    
    app.post("/startgame", ctx -> {
        String requestBody = ctx.body();
              
        gameBoard = new GameBoard();
        char type = requestBody.charAt(requestBody.indexOf("type=") + "type=".length());
        player1 = new Player();
        player1.setId(1);
        player1.setType(type);
        
        gameBoard.setP1(player1);
        
        Gson gson = new Gson();
        ctx.result(gson.toJson(gameBoard));
      });
    
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });
    
    app.post("/move/:playerId", ctx -> {
      String requestBody = ctx.body();
      String playerId = ctx.pathParam("playerId");
      Player player;
      Move move = new Move();
      if (playerId.equals("1")) {
        player = player1;
      } else {
        player = player2;
      }
      int moveX = Character.getNumericValue(
              requestBody.charAt(requestBody.indexOf("x=") + "x=".length()));
      int moveY = Character.getNumericValue(
              requestBody.charAt(requestBody.indexOf("y=") + "y=".length()));
      move.setPlayer(player);
      move.setMoveX(moveX);
      move.setMoveY(moveY);
      
      Message message = new Message();
      message.setMoveValidity(gameBoard.isMoveValid(move));
      message.setCode(100);
      if (!gameBoard.isMoveValid(move)) {
        message.setMessage("Invalid move!");
      } else {
        if (gameBoard.setMove(move)) {
          if (gameBoard.isDraw()) {
            message.setMessage("The game is a draw!");
          } else {
            int winner = gameBoard.getWinner();
            message.setMessage(String.format("Player %d won!", winner));
          }
        } else {
          message.setMessage("");
        }
      }
      
      Gson gson = new Gson();
      ctx.result(gson.toJson(message));
      sendGameBoardToAllPlayers(gson.toJson(gameBoard));
    });
    
    app.get("/joingame", ctx -> {
        ctx.redirect("/tictactoe.html?p=2");
        
        char playerType = player1.getType() == 'O' ? 'X' : 'O';
        
        player2 = new Player();
        player2.setId(2);
        player2.setType(playerType);
        
        gameBoard.setP2(player2);
        gameBoard.setGameStarted(true);
        
        Gson gson = new Gson();
        sendGameBoardToAllPlayers(gson.toJson(gameBoard));
      });

    // Web sockets - DO NOT DELETE or CHANGE
    app.ws("/gameboard", new UiWebSocket());
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
        logger.error("An IO exception occurred!");
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
