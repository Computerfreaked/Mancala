package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContainerTest {
  @Test
  public void container_NewPit_AllPitsHaveAnOpposite(){
    Pit pit1 = new Pit();

    assertSame(pit1, pit1.getOppositeContainer().getOppositeContainer(), "Improper opposite on pit");
    for(int i = 1; i< 6; i++){
      assertSame( pit1.getNextContainer(i),
                  pit1.getNextContainer(i).getOppositeContainer().getOppositeContainer(),
                  "Improper opposite on pit"
      );
    }
  }

  @Test
  public void container_FindKalahaWithOpponent_KalahaFound(){
    Pit pit1 = new Pit();
    
    assertSame( pit1.getNextContainer(13),
                pit1.findKalaha(pit1.getOwner().getOpponent()),
                "Incorrect or no Kalaha found"
    );
  }
}
