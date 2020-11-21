package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
  @Test
  public void pit_SetNextContainer_NextContainerIsUpdated(){
    Pit pit1 = new Pit();
    Pit pit2 = new Pit();

    pit1.setNextContainer(pit2);

    assertSame(pit2, pit1.getNextContainer(1), "Failed to set nextPit");
  }

  @Test
  public void pit_GetNextContainerWithHowOften2_Pit3IsReturned(){
    Pit pit1 = new Pit();
    Pit pit2 = new Pit();
    Pit pit3 = new Pit();

    pit1.setNextContainer(pit2);
    pit2.setNextContainer(pit3);

    assertSame(pit3, pit1.getNextContainer(2), "Failed to get the proper pit");
  }

  @Test
  public void pit_PitWithAmount6_6PitsAreCreatedAndChainedButNotMore(){
    Pit pit1 = new Pit(6);

    assertTrue(pit1.getNextContainer(5) instanceof Pit);
    assertFalse(pit1.getNextContainer(6) instanceof Pit);
  }

  @Test
  public void pit_PitWithAmount2_2PitsCreatedAndKalahaAttachedAndChained(){
    Pit pit1 = new Pit(2);

    assertTrue(pit1.getNextContainer(1) instanceof Pit);
    assertTrue(pit1.getNextContainer(2) instanceof Kalaha);
  }
}
