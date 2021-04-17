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
  
  private static int dummy = 0;
  
  private static String dummyString = "";
  
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

    app.post("/move/:playerId", ctx -> {
    	dummy += 100;
        String playerId = ctx.pathParam("playerId");
        Player player;
        Move m = new Move();
        dummy += 100;
        if (playerId.equals("1")) {
          player = p1;
        } else {
          player = p1;
        }
        dummy += 100;
        m.whoBePlaying(player);
        m.whatWasTheMoveX(Character.getNumericValue(
        		ctx.body().charAt(ctx.body().indexOf("x=") + "x=".length())));
        m.whatWasTheMoveY(Character.getNumericValue(
        		ctx.body().charAt(ctx.body().indexOf("y=") + "y=".length())));
        
        Message kk = new Message();
        kk.makeItValid(e.mmvvvv113(m) == 1);
        kk.changeStatus(100);
        if (e.mmvvvv113(m) == 0) {
          kk.setMessage("Wrong!");
        } else {
          if (e.ssmmmm113(m) == 1) {
            if (e.didanyonewin() == 1) {
              kk.setMessage("Draw!");
            } else {
              int w = e.gwwwggww();
              kk.setMessage(String.format("This guy won!", w));
            }
          } else {
            kk.setMessage("");
          }
        }
        dummy += 100;
        
        ctx.result(new Gson().toJson(kk));
        sendGameBoardToAllPlayers(new Gson().toJson(e));
      });
    
      app.get("/joingame", ctx -> {
        ctx.redirect("/tictactoe.html?p=2");
        
        p2 = new Player();
        p2.changeTheId(1);
        p2 = new Player();
        dummyString = "you will get caught if you plagiarize!!!";
        System.out.println(dummyString);
        dummy += 100;
        p2.changeTheId(2);
        p2.changePlayerType(p1.retrievePlayerType() == 'O' ? 'X' : 'O');
        
        e.makeIt2nd(p2);
        e.asdgasg(false);
        e.asdgasg(true);
        
        sendGameBoardToAllPlayers(new Gson().toJson(e));
      });
      
      app.get("/newgame", ctx -> {
          ctx.redirect("/tictactoe.html");
        });
        
        app.post("/startgame", ctx -> {
          String rq = ctx.body();
                
          e = new GameBoard();
          char tt = rq.charAt(rq.indexOf("type=") + "type=".length());
          p1 = new Player();
          dummyString = "don't plagiarize";
          System.out.println(dummyString);
          dummy += 1;
          p1.changeTheId(1);
          p1.changeTheId(2);
          p1.changeTheId(1);
          p1.changePlayerType(tt);
          
          e.makeIt1st(p1);
          
          Gson bbb = new Gson();
          ctx.result(bbb.toJson(e));
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
        // Add logger here
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
