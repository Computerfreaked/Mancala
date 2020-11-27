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
  public void player_SwitchTurnOnceByPlayer_TurnsAreSwitched(){
    Pit pit1 = new Pit();
    player1 = pit1.getOwner();
    
    player1.switchTurn();

    assertFalse(player1.getHasTurn(), "player 1 should not have the turn after first switch");
    assertTrue(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn  after first switch");
  }

  @Test
  public void player_SwitchTurnTwiceByPlayerAndOpponent_TurnsAreSwitched(){
    Pit pit1 = new Pit();
    player1 = pit1.getOwner();

    player1.switchTurn();
    player1.getOpponent().switchTurn();

    assertTrue(player1.getHasTurn(), "player 1 should not have the turn after fourth switch");
    assertFalse(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn after fourth switch");
  }

  @Test
  public void player_GetAmountStonesInPits_Returns24Stones(){
    Pit pit1 = new Pit();

    assertEquals(24, pit1.owner.getAmountOfStonesInPits(), "Calculated amount of stones incorrect");
  }

  private void clearPlayerSide(Pit pit1){
    Pit pitToPlay = (Pit) pit1.getNextContainer(2);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(12);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(1);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(8);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(2);
    pitToPlay.play();
    
    pitToPlay = (Pit) pit1.getNextContainer(9);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(9);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(3);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(4);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(7);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(5);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(8);
    pitToPlay.play();

    pit1.play();
  }

  @Test
  public void player_SwitchTurnNoStonesInPitsAtBeginningOfTurn_SetPlayerGameOnFalse(){
    Pit pitToPlay;
    Pit pit1 = new Pit();

    clearPlayerSide(pit1);

    //after this move by opponent all players pits are empty and turn is handed over to player
    pitToPlay = (Pit) pit1.getNextContainer(7);
    pitToPlay.play();

    assertFalse(  Player.getGameOn(), 
                  "All pits of this player are empty at the beginning of his turn so he should have stopped the game."
    );
  }

  @Test
  public void player_SwitchTurnNoStonesInPitsAtEndOfTurn_SetPlayerGameOnFalse(){
    Pit pitToPlay;
    Pit pit1 = new Pit();

    clearPlayerSide(pit1);

    pitToPlay = (Pit) pit1.getNextContainer(9);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(4);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(8);
    pitToPlay.play();

    //after this move by player all players pits are empty and turn is handed over to opponent
    pitToPlay = (Pit) pit1.getNextContainer(5);
    pitToPlay.play();

    assertFalse(  Player.getGameOn(), 
                  "All pits of this player are empty at the end of his turn so he should have stopped the game."
    );
  }

  @Test
  public void player_GameEnds_ScoresCalculated(){
    Pit pitToPlay;
    Pit pit1 = new Pit();

    clearPlayerSide(pit1);

    pitToPlay = (Pit) pit1.getNextContainer(7);
    pitToPlay.play();

    //the game has ended
    
    assertEquals(pit1.getOwner().getScore(), pit1.getOwner().getAmountOfStonesInPits() + pit1.findKalaha(pit1.getOwner()).getAmountStones(), "Player score not correctly calculated after the game ended");
    assertEquals(pit1.getOwner().getOpponent().getScore(), pit1.getOwner().getOpponent().getAmountOfStonesInPits() + pit1.findKalaha(pit1.getOwner().getOpponent()).getAmountStones(), "Opponent score not correctly calculated after the game ended");
  }
}
