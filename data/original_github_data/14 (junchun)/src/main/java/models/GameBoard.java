package models;

public class GameBoard {

  private Player p1;

  private Player p2;

  private boolean gameStarted;

  private int turn;
  
  private char[][] boardState;

  private int winner;

  private boolean isDraw;

  /**
   * Constructor for GameBoard.
   */
  public GameBoard() {
    p1 = null;
    p2 = null;
    gameStarted = false;
    turn = 1;
    
    // fill the 3 by 3 board with the default value
    boardState = new char[3][3];
    for (char[] array : boardState) {
      for (int i = 0; i < array.length; i++) {
        array[i] = '0';
      }
    }
    winner = 0;
    isDraw = false;
  }
  
  /**
   * Get player 1.
   * @return player 1
   */
  public Player getP1() {
    return p1;
  }

  /**
   * Set player 1.
   * @param p1 player 1
   */
  public void setP1(Player p1) {
    this.p1 = p1;
  }

  /**
   * Get player 2.
   * @return player 2
   */
  public Player getP2() {
    return p2;
  }

  /**
   * Set player 2.
   * @param p2 player 2
   */
  public void setP2(Player p2) {
    this.p2 = p2;
  }

  /**
   * Check if the game has started.
   * @return true if game has started
   */
  public boolean isGameStarted() {
    return gameStarted;
  }

  /**
   * Set game status to started.
   * @param gameStarted game status
   */
  public void setGameStarted(boolean gameStarted) {
    this.gameStarted = gameStarted;
  }

  /**
   * Get play turn.
   * @return play turn
   */
  public int getTurn() {
    return turn;
  }

  /**
   * Set play turn.
   * @param turn play turn to set
   */
  public void setTurn(int turn) {
    this.turn = turn;
  }

  /**
   * Get board status.
   * @return the board state
   */
  public char[][] getBoardState() {
    return boardState;
  }

  /**
   * Set board status.
   * @param boardState the board state to set
   */
  public void setBoardState(char[][] boardState) {
    this.boardState = boardState;
  }

  /**
   * Get game winner.
   * @return winning player
   */
  public int getWinner() {
    return winner;
  }

  /**
   * Set game winner.
   * @param winner winning player
   */
  public void setWinner(int winner) {
    this.winner = winner;
  }

  /**
   * Get game drawn status.
   * @return true if the game is tied
   */
  public boolean isDraw() {
    return isDraw;
  }

  /**
   * Set game draw status.
   * @param isDraw game draw status 
   */
  public void setDraw(boolean isDraw) {
    this.isDraw = isDraw;
  }

  /**
   * Check if the move is valid or not.
   * If invalid, a message appears to the players.
   * @param move player move
   * @return false if the move is invalid
   */
  public boolean isMoveValid(Move move) {
    char[][] board = getBoardState();
    if (getTurn() != move.getPlayer().getId() 
        || board[move.getMoveX()][move.getMoveY()] != '0') {
      return false;
    }
    return true;
  }
  
  /**
   * Set a valid move and update overall game status.
   * Should be used after isMoveValid() method to check if the move is valid.
   * @param move move
   * @return true if the game is over
   */
  public boolean setMove(Move move) {
    char[][] board = getBoardState();
    board[move.getMoveX()][move.getMoveY()] = move.getPlayer().getType();
    if (getTurn() == 1) {
      setTurn(2);
    } else {
      setTurn(1);
    }
    return checkOverallStatus(board, move);
  }
  
  /**
   * Check if the game has concluded.
   * Update winner and isDraw if the game is over.
   * @param gameBoard game board
   * @param move player move
   * @return true if the game is over
   */
  private boolean checkOverallStatus(char[][] gameBoard, Move move) {
    char type = move.getPlayer().getType();
    if (type == gameBoard[move.getMoveX()][0]
        && gameBoard[move.getMoveX()][0] == gameBoard[move.getMoveX()][1]
        && gameBoard[move.getMoveX()][1] == gameBoard[move.getMoveX()][2]) {
      winner = move.getPlayer().getId();
    } else if (type == gameBoard[0][move.getMoveY()]
          && gameBoard[0][move.getMoveY()] == gameBoard[1][move.getMoveY()]
          && gameBoard[1][move.getMoveY()] == gameBoard[2][move.getMoveY()]) {
      winner = move.getPlayer().getId();
    } else if (isDiagonalMove(move)) {
      if (move.getPlayer().getType() == gameBoard[0][0]
          && gameBoard[0][0] == gameBoard[1][1]
          && gameBoard[1][1] == gameBoard[2][2]) {
        winner = move.getPlayer().getId();
      } else if (move.getPlayer().getType() == gameBoard[0][2]
          && gameBoard[0][2] == gameBoard[1][1]
          && gameBoard[1][1] == gameBoard[2][0]) {
        winner = move.getPlayer().getId();
      }
    }
    
    isDraw = checkForDraw(gameBoard);
    if (getWinner() != 0 || isDraw) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Check if the move is made to a diagonal line cell.
   * @param move player move
   * @return true if the move is on a diagonal line
   */
  private boolean isDiagonalMove(Move move) {
    if ((move.getMoveX() == 0 && move.getMoveY() == 0)
        || (move.getMoveX() == 1 && move.getMoveY() == 1)
        || (move.getMoveX() == 2 && move.getMoveY() == 2)
        || (move.getMoveX() == 2 && move.getMoveY() == 0)
        || (move.getMoveX() == 0 && move.getMoveY() == 2)) {
      return true;
    }
    return false;
  }
  
  /**
   * Check if there is any default value left on the board.
   * If there is no default value, then the game is over and it's a draw.
   * @param gameBoard game board
   * @return true if the game is a draw
   */
  private boolean checkForDraw(char[][] gameBoard) {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        if (gameBoard[i][j] == '0') {
          return false;
        }
      }
    }
    return true;
  }
  
}
