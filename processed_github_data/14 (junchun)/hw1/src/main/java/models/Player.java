package models;

public class Player {

  private char type;

  private int id;

  /**
   * Get player type (O/X).
   * @return player type
   */
  public char getType() {
    return type;
  }

  /**
   * Set player type (O/X).
   * @param type player type
   */
  public void setType(char type) {
    this.type = type;
  }

  /**
   * Get player id.
   * @return player id
   */
  public int getId() {
    return id;
  }

  /**
   * Set player id.
   * @param id player id
   */
  public void setId(int id) {
    this.id = id;
  }
}
