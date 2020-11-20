package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
  @Test
  public void pit_SetNextPit_NextPitIsUpdated(){
    Pit pit1 = new Pit();
    Pit pit2 = new Pit();

    pit1.setNextPit(pit2);

    assertSame(pit2, pit1.getNextPit(), "Failed to set nextPit");
  }

  @Test
  public void pit_GetNextPit_NextPitIsReturned(){
    Pit pit1 = new Pit();
    Pit pit2 = new Pit();

    pit1.setNextPit(pit2);

    assertSame(pit2, pit1.getNextPit(), "Failed to get nextPit");
  }
}
