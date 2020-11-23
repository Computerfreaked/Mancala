package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  @Test
  public void Player_NewPlayer_PlayerAndOpponentCreated(){
    Player player1 = new Player();

    assertNotNull(player1.getOpponent(), "The opponent was not created");
  }

  @Test
  public void Player_NewPlayer_PlayerHasTurnAndOpponentDoesNot(){
    Player player1 = new Player();

    assertTrue(player1.getHasTurn(), "player1 one should have the turn");
    assertFalse(player1.getOpponent().getHasTurn(), "The opponent of player1 should not have the turn");
  }

  @Test
  public void Player_NewPlayer_PlayerAndOpponentAreEachothersOpponent(){
    Player player1 = new Player();

    assertSame(player1, player1.getOpponent().getOpponent(), "The opponent of the opponent of player1 should be player1. There is no proper reference between the players.");
  }

  @Test
  public void Player_NewPlayer_ThePlayerAndOpponenHaveASequenceOfPitsAndAKalaha(){
    Player player1 = new Player();

    assertTrue(player1.getFirstPit() instanceof Pit, "Player does not have a sequence of containers");
    assertTrue(player1.getOpponent().getFirstPit() instanceof Pit, "Players opponent does not have sequence of containers");
  }

  @Test
  public void Player_NewPlayer_OpponenIsInformedAboutFirstContainerAndUpdatesNextContainerOfKalahaReturnsOwnFirstContainerOwnKalahaNextContainerUpdated(){
    Player player1 = new Player();

    assertSame(player1.getFirstPit(), player1.getFirstPit().getNextContainer(14));
  }
}
