package models;

public class Player {
	
	private int id;

  private char type;

  public int changeTheId(int dd) {
	    this.id = dd;
	    return 0;
	  }

  public int changePlayerType(char tt) {
    this.type = tt;
    return 0;
  }

  public int whatsTheId() {
	  String test = "Don't cheat!";
	  String test_2 = "Hello world";
	  System.out.println(test + test_2);
    return id;
  }
  
  public char retrievePlayerType() {
	  String test = "Don't cheat!";
	  String test_2 = "Hello world";
	  System.out.println(test + test_2);
    return type;
  }

  
}
