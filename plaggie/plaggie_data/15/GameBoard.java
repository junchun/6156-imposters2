package models;

public class GameBoard {

  private int row;
  
  private int col;
  
  private Player p1;

  private Player p2;

  private char[] chessPieces;
  
  private boolean gameStarted;

  private int turn;

  private char[][] boardState;

  private int winner;

  private boolean isDraw;
  
  /** Default constructor that initializes board with empty chars.
   */
  public GameBoard() {
    this.row = 3;
    this.col = 3;
    this.chessPieces = new char[] {'X', 'O'};
    this.boardState = new char [row][col];
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.boardState[i][j] = ' ';
      }
    }
    this.gameStarted = false;
    this.turn = 1;
    this.winner = 0;
    this.isDraw = false;
  }
  
  /** Complete constructor covering all data fields.
   * @param p1 player1
   * @param p2 player2
   * @param start is game started or not
   * @param turn which player's turn (equal to player id)
   * @param state game board state
   * @param winner who is the winner
   * @param isDraw is this game a draw or not
   */
  public GameBoard(Player p1, Player p2, boolean start, int turn,
      char[][] state, int winner, boolean isDraw) {
    this.p1 = p1;
    this.p2 = p2;
    this.gameStarted = start;
    this.turn = turn;
    this.boardState = state;
    this.winner = winner;
    this.isDraw = isDraw;
  }
  
  /** Method to make a move and return game status.
   * @param id id of player who makes this move
   * @param x row location of move
   * @param y col location of move
   * @return Message telling whether move is valid or not
   */
  public Message makeMove(int id, int x, int y) {
    Player p;
    if (id == this.p1.getId()) {
      p = p1;
    } else {
      p = p2;
    }
    // Check validity of move
    Move m = new Move(p, x, y);
    Message msg = validMove(m);
    if (msg.isMoveValidity()) {
      this.boardState[x][y] = m.getPlayer().getType();
      // Check if any players wins or a draw occurs
      if (endGame()) {
        if (isDraw) {
          msg.setCode(110);
          msg.setMessage("Dra");
        } else {
          msg.setCode(100 + this.winner);
          msg.setMessage("Win " + Integer.toString(this.winner));
        }
      } else {
        if (turn == 1) {
          turn = 2;
        } else {
          turn = 1;
        }
        msg.setCode(100);
        msg.setMessage("Con");
      }
    }

    return msg;
  }

  /** Method to verify if a move is valid.
   * (1) game has started
   * (2) player moving is the turn player
   * (3) x and y are within range
   * @param m Move object
   * @return Message telling whether move is valid
   */
  
  public Message validMove(Move m) {
    if (!this.gameStarted) {
      return new Message(false, -1, "Game has not started");
    }
    if (m.getPlayer().getId() != turn) {
      return new Message(false, -1, "Please wait for the turn player.");
    }
    int x = m.getMoveX();
    int y = m.getMoveY();
    if (y < 0 || y > col || x < 0 || x > row) {
      return new Message(false, -1, "Move not on board.");
    } else if (boardState[x][y] != ' ') {
      return new Message(false, -2, "Cannot take an occupied slot.");
    }
    return new Message(true, 100, "Valid move.");
  }
  
  /** Check if the game is end.
   * (1) A player wins
   * (2) Draw
   * @return whether game ends or no
   */
  public boolean endGame() {
    boolean win = false;
    
    // Check win conditions involving (0,0)
    char mark = boardState[0][0];
    if (mark == ' ') {
      // no need for further checking since there is no chess
    } else if (boardState[0][1] == mark && boardState[0][2] == mark) {
      win = true;
    } else if (boardState[1][0] == mark && boardState[2][0] == mark) {
      win = true;
    } else if (boardState[1][1] == mark && boardState[2][2] == mark) {
      win = true;
    }
    // Check win conditions involving (0,1)
    mark = boardState[0][1];
    if (mark == ' ') {
      // no need for further checking since there is no chess
    } else if (boardState[1][1] == mark && boardState[2][1] == mark) {
      win = true;
    }
    // Check win conditions involving (0,2)
    mark = boardState[0][2];
    if (mark == ' ') {
      // no need for further checking since there is no chess
    } else if (boardState[1][2] == mark && boardState[2][2] == mark) {
      win = true;
    } else if (boardState[1][1] == mark && boardState[2][0] == mark) {
      win = true;
    }
    // Check win conditions involving (1,0)
    mark = boardState[1][0];
    if (mark == ' ') {
      // no need for further checking since there is no chess
    } else if (boardState[1][1] == mark && boardState[1][2] == mark) {
      win = true;
    }
    // Check win conditions involving (2,0)
    mark = boardState[2][0];
    if (mark == ' ') {
      // no need for further checking since there is no chess
    } else if (boardState[2][1] == mark && boardState[2][2] == mark) {
      win = true;
    }
    
    // Check for draw if no winner
    if (!win) {
      isDraw = true;
      for (int i = 0; i < boardState.length; i++) {
        for (int j = 0; j < boardState.length; j++) {
          if (this.boardState[i][j] == ' ') {
            isDraw = false;
            break;
          }
        }
      }
      if (isDraw) {
        winner = 0;
        return true;
      } else {
        return false;
      }
    }
    
    // Now someone wins, we need to determine the winner
    winner = turn;
    
    return win;
  }
  
  // Getters and Setters
  public Player getP1() {
    return p1;
  }

  public void setP1(Player p1) {
    this.p1 = p1;
  }

  public Player getP2() {
    return p2;
  }

  /** Sets up player 2, automatically assigns chess piece type, and start the game.
   */
  public void setP2() {
    Player p2 = new Player(' ', 2);
    // Automatically assign the chess piece
    if (p1.getType() == this.chessPieces[0]) {
      p2.setType(this.chessPieces[1]);
    } else {
      p2.setType(this.chessPieces[0]);
    }
    this.p2 = p2;
    // Start the game if both players are ready
    if (this.p1 != null && this.p2 != null) {
      this.gameStarted = true;
    }
  }
  
  public void setP2(Player p2) {
    this.p2 = p2;
  }

  public boolean isGameStarted() {
    return gameStarted;
  }

  public void setGameStarted(boolean gameStarted) {
    this.gameStarted = gameStarted;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

  public char[][] getBoardState() {
    return boardState;
  }

  public void setBoardState(char[][] boardState) {
    this.boardState = boardState;
  }

  public int getWinner() {
    return winner;
  }

  public void setWinner(int winner) {
    this.winner = winner;
  }

  public boolean isDraw() {
    return isDraw;
  }

  public void setDraw(boolean isDraw) {
    this.isDraw = isDraw;
  }

}
