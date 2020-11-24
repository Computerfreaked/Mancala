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
  public void player_NewPlayer_ThePlayerAndOpponenHaveASequenceOfPitsAndAKalaha(){
    assertTrue(player1.getFirstPit() instanceof Pit, "Player does not have a sequence of containers");
    assertTrue(player1.getOpponent().getFirstPit() instanceof Pit, "Players opponent does not have sequence of containers");
  }

  @Test
  public void player_NewPlayer_OpponenIsInformedAboutFirstContainerAndUpdatesNextContainerOfKalahaReturnsOwnFirstContainerOwnKalahaNextContainerUpdated(){
    assertSame(player1.getFirstPit(), player1.getFirstPit().getNextContainer(14));
  }

  @Test
  public void player_SwitchTurn_TurnsAreSwitched(){
    player1.switchTurn();
    assertFalse(player1.getHasTurn(), "player 1 should not have the turn");
    assertTrue(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn");

    player1.getFirstPit().play();
    assertTrue(player1.getHasTurn(), "player 1 should not have the turn after second switch");
    assertFalse(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn after second switch");
  }
}
