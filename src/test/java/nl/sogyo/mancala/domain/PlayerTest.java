package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  @Test
  public void createPlayers(){
    Player player1 = new Player();

    assertNotNull(player1.opponent(), "The opponent was not created");
    assertTrue(player1.hasTurn(), "player1 one should have the turn");
    assertFalse(player1.opponent().hasTurn(), "The opponent of player1 should not have the turn");
    assertSame(player1, player1.opponent().opponent(), "The opponent of the opponent of player1 should be player1. There is no proper reference between the players.");
  }
}
