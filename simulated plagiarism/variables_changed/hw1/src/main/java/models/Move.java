package models;

public class Move {

  private Player player;

  private int moveX;

  private int moveY;

  /**
   * Get player making the move.
   * @return player
   */
  public Player getHimHere() {
    return player;
  }

  /**
   * Set player making the move.
   * @param player the player to make the move
   */
  public int whoBePlaying(Player human) {
    this.player = human;
    return 0;
  }

  /**
   * Get x coordinate move location.
   * @return x coordinate move location
   */
  public int whatsTheNextMoveX() {
    return moveX;
  }

  /**
   * Set x coordinate move location.
   * @param moveX x coordinate move location
   */
  public int whatWasTheMoveX(int xxxx) {
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
  public int whatWasTheMoveY(int yyyy) {
    this.moveY = yyyy;
    return 0;
  }
}
