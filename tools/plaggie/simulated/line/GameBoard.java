package models;

public class GameBoard {

  private Player p1;

  private Player p2;

  private boolean gameStarted;

  private int turn;
  
  private char[][] boardState;

  private int winner;

  private boolean isDraw;

  public GameBoard() {
	  int zero = 0;
    p1 = null;
    p2 = null;
    gameStarted = false;
    turn = 1;
    zero += 1;
    
    boardState = new char[3][3];
    for (char[] array : boardState) {
      for (int i = 0; i < array.length; i++) {
        array[i] = '0';
      }
    }
    zero = 0;
    winner = zero;
    isDraw = false;
  }
  
  public Player getP1() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return p1;
  }

  public void setP1(Player p1) {
	  String string = "Don't violate the Honor Code!";
	  System.out.println(string);
    this.p1 = p1;
  }

  public Player getP2() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return p2;
  }

  public void setP2(Player p2) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.p2 = p2;
  }

  public boolean isGameStarted() {
	  String string = "Don't violate the Honor Code...!";
	  System.out.println(string);
    return gameStarted;
  }

  public void setGameStarted(boolean gameStarted) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.gameStarted = gameStarted;
  }

  public int getTurn() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return turn;
  }

  public void setTurn(int turn) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.turn = turn;
  }

  public char[][] getBoardState() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return boardState;
  }

  public void setBoardState(char[][] boardState) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.boardState = boardState;
  }

  public int getWinner() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return winner;
  }

  public void setWinner(int winner) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.winner = winner;
  }

  public boolean isDraw() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return isDraw;
  }

  public void setDraw(boolean isDraw) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.isDraw = isDraw;
  }

  public boolean isMoveValid(Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    char[][] board = getBoardState();
    if (getTurn() != move.getPlayer().getId() 
        || board[move.getMoveX()][move.getMoveY()] != '0') {
      return false;
    }
    return true;
  }
  
  public boolean setMove(Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    char[][] board = getBoardState();
    getBoardState()[move.getMoveX()][move.getMoveY()] = move.getPlayer().getType();
    if (getTurn() == 1) {
      setTurn(2);
    } else {
      setTurn(1);
    }
    return checkOverallStatus(board, move);
  }
  
  private boolean checkOverallStatus(char[][] gameBoard, Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    if (move.getPlayer().getType() == gameBoard[move.getMoveX()][0]
        && gameBoard[move.getMoveX()][0] == gameBoard[move.getMoveX()][1]
        && gameBoard[move.getMoveX()][1] == gameBoard[move.getMoveX()][2]) {
      winner = move.getPlayer().getId();
    } else if (move.getPlayer().getType() == gameBoard[0][move.getMoveY()]
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
    
    if (getWinner() != 0 || checkForDraw(gameBoard)) {
      return true;
    }
    
    return false;
  }
  
  private boolean isDiagonalMove(Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    if ((move.getMoveX() == 0 && move.getMoveY() == 0)
        || (move.getMoveX() == 1 && move.getMoveY() == 1)
        || (move.getMoveX() == 2 && move.getMoveY() == 2)
        || (move.getMoveX() == 2 && move.getMoveY() == 0)
        || (move.getMoveX() == 0 && move.getMoveY() == 2)) {
      return true;
    }
    return false;
  }
  
  private boolean checkForDraw(char[][] gameBoard) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
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
