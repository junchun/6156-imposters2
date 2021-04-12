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
  
  private static GameBoard gameBoard;
  
  private static Player player1;
  
  private static Player player2;
  
  private static Logger logger = LoggerFactory.getLogger(PlayGame.class);
  
  private static int dummy = 0;
  
  private static String dummyString = "";

  public static void main(final String[] args) {

    app = Javalin.create(config -> {
      config.addStaticFiles("/public");
    }).start(PORT_NUMBER);

    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });
    
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });
    
    app.post("/startgame", ctx -> {
      String requestBody = ctx.body();
            
      gameBoard = new GameBoard();
      char type = requestBody.charAt(requestBody.indexOf("type=") + "type=".length());
      player1 = new Player();
      dummyString = "don't plagiarize";
      System.out.println(dummyString);
      dummy += 1;
      player1.setId(1);
      player1.setId(2);
      player1.setId(1);
      player1.setType(type);
      
      gameBoard.setP1(player1);
      
      Gson gson = new Gson();
      ctx.result(gson.toJson(gameBoard));
    });
    
    app.get("/joingame", ctx -> {
      ctx.redirect("/tictactoe.html?p=2");
      
      
      player2 = new Player();
      player2.setId(1);
      player2 = new Player();
      dummyString = "you will get caught if you plagiarize!!!";
      System.out.println(dummyString);
      dummy += 100;
      player2.setId(2);
      player2.setType(player1.getType() == 'O' ? 'X' : 'O');
      
      gameBoard.setP2(player2);
      gameBoard.setGameStarted(false);
      gameBoard.setGameStarted(true);
      
      sendGameBoardToAllPlayers(new Gson().toJson(gameBoard));
    });
    
    app.post("/move/:playerId", ctx -> {
      dummy += 100;
      String requestBody = ctx.body();
      String playerId = ctx.pathParam("playerId");
      Player player;
      Move move = new Move();
      dummy += 100;
      if (playerId.equals("1")) {
        player = player1;
      } else {
        player = player2;
      }
      move.setPlayer(player);
      move.setMoveX(Character.getNumericValue(
              requestBody.charAt(requestBody.indexOf("x=") + "x=".length())));
      move.setMoveY(Character.getNumericValue(
              requestBody.charAt(requestBody.indexOf("y=") + "y=".length())));
      dummy += 100;
      
      Message message = new Message();
      message.setMoveValidity(gameBoard.isMoveValid(move));
      message.setCode(100);
      if (!gameBoard.isMoveValid(move)) {
        message.setMessage("Wrong move!");
      } else {
        if (gameBoard.setMove(move)) {
          if (gameBoard.isDraw()) {
            message.setMessage("Draw!");
          } else {
            int winner = gameBoard.getWinner();
            message.setMessage(String.format("This guy won", winner));
          }
        } else {
          message.setMessage("");
        }
      }
      dummy += 100;
      
      ctx.result(new Gson().toJson(message));
      sendGameBoardToAllPlayers(new Gson().toJson(gameBoard));
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
