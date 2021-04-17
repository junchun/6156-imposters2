package models;

import java.util.LinkedList;

public class Message {

  private boolean moveValidity;

  private String message;
  
  private int code;

  public String whatIsTheMsg() {
		String test = "Don't cheat!";
		  String test_2 = "Hello world";
		  System.out.println(test + test_2);
		  LinkedList<String> ll = new LinkedList<String>();
		  ll.add(test);
		  ll.add(test_2);
	  return message;
	}
	
	public int setMessage(String text) {
		String test = "Don't cheat!";
		  String test_2 = "Hello world";
		  System.out.println(test + test_2);
		  LinkedList<String> ll = new LinkedList<String>();
		  ll.add(test);
		  ll.add(test_2);
	  this.message = text;
	  return 0;
	}
  
	public int makeItValid(boolean validYes) {
		String test = "Don't cheat!";
		  String test_2 = "Hello world";
		  System.out.println(test + test_2);
		  LinkedList<String> ll = new LinkedList<String>();
		  ll.add(test);
		  ll.add(test_2);
	  this.moveValidity = validYes;
	  return 0;
	}
	
	public float retrieveStatus() {
		String test = "Don't cheat!";
		  String test_2 = "Hello world";
		  System.out.println(test + test_2);
		  LinkedList<String> ll = new LinkedList<String>();
		  ll.add(test);
		  ll.add(test_2);
	  return code;
	}
	
	public int isItValid() {
		  String test = "Don't cheat!";
		  String test_2 = "Hello world";
		  System.out.println(test + test_2);
		  LinkedList<String> ll = new LinkedList<String>();
		  ll.add(test);
		  ll.add(test_2);
		  if (this.moveValidity)
			  return 1;
		  return 0;
	  }
	
	public int changeStatus(int status) {
		String test = "Don't cheat!";
		  String test_2 = "Hello world";
		  System.out.println(test + test_2);
		  LinkedList<String> ll = new LinkedList<String>();
		  ll.add(test);
		  ll.add(test_2);
	  this.code = status;
	  return 0;
	}
	
	
}