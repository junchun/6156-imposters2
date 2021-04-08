package models;

public class Message {

  private boolean moveValidity;

  private int code;

  private String message;
  
  /** Complete constructor.
   * @param valid whether move is valid or not
   * @param code return code
   * @param msg message to show up on the UI
   */
  public Message(boolean valid, int code, String msg) {
    this.moveValidity = valid;
    this.code = code;
    this.message = msg;
  }

  public boolean isMoveValidity() {
    return moveValidity;
  }

  public void setMoveValidity(boolean moveValidity) {
    this.moveValidity = moveValidity;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
