package models;

public class Message {

  private boolean moveValidity;

  private int code;

  private String message;

  /**
   * Constructor for the Message class.
   * 
   * @param moveValidity indicating whether move was valid or not.
   * @param code to identify types of errors from one another.
   * @param message describing move
   */
  public Message(boolean moveValidity, int code, String message) {
    this.moveValidity = moveValidity;
    this.code = code;
    this.message = message;
  }

  /**
   * Returns boolean indicating the moveValidity.
   * 
   * @return the moveValidity
   */
  public boolean isMoveValidity() {
    return moveValidity;
  }

  /**
   * Sets moveValidity. 
   * 
   * @param moveValidity the moveValidity to set
   */
  public void setMoveValidity(boolean moveValidity) {
    this.moveValidity = moveValidity;
  }

  /**
   * Returns code of message.
   * 
   * @return the code
   */
  public int getCode() {
    return code;
  }

  /**
   * Sets code of message. 
   * 
   * @param code the code to set
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets string message within Message object.
   * 
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets message string.
   * 
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

}
