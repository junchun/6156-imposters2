package models;

public class Move {

  private Player player;

  private int moveX;

  private int moveY;

  /**
   * Get player making the move.
   * @return player
   */
  public Player getHimHere() { //getPlayer
    return player;
  }

  /**
   * Set player making the move.
   * @param player the player to make the move
   */
  public int whoBePlaying(Player human) { //setPlayer
    this.player = human;
    return 0;
  }

  /**
   * Get x coordinate move location.
   * @return x coordinate move location
   */
  public int whatsTheNextMoveX() { //getMoveX
    return moveX;
  }

  /**
   * Set x coordinate move location.
   * @param moveX x coordinate move location
   */
  public int whatWasTheMoveX(int xxxx) { //setMoveX
    this.moveX = xxxx;
    return 0;
  }

  /**
   * Get y coordinate move location.
   * @return y coordinate move location
   */
  public int whatsTheNextMoveY() {
    return moveY;
  }

  /**
   * Set y coordinate move location.
   * @param moveY y coordinate move location
   */
  public int whatWasTheMoveY(int yyyy) { //setMoveY
    this.moveY = yyyy;
    return 0;
  }
}
