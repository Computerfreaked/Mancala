package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class PitTest {
  @Test
  public void pit_SetNextPit_NextPitIsUpdated(){
    Pit pit1 = new Pit();
    Pit pit2 = new Pit();

    pit1.setNextPit(pit2);

    assertSame(pit2, pit1.getNextPit(1), "Failed to set nextPit");
  }

  @Test
  public void pit_GetNextPitWithHowOften2_Pit3IsReturned(){
    Pit pit1 = new Pit();
    Pit pit2 = new Pit();
    Pit pit3 = new Pit();

    pit1.setNextPit(pit2);
    pit2.setNextPit(pit3);

    assertSame(pit3, pit1.getNextPit(2), "Failed to get the proper pit");
  }
}
