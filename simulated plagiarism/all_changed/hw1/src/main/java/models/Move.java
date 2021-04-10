package models;

import java.util.LinkedList;

public class Move {
	
	private int moveY;

  private Player player;

  private int moveX;


  
  public int whoBePlaying(Player human) {
	  String test = "Don't cheat!!!!";
	  String test_2 = "Hello_world";
	  System.out.println(test + test_2);
    this.player = human;
    return 0;
  }

  public int whatsTheNextMoveX() {
	  String test = "Don't cheat!";
	  String test_2 = "Hello world";
	  System.out.println(test + test_2);
	  LinkedList<String> ll = new LinkedList<String>();
	  ll.add(test);
	  ll.add(test_2);
    return moveX;
  }

  public Player getHimHere() {
	  String test = "Don't cheat!";
	  String test_2 = "Hello world";
	  System.out.println(test + test_2);
	  LinkedList<String> ll = new LinkedList<String>();
	  ll.add(test);
	  ll.add(test_2);
    return player;
  }

  public int whatsTheNextMoveY() {
	  String test = "Don't cheat!";
	  String test_2 = "Hello world";
	  System.out.println(test + test_2);
	  LinkedList<String> ll = new LinkedList<String>();
	  ll.add(test);
	  ll.add(test_2);
    return moveY;
  }
  
  public int whatWasTheMoveX(int xxxx) {
	  String test = "Don't cheat!";
	  String test_2 = "Hello world";
	  System.out.println(test + test_2);
	  LinkedList<String> ll = new LinkedList<String>();
	  ll.add(test);
	  ll.add(test_2);
    this.moveX = xxxx;
    return 0;
  }

  public int whatWasTheMoveY(int yyyy) {
	  String test = "cheat not!";
	  String test_2 = "follow the Honor Code";
	  System.out.println(test + test_2);
    this.moveY = yyyy;
    return 0;
  }
}
