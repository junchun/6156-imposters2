package models;

public class Move {

  private Player player;

  private int moveX;

  private int moveY;

  /**
   * Constructor for the Move class.
   * 
   * @param player executing move
   * @param moveX represents x coordinate of move
   * @param moveY represents y coordinate of move
   */
  public Move(Player player, int moveX, int moveY) {
    this.player = player;
    this.moveX = moveX;
    this.moveY = moveY;
  }

  /**
   * Returns player executing move.
   * 
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets player of move.
   * 
   * @param player the player to set
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Returns x coordinate of move.
   * 
   * @return the moveX
   */
  public int getMoveX() {
    return moveX;
  }

  /**
   * Sets x coordinate of move.
   * 
   * @param moveX the moveX to set
   */
  public void setMoveX(int moveX) {
    this.moveX = moveX;
  }

  /**
   * Returns y coordinate of move.
   * 
   * @return the moveY
   */
  public int getMoveY() {
    return moveY;
  }

  /**
   * Sets y coordinate of move.
   * 
   * @param moveY the moveY to set
   */
  public void setMoveY(int moveY) {
    this.moveY = moveY;
  }

}
