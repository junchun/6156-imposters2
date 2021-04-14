package models;

public class Player {

  private char type;

  private int id;

  /**
   * Returns type of player.
   * 
   * @return the type
   */
  public char getType() {
    return type;
  }

  /**
   * Sets type of player. 
   * 
   * <p>Note: For tic-tac-toe, should take value of either 'X' or 'O'.
   * 
   * @param type the type to set
   */
  public void setType(char type) {
    this.type = type;
  }

  /**
   * Gets id of player. 
   * 
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id of player. 
   * 
   * <p>For two-player tic-tac-toe, id should be either 1 or 2.
   * 
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

}
