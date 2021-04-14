package models;

public class Message {

  private boolean moveValidity;

  private int code;

  private String message;

  public void setMoveValidity(boolean moveValidity) {
    this.moveValidity = moveValidity;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean getMoveValidity() {
    return moveValidity;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
