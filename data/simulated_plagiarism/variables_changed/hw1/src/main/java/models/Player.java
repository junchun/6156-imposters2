package models;

public class Player {

  private char type;

  private int id;

  /**
   * Get player type (O/X).
   * @return player type
   */
  public char retrievePlayerType() {
    return type;
  }

  /**
   * Set player type (O/X).
   * @param type player type
   */
  public int changePlayerType(char tt) {
    this.type = tt;
    return 0;
  }

  /**
   * Get player id.
   * @return player id
   */
  public int whatsTheId() {
    return id;
  }

  /**
   * Set player id.
   * @param id player id
   */
  public int changeTheId(int dd) {
    this.id = dd;
    return 0;
  }
}
