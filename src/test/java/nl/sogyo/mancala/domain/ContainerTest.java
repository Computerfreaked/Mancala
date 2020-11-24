package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContainerTest {
  private class ContainerStub extends Container{
    @Override
    public void distributeStones(int amountStones) {
      // not used
    }
  }

  ContainerStub container1 = new ContainerStub();
  ContainerStub container2 = new ContainerStub();
  ContainerStub container3 = new ContainerStub();

  @Test
  public void container_SetNextContainer_NextContainerIsUpdated(){
    container1.setNextContainer(container2);

    assertSame(container2, container1.getNextContainer(1), "Failed to set nextPit");
  }

  @Test
  public void container_GetNextContainerWithHowOften2_Container3IsReturned(){
    container1.setNextContainer(container2);
    container2.setNextContainer(container3);

    assertSame(container3, container1.getNextContainer(2), "Failed to get the proper pit");
  }

  @Test
  public void container_NewPlayer_AllPitsHaveAnOpposite(){
    Pit pit1 = new Pit();

    assertSame(pit1, pit1.getOppositeContainer().getOppositeContainer(), "Improper opposite on pit");
    for(int i = 1; i< 6; i++){
      assertSame( pit1.getNextContainer(i),
                  pit1.getNextContainer(i).getOppositeContainer().getOppositeContainer(),
                  "Improper opposite on pit"
      );
    }
  }
}
