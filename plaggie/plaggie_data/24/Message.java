package models;

import java.io.IOException;

public class Message {
  
  /**
   *  Primary Constructor for Message().
   */
  public Message(boolean moveValidity, int code, String message) {

    // TODO Auto-generated constructor stub
    this.moveValidity = moveValidity;
    this.code = code;
    this.message = message;
  }

  private boolean moveValidity;

  private int code;

  private String message;

}
