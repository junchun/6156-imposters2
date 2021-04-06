package models;

/**
 * This is the method to update board states and conclude the outcome for 
 * the current game.
 * @author Jiaqi Liu
 * @since 2020-9-25
 */
public class GameBoard {

  private Player p1;

  private Player p2;

  private boolean gameStarted;

  private int turn;

  private char[][] boardState;

  private int winner;

  private boolean isDraw;

  /**
   * This is the constructor for GameBoard. 
   */
  public GameBoard() {
    this.turn = 1;
    this.gameStarted = false;
    this.isDraw = false;
  }

  public void setP1(Player p1) {
    this.p1 = p1;
  }

  public void setP2(Player p2) {
    this.p2 = p2;
  }

  public void setGameStarted(boolean gameStarted) {
    this.gameStarted = gameStarted;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

  public void setBoardState(char[][] boardState) {
    this.boardState = boardState;
  }

  public void setWinner(int winner) {
    this.winner = winner;
  }

  public void setIsDraw(boolean isDraw) {
    this.isDraw = isDraw;
  }

  public Player getP1() {
    return p1;
  }

  public Player getP2() {
    return p2;
  }

  /**
   * This is the method to get Player by id.
   * @param id This is the id of the Player to get.
   * @return Player This is the Player got by id.
   */
  public Player getPlayer(int id) {
    if (p1.getId() == id) {
      return p1;
    } else {
      return p2;
    }
  }

  public boolean getGameStarted() {
    return gameStarted;
  }

  public int getTurn() {
    return turn;
  }

  public char[][] getBoardState() {
    return boardState;
  }

  public int getWinner() {
    return winner;
  }

  public boolean getIsDraw() {
    return isDraw;
  }

  /** 
   * This is the method to initialize/reset the game board and all states.
   */
  public void initializeBoardState() {
    boardState = new char[3][3];
    this.turn = 1;
    this.winner = 0;
    this.isDraw = false;
    this.gameStarted = false;
  }

  /**
   * This is the method to check the validity of current move.
   * @param move This is the move the current player attempts.
   * @param message This is the error message returned if the move is invalid.
   * @return boolean  This returns the validity of current move.
   */
  public boolean checkMoveValidity(Move move, Message message) {
    int currPlayerId = move.getPlayer().getId();
    int x = move.getMoveX();
    int y = move.getMoveY();
    String code = String.valueOf(currPlayerId) + String.valueOf(x) + String.valueOf(y);
    message.setCode(Integer.parseInt(code));

    message.setMoveValidity(false);
    if (currPlayerId != turn) {
      message.setMessage("It's not your turn to move. Please wait for another player to move.");
      return false;
    } else {
      if (boardState[x][y] == 'X' || boardState[x][y] == 'O') {
        message.setMessage(
            "The position you select to move is invalid. Please try an unoccupied position.");
        return false;
      } else {
        message.setMoveValidity(true);
        return true;
      }
    }
  }

  /**
   * This is the method to make a move and check whether the game ends after the move.
   * @param move This is the move the  the player attempts.
   * @return boolean This returns false if the game ends after the move and true
   *                 otherwise.
   */
  public boolean makeMove(Move move) {
    int x = move.getMoveX();
    int y = move.getMoveY();
    boardState[x][y] = move.getPlayer().getType(); // Make a Move

    // Check whether the game ends after move
    int occupiedCellsCount = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (boardState[i][j] == 'X' || boardState[i][j] == 'O') {
          occupiedCellsCount++;
        }
      }
    }
    if (occupiedCellsCount == 9) {
      this.isDraw = true;
      return false;
    }

    if (boardState[x][0] == boardState[x][1] && boardState[x][1] == boardState[x][2]) {
      this.winner = move.getPlayer().getId();
      return false;
    }
    if (boardState[0][y] == boardState[1][y] && boardState[1][y] == boardState[2][y]) {
      this.winner = move.getPlayer().getId();
      return false;
    }
    if ((boardState[1][1] == move.getPlayer().getType())
        && (boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2])) {
      this.winner = move.getPlayer().getId();
      return false;
    }
    if ((boardState[1][1] == move.getPlayer().getType())
        && (boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0])) {
      this.winner = move.getPlayer().getId();
      return false;
    }
    return true;
  }

}
