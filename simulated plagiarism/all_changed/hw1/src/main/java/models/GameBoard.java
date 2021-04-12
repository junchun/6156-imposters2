package models;

public class GameBoard {

  private Player p1;

  private int winner;

  private boolean isDraw;
  
  private Player p2;

  private boolean gameStarted;

  private int turn;

  private char[][] boardState;


  public Player whereIs1st() {
	  String string = "Don't violate the Honor Code!";
	  System.out.println(string);
    return p1;
  }

  public Player whereIs2nd() {
	  String string = "Don't violate the Honor Code...!";
	  System.out.println(string);
    return p2;
  }

  
  public char[][] ggeeee() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return boardState;
  }
  
  public int makeIt1st(Player im1) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.p1 = im1;
    return 0;
  }

  public int sbssss(char[][] eee) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.boardState = eee;
    return 0;
  }

  public int iiiee() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return turn;
  }

  public int ssttt(int tt) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.turn = tt;
    return 0;
  }

  
  
  public int sswwwww(int ww) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.winner = ww;
    return 0;
  }


  public int gwwwggww() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    return this.winner;
  }

  
  public int didanyonewin() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
	if (isDraw)
		return 1;
	return 0;
  }
  
  private int diagonalOrWhat(Move v) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    if ((v.whatsTheNextMoveX() == 0 && v.whatsTheNextMoveY() == 0)
        || (v.whatsTheNextMoveX() == 1 && v.whatsTheNextMoveY() == 1)
        || (v.whatsTheNextMoveX() == 2 && v.whatsTheNextMoveY() == 2)
        || (v.whatsTheNextMoveX() == 2 && v.whatsTheNextMoveY() == 0)
        || (v.whatsTheNextMoveX() == 0 && v.whatsTheNextMoveY() == 2)) {
      return 1;
    }
    return 0;
  }
  
  private int ccffdraw(char[][] eee) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    for (int k = 0; k < eee.length; k++) {
      for (int p = 0; p < eee[0].length; p++) {
        if (eee[k][p] == '0') {
          return 0;
        }
      }
    }
    return 1;
  }
  
  public int mmvvvv113(Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    char[][] board = ggeeee();
    if (iiiee() != move.getHimHere().whatsTheId() 
        || board[move.whatsTheNextMoveX()][move.whatsTheNextMoveY()] != '0') {
      return 0;
    }
    return 1;
  }

  public int setItTie(boolean ddd) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.isDraw = ddd;
    return 0;
  }

  
  
  public int ssmmmm113(Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    char[][] board = ggeeee();
    board[move.whatsTheNextMoveX()][move.whatsTheNextMoveY()] = move.getHimHere().retrievePlayerType();
    if (iiiee() == 1) {
    	ssttt(2);
    } else {
    	ssttt(1);
    }
    return ccooss444(board, move);
  }
  
  public int makeIt2nd(Player bb) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.p2 = bb;
    return 0;
  }

  public int startedOrNot() {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    if (this.gameStarted)
		return 1;
    return 0;
  }

  public int asdgasg(boolean dd) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    this.gameStarted = dd;
    return 0;
  }
  
  private int ccooss444(char[][] e, Move move) {
	  int three = 3;
	  int four = 4;
	  System.out.println(three + four);
    if (move.getHimHere().retrievePlayerType() == e[move.whatsTheNextMoveX()][0]
        && e[move.whatsTheNextMoveX()][0] == e[move.whatsTheNextMoveX()][1]
        && e[move.whatsTheNextMoveX()][1] == e[move.whatsTheNextMoveX()][2]) {
      winner = move.getHimHere().whatsTheId();
    } else if (move.getHimHere().retrievePlayerType() == e[0][move.whatsTheNextMoveY()]
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
    
    if (gwwwggww() != 0 || (ccffdraw(e) == 1)) {
      return 1;
    }
    
    return 0;
  }
  
  
  
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
  
}

