package models;

public class GameBoard {

  /**
   * Player who starts the game.
   */
  private Player p1;

  /**
   * Player who joins the game.
   */
  private Player p2;

  /**
   * Game starts immediately after both players joined.
   */
  private boolean gameStarted;

  /**
   * Indicate which players turn is it.
   */
  private int turn;

  /**
   * Record current board status.
   */
  private char[][] boardState;

  /**
   * Player wins a game when three in a row/column.
   */
  private int winner;

  /**
   * Game is a draw when moves are exhausted.
   */
  private boolean isDraw;

  /**
   * Set dimension of game board (default is 3).
   */
  static final int DIM = 3;

  /**
   * Get player 1.
   * @return Player 1 object.
   */
  public Player getP1() {
    return p1;
  }

  /**
   * Set player 1 of game board.
   * @param p Player object
   */
  public void setP1(final Player p) {
    this.p1 =  p;
  }

  /**
   * Get player 2.
   * @return Player 2 object
   */
  public Player getP2() {
    return p2;
  }

  /**
   * Set player 2 of game board.
   * @param p Player object
   */
  public void setP2(final Player p) {
    this.p2 = p;
  }

  /**
   * Check if game started.
   * @return boolean
   */
  public boolean isGameStarted() {
    return gameStarted;
  }

  /**
   * Starts the game.
   * @param started
   */
  public void setGameStarted(final boolean started) {
    this.gameStarted = started;
  }

  /**
   * Get gameStarted variable.
   * @return gameStarted variable
   */
  public boolean getGameStarted() {
    return gameStarted;
  }

  /**
   * Get current turn.
   * @return current player's turn
   */
  public int getTurn() {
    return turn;
  }

  /**
   * Set current turn.
   * @param t Player's turn
   */
  public void setTurn(final int t) {
    this.turn = t;
  }

  /**
   * Give turn to other player.
   */
  public void switchTurn() {
    turn = turn % 2 + 1;
  }

  /**
   * Initialize game board.
   */
  public void setBoardState() {
    boardState = new char[DIM][DIM];
  }

  /**
   * get current board state.
   * @return board state
   */
  public char[][] getBoardState() {
    return boardState;
  }

  /**
   * Check if move is valid.
   * @param move
   * @param message
   * @return boolean
   */
  public boolean isValid(final Move move, final Message message) {
    String code = String.valueOf(move.getPlayer().getId());
    code += String.valueOf(move.getMoveX());
    code += String.valueOf(move.getMoveY());
    message.setCode(Integer.parseInt(code));
    if (move.getPlayer().getId() != turn) {
      message.setMoveValidity(false);
      message.setMessage("Not Your Turn!");
      return false;
    } else if (boardState[move.getMoveX()][move.getMoveY()] != '\u0000') {
      message.setMoveValidity(false);
      message.setMessage("Invalid Move. Try Again!");
      return false;
    } else {
      message.setMoveValidity(true);
      return true;
    }

  }

  /**
   * Mark the move on game board.
   * @param move
   */
  public void makeMove(final Move move) {
    char mark = move.getPlayer().getType();
    boardState[move.getMoveX()][move.getMoveY()] = mark;
  }

  /**
   * Get player from player id.
   * @param playerId
   * @return player object
   */
  public Player getPlayerFromId(final int playerId) {
    if (playerId == 1) {
      return p1;
    } else {
      return p2;
    }
  }

  /**
   * Check if game is over.
   * @param move
   * @return boolean
   */
  public boolean isOver(final Move move) {
    int moves = 0;
    for (int i = 0; i < boardState.length; i++) {
      for (int j = 0; j < boardState.length; j++) {
        if (boardState[i][j] == 'X' || boardState[i][j] == 'O') {
          moves++;
        }
      }
    }
    if (moves == boardState.length * boardState.length) {
      isDraw = true;
      return true;
    }
    int row = move.getMoveX();
    int col = move.getMoveY();
    if (boardState[0][col] ==  boardState[1][col]
      && boardState[0][col] == boardState[2][col]) {
      winner = move.getPlayer().getId();
      return true;
    } else if (boardState[row][0] == boardState[row][1]
      && boardState[row][0] == boardState[row][2]) {
      winner = move.getPlayer().getId();
      return true;
    } else if (row == col && boardState[0][0] == boardState[1][1]
      && boardState[0][0] == boardState[2][2]) {
      winner = move.getPlayer().getId();
      return true;
    } else if (row + col == 2 && boardState[0][2] == boardState[1][1]
      && boardState[0][2] == boardState[2][0]) {
      winner = move.getPlayer().getId();
      return true;
    }
    return false;
  }

  /**
   * Set isDraw variable.
   * @param draw
   */
  public void setIsDraw(final boolean draw) {
    this.isDraw = draw;
  }

  /**
   * Get isDraw variable.
   * @return isDraw variable
   */
  public boolean getIsDraw() {
    return isDraw;
  }

  /**
   * Set winner.
   * @param playerId
   */
  public void setWinner(final int playerId) {
    this.winner = playerId;
  }
  /**
   * Get winner variable.
   * @return winner variable
   */
  public int getWinner() {
    return winner;
  }

}
