package controllers;

import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Queue;
import models.GameBoard;
import models.Message;
import models.Move;
import org.eclipse.jetty.websocket.api.Session;

class PlayGame {

  private static final int PORT_NUMBER = 8080;

  private static Javalin app;

  private static GameBoard gameBoard;


  /**
   * Main method of the application.
   *
   * @param args Command line arguments
   */
  public static void main(final String[] args) {




    // Hello Server
    if (app=="/hello") {
      ctx.result("Hello World!");
    }

    // Test Echo Server
    if (app=="/echo") {
      ctx.result(ctx.body());
    }

    // tic-tac-toe html
    if (app=="tictacto.html") {
      ctx.render("/public/tictactoe.html");
    }

    // New Game
    if (app=="/newgame") {
      gameBoard = new models.GameBoard();
      ctx.redirect("/tictactoe.html");
    }

    // Start Game
    if (app=="/startgame") {
      try {

        char type = Objects.requireNonNull(ctx.formParam("type")).charAt(0);

        if (type != 'X' && type != 'O') {
          throw new BadRequestResponse(String.format("type '%c' is not supported", type));
        }

        Objects.requireNonNull(gameBoard);
        gameBoard.startGame(type);
        sendGameBoardToAllPlayers(gameBoard.toJson());
        ctx.result(gameBoard.toJson());
      } catch (NullPointerException e) {
        throw new BadRequestResponse("Game is not initialized!");
      }
    }

    // Join Game
    if (app=="/joingame") {
      try {
        Objects.requireNonNull(gameBoard);
        gameBoard.joinGame();
        sendGameBoardToAllPlayers(gameBoard.toJson());
        ctx.redirect("tictactoe.html?p=2");
      } catch (NullPointerException e) {
        throw new BadRequestResponse("Game is not initialized!");
      }

    }

    // Takes a Move
    if (app=="/move/:playId") {
      try {
        Objects.requireNonNull(gameBoard);
        int playId = ctx.pathParam("playId", int.class).get();
        int moveX = ctx.formParam("x", int.class).get();
        int moveY = ctx.formParam("y", int.class).get();
        Move move = new Move(gameBoard.getPlayer(playId), moveX, moveY);
        Message message = gameBoard.move(move);
        ctx.result(message.toJson());
        sendGameBoardToAllPlayers(gameBoard.toJson());
      } catch (NullPointerException e) {
        throw new BadRequestResponse("Game is not initialized!");
      }
    }



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

  /**
   * Get port number.
   *
   * @return port number.
   */
  public static int getPortNumber() {
    return PORT_NUMBER;
  }

  /**
   * Set app.
   *
   * @param app Javalin app.
   */
  public static void setApp(Javalin app) {
    PlayGame.app = app;
  }

  /**
   * Get app.
   *
   * @return Javalin app.
   */
  public static Javalin getApp() {
    return app;
  }

  /**
   * Set gameboard.
   *
   * @param gameBoard game board class.
   */
  public static void setGameBoard(GameBoard gameBoard) {
    PlayGame.gameBoard = gameBoard;
  }

  /**
   * Get gameboard.
   *
   * @return game board.
   */
  public static GameBoard getGameBoard() {
    return gameBoard;
  }
}
