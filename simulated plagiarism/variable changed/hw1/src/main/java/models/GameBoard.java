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
  public Player whereIs1st() { //getP1
    return p1;
  }

  /**
   * Set player 1.
   * @param p1 player 1
   */
  public int makeIt1st(Player im1) { //setP1
    this.p1 = im1;
    return 0;
  }

  /**
   * Get player 2.
   * @return player 2
   */
  public Player whereIs2nd() { //getP2
    return p2;
  }

  /**
   * Set player 2.
   * @param p2 player 2
   */
  public int makeIt2nd(Player bb) { //setP2
    this.p2 = bb;
    return 0;
  }

  /**
   * Check if the game has started.
   * @return true if game has started
   */
  public int startedOrNot() { //isGameStarted
    if (this.gameStarted)
		return 1;
    return 0;
  }

  /**
   * Set game status to started.
   * @param gameStarted game status
   */
  public int asdgasg(boolean dd) { //setGameStarted
    this.gameStarted = dd;
    return 0;
  }

  /**
   * Get play turn.
   * @return play turn
   */
  public int iiiee() { //getTurn
    return turn;
  }

  /**
   * Set play turn.
   * @param turn play turn to set
   */
  public int ssttt(int tt) { //setTurn
    this.turn = tt;
    return 0;
  }

  /**
   * Get board status.
   * @return the board state
   */
  public char[][] ggeeee() { //getBoardState
    return boardState;
  }

  /**
   * Set board status.
   * @param boardState the board state to set
   */
  public int sbssss(char[][] eee) { //setBoardState
    this.boardState = eee;
    return 0;
  }

  /**
   * Get game winner.
   * @return winning player
   */
  public int gwwwggww() { //getWinner
    return this.winner;
  }

  /**
   * Set game winner.
   * @param winner winning player
   */
  public int sswwwww(int ww) { //setWinner
    this.winner = ww;
    return 0;
  }

  /**
   * Get game drawn status.
   * @return true if the game is tied
   */
  public int didanyonewin() { //isDraw
	if (isDraw)
		return 1;
	return 0;
  }

  /**
   * Set game draw status.
   * @param isDraw game draw status 
   */
  public int setItTie(boolean ddd) { //setDraw
    this.isDraw = ddd;
    return 0;
  }

  /**
   * Check if the move is valid or not.
   * If invalid, a message appears to the players.
   * @param move player move
   * @return false if the move is invalid
   */
  public int mmvvvv113(Move move) { //isMoveValid
    char[][] board = ggeeee();
    if (iiiee() != move.getHimHere().whatsTheId() 
        || board[move.whatsTheNextMoveX()][move.whatsTheNextMoveY()] != '0') {
      return 0;
    }
    return 1;
  }
  
  /**
   * Set a valid move and update overall game status.
   * Should be used after isMoveValid() method to check if the move is valid.
   * @param move move
   * @return true if the game is over
   */
  public int ssmmmm113(Move move) { //setMove
    char[][] board = ggeeee();
    board[move.whatsTheNextMoveX()][move.whatsTheNextMoveY()] = move.getHimHere().retrievePlayerType();
    if (iiiee() == 1) {
    	ssttt(2);
    } else {
    	ssttt(1);
    }
    return ccooss444(board, move);
  }
  
  /**
   * Check if the game has concluded.
   * Update winner and isDraw if the game is over.
   * @param gameBoard game board
   * @param move player move
   * @return true if the game is over
   */
  private int ccooss444(char[][] e, Move move) { //checkOverallStatus
    char type = move.getHimHere().retrievePlayerType();
    if (type == e[move.whatsTheNextMoveX()][0]
        && e[move.whatsTheNextMoveX()][0] == e[move.whatsTheNextMoveX()][1]
        && e[move.whatsTheNextMoveX()][1] == e[move.whatsTheNextMoveX()][2]) {
      winner = move.getHimHere().whatsTheId();
    } else if (type == e[0][move.whatsTheNextMoveY()]
          && e[0][move.whatsTheNextMoveY()] == e[1][move.whatsTheNextMoveY()]
          && e[1][move.whatsTheNextMoveY()] == e[2][move.whatsTheNextMoveY()]) {
      winner = move.getHimHere().whatsTheId();
    } else if (diagonalOrWhat(move) == 1) {
      if (move.getHimHere().retrievePlayerType() == e[0][0]
          && e[0][0] == e[1][1]
          && e[1][1] == e[2][2]) {
        winner = move.getHimHere().whatsTheId();
      } else if (move.getHimHere().retrievePlayerType() == e[0][2]
          && e[0][2] == e[1][1]
          && e[1][1] == e[2][0]) {
        winner = move.getHimHere().whatsTheId();
      }
    }
    
    isDraw = false;
    if (ccffdraw(e) == 1)
    	isDraw = true;
    if (gwwwggww() != 0 || isDraw) {
      return 1;
    }
    
    return 0;
  }
  
  /**
   * Check if the move is made to a diagonal line cell.
   * @param move player move
   * @return true if the move is on a diagonal line
   */
  private int diagonalOrWhat(Move v) { //isDiagonalMove
    if ((v.whatsTheNextMoveX() == 0 && v.whatsTheNextMoveY() == 0)
        || (v.whatsTheNextMoveX() == 1 && v.whatsTheNextMoveY() == 1)
        || (v.whatsTheNextMoveX() == 2 && v.whatsTheNextMoveY() == 2)
        || (v.whatsTheNextMoveX() == 2 && v.whatsTheNextMoveY() == 0)
        || (v.whatsTheNextMoveX() == 0 && v.whatsTheNextMoveY() == 2)) {
      return 1;
    }
    return 0;
  }
  
  /**
   * Check if there is any default value left on the board.
   * If there is no default value, then the game is over and it's a draw.
   * @param gameBoard game board
   * @return true if the game is a draw
   */
  private int ccffdraw(char[][] eee) { //checkForDraw
    for (int k = 0; k < eee.length; k++) {
      for (int p = 0; p < eee[0].length; p++) {
        if (eee[k][p] == '0') {
          return 0;
        }
      }
    }
    return 1;
  }
  
}

