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

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;
  
  private static GameBoard e;
  
  private static Player p1;
  
  private static Player p2;

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

    /**
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     */
    app.get("/newgame", ctx -> {
        ctx.redirect("/tictactoe.html");
      });
      
      app.post("/startgame", ctx -> {
        String requestBody = ctx.body();
              
        e = new GameBoard();
        char type = requestBody.charAt(requestBody.indexOf("type=") + "type=".length());
        p1 = new Player();
        p1.changeTheId(1);
        p1.changePlayerType(type);
        
        e.makeIt1st(p1);
        
        Gson gson = new Gson();
        ctx.result(gson.toJson(e));
      });
      
      app.get("/joingame", ctx -> {
        ctx.redirect("/tictactoe.html?p=2");
        
        char playerType = p1.retrievePlayerType() == 'O' ? 'X' : 'O';
        
        p2 = new Player();
        p2.changeTheId(2);
        p2.changePlayerType(playerType);
        
        e.makeIt2nd(p2);
        e.asdgasg(true);
        
        Gson gson = new Gson();
        sendGameBoardToAllPlayers(gson.toJson(e));
      });
      
      app.post("/move/:playerId", ctx -> {
        String requestBody = ctx.body();
        String playerId = ctx.pathParam("playerId");
        Player player;
        Move move = new Move();
        if (playerId.equals("1")) {
          player = p1;
        } else {
          player = p1;
        }
        int moveX = Character.getNumericValue(
                requestBody.charAt(requestBody.indexOf("x=") + "x=".length()));
        int moveY = Character.getNumericValue(
                requestBody.charAt(requestBody.indexOf("y=") + "y=".length()));
        move.whoBePlaying(player);
        move.whatWasTheMoveX(moveX);
        move.whatWasTheMoveY(moveY);
        
        Message message = new Message();
        message.makeItValid(e.mmvvvv113(move) == 1);
        message.changeStatus(100);
        if (e.mmvvvv113(move) == 0) {
          message.setMessage("Invalid move!");
        } else {
          if (e.ssmmmm113(move) == 1) {
            if (e.didanyonewin() == 1) {
              message.setMessage("The game is a draw!");
            } else {
              int winner = e.gwwwggww();
              message.setMessage(String.format("Player %d won!", winner));
            }
          } else {
            message.setMessage("");
          }
        }
        
        Gson gson = new Gson();
        ctx.result(gson.toJson(message));
        sendGameBoardToAllPlayers(gson.toJson(e));
      });
      // TODO complete

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
