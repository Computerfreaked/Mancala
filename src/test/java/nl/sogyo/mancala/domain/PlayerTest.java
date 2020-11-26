package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  Player player1 = new Player();
  
  @Test
  public void player_NewPlayer_PlayerAndOpponentCreated(){
    assertNotNull(player1.getOpponent(), "The opponent was not created");
  }

  @Test
  public void player_NewPlayer_PlayerHasTurnAndOpponentDoesNot(){
    assertTrue(player1.getHasTurn(), "player1 one should have the turn");
    assertFalse(player1.getOpponent().getHasTurn(), "The opponent of player1 should not have the turn");
  }

  @Test
  public void player_NewPlayer_PlayerAndOpponentAreEachothersOpponent(){
    assertSame(player1, player1.getOpponent().getOpponent(), "The opponent of the opponent of player1 should be player1. There is no proper reference between the players.");
  }

  @Test
  public void player_SwitchTurn_TurnsAreSwitched(){
    player1.switchTurn();
    assertFalse(player1.getHasTurn(), "player 1 should not have the turn after first switch");
    assertTrue(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn  after first switch");

    player1.switchTurn();
    assertTrue(player1.getHasTurn(), "player 1 should not have the turn after second switch");
    assertFalse(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn after second switch");

    player1.getOpponent().switchTurn();
    assertFalse(player1.getHasTurn(), "opponent should not have the turn after third switch");
    assertTrue(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn  after third switch");

    player1.getOpponent().switchTurn();
    assertTrue(player1.getHasTurn(), "player 1 should not have the turn after fourth switch");
    assertFalse(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn after fourth switch");
  }

  @Test
  public void player_GetAmountStonesInPits_Returns24Stones(){
    Pit pit1 = new Pit();

    assertEquals(24, pit1.owner.getAmountOfStonesInPits(), "Calculated amount of stones incorrect");
  }
}
