package controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import kong.unirest.HttpResponse;
import kong.unirest.*;
import kong.unirest.json.JSONObject;
import models.GameBoard;
import com.google.gson.Gson;
import models.Player;
import models.Message;


@TestMethodOrder(OrderAnnotation.class) 
public class PlayGameTest {
	
  /**
   * Runs only once before the testing starts.
  */
  @BeforeAll
  public static void init() {
    // Start Server
    PlayGame.main(null);
    System.out.println("Before All");
  }
	
  /**
  * This method starts a new game before every test run. It will run every time before a test.
  */
  
  @AfterAll
  public static void close() {
    // Stop Server
    PlayGame.stop();
    System.out.println("After All");
  }
}

