package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
  @Test
  public void pit_PitWithAmount6_6PitsAreCreatedAndChainedButNotMore(){
    Pit pit1 = new Pit(6);

    assertTrue(pit1.getNextContainer(5) instanceof Pit, "Pit 5 was not or improperly created");
    assertFalse(pit1.getNextContainer(6) instanceof Pit, "Pit 6 should not exist or at least not be of type Pit");
  }

  @Test
  public void pit_PitWithAmount2_2PitsCreatedAndKalahaAttachedAndChained(){
    Pit pit1 = new Pit(2);

    assertTrue(pit1 instanceof Pit, "Pit creation error");
    assertTrue(pit1.getNextContainer(1) instanceof Pit, "Pit creation error");
    assertTrue(pit1.getNextContainer(2) instanceof Kalaha, "Kalaha was not attached to chain of Pits");
  }

  @Test
  public void pit_PitWithAmount6_AllPitsHave4Stones(){
    Pit pit1 = new Pit(6);

    for(int i = 1; i < 6; i++){
      assertEquals(4, pit1.getNextContainer(i).getAmountStones());
    }
  }
}
