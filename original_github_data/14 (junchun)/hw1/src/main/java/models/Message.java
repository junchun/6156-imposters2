package models;

public class Message {

  private boolean moveValidity;

  private int code;

  private String message;

  /**
   * Get moveValidity.
   * @return moveValidity
   */
  public boolean getMoveValidity() {
    return moveValidity;
  }

  /**
   * set moveValidity.
   * @param moveValidity true if move is valid
   */
  public void setMoveValidity(boolean moveValidity) {
    this.moveValidity = moveValidity;
  }

  /**
   * Get HTTP code.
   * @return HTTP status code
   */
  public int getCode() {
    return code;
  }

  /**
   * Set HTTP code.
   * @param code HTTP status code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Get message.
   * @return message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Set message.
    * @param message message
    */
  public void setMessage(String message) {
    this.message = message;
  }
}
