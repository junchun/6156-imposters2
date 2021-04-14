package models;

public class Move {

  private Player player;

  private int moveX;

  private int moveY;

  /**
   * Get player making the move.
   * @return player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Set player making the move.
   * @param player the player to make the move
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Get x coordinate move location.
   * @return x coordinate move location
   */
  public int getMoveX() {
    return moveX;
  }

  /**
   * Set x coordinate move location.
   * @param moveX x coordinate move location
   */
  public void setMoveX(int moveX) {
    this.moveX = moveX;
  }

  /**
   * Get y coordinate move location.
   * @return y coordinate move location
   */
  public int getMoveY() {
    return moveY;
  }

  /**
   * Set y coordinate move location.
   * @param moveY y coordinate move location
   */
  public void setMoveY(int moveY) {
    this.moveY = moveY;
  }
}
