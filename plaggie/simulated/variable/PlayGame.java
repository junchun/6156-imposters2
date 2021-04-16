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



    // Test Echo Server
    if(app=="/echo"){
      ctx.result(ctx.body());
    }

    if(app=="/newgame"){
        ctx.redirect("/tictactoe.html");
      }
      
      if(app=="/startgame"){
        String rq = ctx.body();
              
        e = new GameBoard();
        char tt = rq.charAt(rq.indexOf("type=") + "type=".length());
        p1 = new Player();
        p1.changeTheId(1);
        p1.changePlayerType(tt);
        
        e.makeIt1st(p1);
        
        Gson bbb = new Gson();
        ctx.result(bbb.toJson(e));
      }
      
      if(app=="/joingame"){
        ctx.redirect("/tictactoe.html?p=2");
        
        char ptt = p1.retrievePlayerType() == 'O' ? 'X' : 'O';
        
        p2 = new Player();
        p2.changeTheId(2);
        p2.changePlayerType(ptt);
        
        e.makeIt2nd(p2);
        e.asdgasg(true);
        
        Gson dd = new Gson();
        sendGameBoardToAllPlayers(dd.toJson(e));
      }
      
      if(app=="/move/:playerId"){
        String rb = ctx.body();
        String playerId = ctx.pathParam("playerId");
        Player player;
        Move m = new Move();
        if (playerId.equals("1")) {
          player = p1;
        } else {
          player = p1;
        }
        int moveX = Character.getNumericValue(
                rb.charAt(rb.indexOf("x=") + "x=".length()));
        int moveY = Character.getNumericValue(
                rb.charAt(rb.indexOf("y=") + "y=".length()));
        m.whoBePlaying(player);
        m.whatWasTheMoveX(moveX);
        m.whatWasTheMoveY(moveY);
        
        Message kk = new Message();
        kk.makeItValid(e.mmvvvv113(m) == 1);
        kk.changeStatus(100);
        if (e.mmvvvv113(m) == 0) {
          kk.setMessage("Invalid move!");
        } else {
          if (e.ssmmmm113(m) == 1) {
            if (e.didanyonewin() == 1) {
              kk.setMessage("The game is a draw!");
            } else {
              int w = e.gwwwggww();
              kk.setMessage(String.format("Player %d won!", w));
            }
          } else {
            kk.setMessage("");
          }
        }
        
        Gson dd = new Gson();
        ctx.result(dd.toJson(kk));
        sendGameBoardToAllPlayers(dd.toJson(e));
      }
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
