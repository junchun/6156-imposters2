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

  private static GameBoard gameboard;

  /**
   * Main method of the application.
   * 
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

    // Endpoint that redirects to initial UI, allowing user to start a game.
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });

    // Endpoint to initialize player 1 and gameboard. 
    app.post("/startgame", ctx -> {
      String requestBody = ctx.body();
      char playerType = requestBody.charAt(requestBody.indexOf("=") + 1);
      gameboard = new GameBoard();

      Player player1 = new Player();
      player1.setId(1);
      player1.setType(playerType);
      
      gameboard.setP1(player1);
      ctx.result(getGameboardJson());
    });

    // Endpoint to initialize player 2, add them to game, and start the game.
    app.get("/joingame", ctx -> {
      Player p2 = new Player();
      p2.setId(2);
      if (gameboard.getP1().getType() == 'O') {
        p2.setType('X');
      } else {
        p2.setType('O');
      }
      
      gameboard.setP2(p2);
      gameboard.setGameStarted(true);
      
      sendGameBoardToAllPlayers(getGameboardJson());
      ctx.redirect("/tictactoe.html?p=2");
    });

    // Endpoint to execute a move if valid and update gameboard state accordingly.
    app.post("/move/:playerId", ctx -> {
      String playerId = ctx.pathParam("playerId");
      Player player;
      if (playerId.indexOf('1') != -1) {
        player = gameboard.getP1();
      } else {
        player = gameboard.getP2();
      }
      
      String requestBody = ctx.body();
      int x = Integer.parseInt("" + requestBody.charAt(requestBody.indexOf("=") + 1));
      int y = Integer.parseInt("" + requestBody.charAt(requestBody.lastIndexOf("=") + 1));
      Move move = new Move(player, x, y);
      
      Message response;
      if (gameboard.isValidMove(move)) {
        gameboard.makeMove(move);
        response = new Message(true, 100, "");
      } else {
        response = new Message(false, 400, "This is not a valid move.");
      }
      
      Gson gsonLib = new Gson();
      ctx.result(gsonLib.toJson(response));
      sendGameBoardToAllPlayers(getGameboardJson());
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
        System.out.println("The following error occured: " + e.getMessage());
      }
    }
  }

  /**
   * Returns JSON string of current gameboard.
   * 
   * @return gameboard represented as JSON string.
   */
  private static String getGameboardJson() {
    Gson gsonLib = new Gson();
    return gsonLib.toJson(gameboard);
  }

  public static void stop() {
    app.stop();
  }
}
