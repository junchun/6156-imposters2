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

class PlayGame {

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;

  /**
   * Main method of the application.
   * 
   * @param args Command line arguments
   */
  public static void main(final String[] args) {

    app = Javalin.create(config -> {
      config.addStaticFiles("/public"); // Enable static files
    }).start(PORT_NUMBER);

    // Test Echo Server
    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });

    GameBoard gameBoard = new GameBoard();

    /*
     * New Game This EndPoint will return a new page for Player 1. Redirect the user to
     * tiatactoe.html page.
     */
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });

    /*
     * Start Game This EndPoint will initialize the game board and setup Player 1. The first player
     * will always start the game.
     */
    app.post("/startgame", ctx -> {
      Player p1 = new Player();
      p1.setId(1);
      p1.setType(ctx.formParam("type").charAt(0));
      gameBoard.setP1(p1);
      gameBoard.initializeBoardState();
      Gson gson = new Gson();
      String gameBoardJson = gson.toJson(gameBoard);
      ctx.result(gameBoardJson); // Return game board in JSON
    });

    /*
     * Join Game This End Point will Initialize Player 2 and add the player to existing game board.
     * After Player 2 has joined, the game must start, and all players' UI must be updated with the
     * latest game board state.
     */
    app.get("/joingame", ctx -> {
      Player p2 = new Player();
      p2.setId(2);
      if (gameBoard.getP1().getType() == 'X') {
        p2.setType('O');
      } else {
        p2.setType('X');
      }
      gameBoard.setP2(p2);
      gameBoard.setGameStarted(true);
      Gson gson = new Gson();
      String gameBoardJson = gson.toJson(gameBoard);
      sendGameBoardToAllPlayers(gameBoardJson); // Return game board in
      // JSON
      ctx.redirect("/tictactoe.html?p=2");
    });

    /*
     * Move Update the game board if the move is valid. Otherwise it returns an error message.
     */
    app.post("/move/:playerId", ctx -> {
      // Get id of the player making a move
      Move move = new Move();
      move.setMoveX(Integer.parseInt(ctx.formParam("x"))); // Board row
      // number
      move.setMoveY(Integer.parseInt(ctx.formParam("y"))); // Board column
      // number
      int playerId = Integer.parseInt(ctx.pathParam("playerId"));
      move.setPlayer(gameBoard.getPlayer(playerId));
      Message message = new Message();
      if (gameBoard.checkMoveValidity(move, message) && gameBoard.makeMove(move)) {
        gameBoard.setTurn(gameBoard.getTurn() % 2 + 1);
      }
      Gson messageGson = new Gson();
      String messageJson = messageGson.toJson(message);
      ctx.result(messageJson);
      Gson gameBoardGson = new Gson();
      String gameBoardJson = gameBoardGson.toJson(gameBoard);
      sendGameBoardToAllPlayers(gameBoardJson);
    });

    // Web sockets - DO NOT DELETE or CHANGE
    app.ws("/gameboard", new UiWebSocket());
  }

  /**
   * Send message to all players.
   * 
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
