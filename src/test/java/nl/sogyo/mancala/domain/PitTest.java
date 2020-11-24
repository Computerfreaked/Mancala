package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
  private void doGrossErrorCheck(Player player1){
    int totalAmount = player1.getFirstPit().getAmountStones();
    for (int i=1; i < 14; i++){
      totalAmount = totalAmount + player1.getFirstPit().getNextContainer(i).getAmountStones();
    }
    assertEquals(48, totalAmount, "Incorrect number of total stones");
  }

  @Test
  public void pit_PitWithAmount6_6PitsAreCreatedAndChainedButNotMore(){
    Pit pit1 = new Pit(6);

    assertTrue(pit1 instanceof Pit, "A Pit was not created or improperly linked");
    for(int i=1; i<6; i++){
      assertTrue(pit1.getNextContainer(i) instanceof Pit, "A Pit was not created or improperly linked");
    }
    assertFalse(pit1.getNextContainer(6) instanceof Pit, "Pit 6 should not exist or at least not be of type Pit");
  }

  @Test
  public void pit_PitWithAmount2_2PitsCreatedAndKalahaAttachedAndChained(){
    Pit pit1 = new Pit(2);

    assertTrue(pit1 instanceof Pit, "Pit creation error");
    assertTrue(pit1.getNextContainer(1) instanceof Pit, "Pit creation error or improper linking");
    assertTrue(pit1.getNextContainer(2) instanceof Kalaha, "Kalaha was not attached to chain of Pits");
  }

  @Test
  public void pit_PitWithAmount6_AllPitsHave4Stones(){
    Pit pit1 = new Pit(6);

    assertEquals(4, pit1.getAmountStones(), "Not all pits have 4 stones when created");
    for(int i = 1; i < 6; i++){
      assertEquals(4, pit1.getNextContainer(i).getAmountStones(), "Not all pits have 4 stones when created");
    }
  }

  @Test
  public void pit_PitWithAmount6AndOwnerPlayer1_AllPitsAndKalahaHaveOwnerPlayer1(){
    Player player1 = new Player();

    Pit pit1 = new Pit(6, player1);

    assertEquals(player1, pit1.getOwner(), "Owner of a pit is not properly set");
    for(int i = 1; i < 7; i++){
      assertEquals(player1, pit1.getNextContainer(i).getOwner(), "Owner of a pit is not properly set");
    }
  }

  @Test
  public void pit_Play_StonesDistributed(){
    Player player1 = new Player();

    player1.getFirstPit().play();

    assertEquals(0, player1.getFirstPit().getAmountStones(), "Played pit not empty");
    for(int i = 1; i <5; i++){
      assertEquals(5, player1.getFirstPit().getNextContainer(i).getAmountStones(), "Stones not properly distributed");
    }
    assertEquals(4, player1.getFirstPit().getNextContainer(5).getAmountStones(), "Too many stones distributed");
  }

  @Test
  public void pit_Play_AfterStonesDistributionSwitchHasTurn(){
    Player player1 = new Player();

    player1.getFirstPit().play();
    
    assertFalse(player1.getHasTurn(), "player 1 should not have the turn");
    assertTrue(player1.getOpponent().getHasTurn(), "opponent of player1 should have the turn");
  }

  @Test
  public void pit_PlaySomePitWith0Stones_PlayerTurnsShouldNotBeSwitched(){
    Player player1 = new Player();

    player1.getFirstPit().play();
    player1.getFirstPit().play();

    assertFalse(player1.getHasTurn(), "Turns should not have been switched");
  }

  @Test
  public void pit_PlayEndsOnOwnEmptyPit_PitStoneAndOppositeStonesToOwnKalaha(){
    Player player1 = new Player();

    Pit firstPitToPlay = player1.getOpponent().getFirstPit();
    Pit secondPitToPlay = (Pit) player1.getFirstPit().getNextContainer(2);
    firstPitToPlay.play();
    secondPitToPlay.play();

    Kalaha receivingKalaha = (Kalaha) player1.getOpponent().getFirstPit().getNextContainer(6);

    doGrossErrorCheck(player1);

    assertEquals( 0,
                  firstPitToPlay.getAmountStones(),
                 "The turn ended on own pit and it was previsouly empty. The stone should have been moved to Kalaha"
    );
    assertEquals( 0,
                  firstPitToPlay.getOppositeContainer().getAmountStones(),
                  "The turn ended on own pit and it was previsouly empty. The stones in this opposite pit should have been moved to Kalaha"
    );
    assertEquals(6, receivingKalaha.getAmountStones(), "Stones were not moved to Kalaha");
  }

  @Test
  public void pit_PlayEndsOnOpponentEmptyPit_NoExtraStonesAreMovedToKalaha(){
    Player player1 = new Player();

    Pit firstPitToPlay = player1.getFirstPit();
    Pit secondPitToPlay = (Pit) player1.getOpponent().getFirstPit().getNextContainer(3);
    firstPitToPlay.play();
    secondPitToPlay.play();

    Kalaha receivingKalaha = (Kalaha) player1.getOpponent().getFirstPit().getNextContainer(6);

    doGrossErrorCheck(player1);

    assertEquals(1, firstPitToPlay.getAmountStones(), "Stones were incorrectly taken from first Pit");
    assertEquals(1, receivingKalaha.getAmountStones(), "Some stones were moved to Kalaha and they should not");
  }

  @Test
  public void pit_sendStonesToKalaha_ReceivedAmountAndOwnAmountSentToKalaha(){
    Player player1 = new Player();

    Pit instructedPit = (Pit) player1.getFirstPit().getOppositeContainer();
    instructedPit.sendStonesToKalaha(2);

    assertEquals(0, instructedPit.getAmountStones(), "Instrcuted pit to send stones should be empty");
    assertEquals(6, player1.getFirstPit().getNextContainer(6).getAmountStones(), "Not all stones ended up in the Kalaha");
  }
}
