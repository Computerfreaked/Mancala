package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
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
}
