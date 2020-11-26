package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
  Pit pit1 = new Pit();

  private void doGrossErrorCheck(Pit firstPit){
    int totalAmount = firstPit.getAmountStones();
    for (int i=1; i < 14; i++){
      totalAmount = totalAmount + firstPit.getNextContainer(i).getAmountStones();
    }
    assertEquals(48, totalAmount, "Incorrect number of total stones");
  }

  @Test
  public void pit_Pit_6Pits1Kalaha6pits1KalahaCreated(){
    assertTrue(pit1 instanceof Pit, "Error in creating first pit");
    int i;
    for(i=1; i <=5; i++){
      assertTrue(pit1.getNextContainer(i) instanceof Pit, "Error in creating first 6 pits");
    }
    assertTrue(pit1.getNextContainer(6) instanceof Kalaha, "First kalaha not created");
    for(i=7; i <=12; i++){
      assertTrue(pit1.getNextContainer(i) instanceof Pit, "Error in creating second 6 pits");
    }
    assertTrue(pit1.getNextContainer(13) instanceof Kalaha, "Error in creating second Kalaha");
  }

  @Test
  public void pit_Pit_LastKalahaIsLinkedToFirstPit(){
    assertSame(pit1, pit1.getNextContainer(14), "Last kalaha not linked to first Pit");
  }

  @Test
  public void pit_Pit_AllPitsHave4StonesAndKalahasHave0(){
    assertEquals(4, pit1.getAmountStones(), "First pit does not have 4 stones");
    int i;
    for(i=1; i <=5; i++){
      assertEquals(4, pit1.getNextContainer(i).getAmountStones(), "Not all of the first 6 pits have 4 stones");
    }
    assertEquals(0, pit1.getNextContainer(6).getAmountStones(), "First kalaha does not have 0 stones");
    for(i=7; i <=12; i++){
      assertEquals(4, pit1.getNextContainer(i).getAmountStones(), "Not all of the second 6 pits have 4 stones");
    }
    assertEquals(0, pit1.getNextContainer(13).getAmountStones(), "Second kalaha does not have 0 stones");

    doGrossErrorCheck(pit1);
  }

  @Test
  public void pit_Pit_First6PitsAndKalahaAreOwnedByPlayerRestByPlayerOpponent(){
    assertTrue(pit1.getOwner() instanceof Player, "No player assigned to pit1");
    int i;
    for(i=1; i <=5; i++){
      assertTrue(pit1.getNextContainer(i).getOwner() instanceof Player, "No player assigned to first sequence of containers");
    }
    assertTrue(pit1.getNextContainer(6).getOwner() instanceof Player, "No player assigned to first kalaha");
    for(i=7; i <=12; i++){
      assertSame(pit1.getOwner(), pit1.getNextContainer(i).getOwner().getOpponent(), "Second sequence of containers does not belong to opponent");
    }
    assertSame(pit1.getOwner(), pit1.getNextContainer(13).getOwner().getOpponent(), "Second kalaha does not belong to opponent");
  }

  @Test
  public void pit_Play_StonesDistributed(){
    pit1.play();

    assertEquals(0, pit1.getAmountStones(), "Played pit not empty");
    for(int i = 1; i <5; i++){
      assertEquals(5, pit1.getNextContainer(i).getAmountStones(), "Stones not properly distributed");
    }
    assertEquals(4, pit1.getNextContainer(5).getAmountStones(), "Too many stones distributed");
  }

  @Test
  public void pit_Play_AfterStonesDistributionSwitchPlayersHasTurn(){
    pit1.play();
    
    assertFalse(pit1.getOwner().getHasTurn(), "player 1 should not have the turn");
    assertTrue(pit1.getOwner().getOpponent().getHasTurn(), "opponent of player1 should have the turn");
  }

  @Test
  public void pit_PlaySomePitWith0Stones_PlayerTurnsShouldNotBeSwitched(){
    pit1.play();
    pit1.play();

    assertFalse(pit1.getOwner().getHasTurn(), "Turns should not have been switched");
  }

  @Test
  public void pit_PlayEndsOnOwnEmptyPit_PitStoneAndOppositeStonesToOwnKalaha(){
    Pit firstPitToPlay = (Pit) pit1.getNextContainer(7);
    Pit secondPitToPlay = (Pit) pit1.getNextContainer(2);
    firstPitToPlay.play();
    secondPitToPlay.play();

    Kalaha receivingKalaha = (Kalaha) pit1.getNextContainer(13);

    doGrossErrorCheck(pit1);

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
    Pit firstPitToPlay = pit1;
    Pit secondPitToPlay = (Pit) pit1.getNextContainer(10);
    firstPitToPlay.play();
    secondPitToPlay.play();

    Kalaha receivingKalaha = (Kalaha) pit1.getNextContainer(13);

    doGrossErrorCheck(pit1);

    assertEquals(1, firstPitToPlay.getAmountStones(), "Stones were incorrectly taken from first Pit");
    assertEquals(1, receivingKalaha.getAmountStones(), "Some stones were moved to Kalaha and they should not");
  }

  @Test
  public void pit_SendStonesToKalaha_ReceivedAmountAndOwnAmountSentToKalaha(){
    Pit instructedPit = (Pit) pit1.getNextContainer(12);
    instructedPit.sendStonesToKalaha(2);

    assertEquals(0, instructedPit.getAmountStones(), "Instrcuted pit to send stones should be empty");
    assertEquals(6, pit1.getNextContainer(6).getAmountStones(), "Not all stones ended up in the Kalaha");
  }

  @Test
  public void pit_PitFirstPitOfEachPlayerSendsItsRefToPlayer_PlayersFirstPitRefUpdated(){
    assertSame(pit1, pit1.getOwner().getFirstPit(), "Player was not correctly informed about his first pit");
    assertSame(pit1.getNextContainer(7), pit1.getNextContainer(7).getOwner().getFirstPit(), "Player opponent was not correctly informed about his first pit");
  }
}
