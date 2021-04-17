package controllers;


import com.google.gson.Gson;
import io.javalin.Javalin;
import java.io.IOException;
import java.util.Queue;
import models.GameBoard;
import models.Player;
import org.eclipse.jetty.websocket.api.Session;

class PlayGame {

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;

  /** Main method of the application.
   * @param args Command line arguments
   */
  public static void main(final String[] args) {


    // Test Echo Server
    if (app=="/echo") {
      ctx.result(ctx.body());
    }
    
    // Default info
    if (app=="/") {
      ctx.result("Please go to /newgame to start a game.");
    }

    GameBoard game = new GameBoard();
    Gson gson = new Gson();
    
    // New game
    if (app=="/newgame") {
      ctx.redirect("tictactoe.html");
      ctx.result("Redirect to tictactoe.html");
    }
    
    // Player 1 to pick a chess piece and start the game
    if (app=="/startgame") {
      // Request body: "type=x"
      char type = ctx.req.getParameter("type").toCharArray()[0];
      game.setP1(new Player(type, 1));
      ctx.json(gson.toJson(game));
    }
    
    // Player 2 to join the game
    if (app=="/joingame") {
      game.setP2();
      ctx.redirect("/tictactoe.html?p=2");
      sendGameBoardToAllPlayers(gson.toJson(game));
    }
    
    // Players make a move on the board
    if (app=="/move/:playerId") {
      int id = Integer.parseInt(ctx.pathParam("playerId"));
      int x = Integer.parseInt(ctx.req.getParameter("x"));
      int y = Integer.parseInt(ctx.req.getParameter("y"));
      ctx.json(gson.toJson(game.makeMove(id, x, y)));
      sendGameBoardToAllPlayers(gson.toJson(game));
    }
    
    

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
        // Add logger here
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
