package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContainerTest {
  private class ContainerExtender extends Container{
    @Override
    public void distributeStones(int amountStones) {
      // not used

    }
  }

  ContainerExtender container1 = new ContainerExtender();
  ContainerExtender container2 = new ContainerExtender();
  ContainerExtender container3 = new ContainerExtender();

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
    Player player1 = new Player();

    assertSame(player1.getFirstPit(), player1.getFirstPit().getOppositeContainer().getOppositeContainer(), "Improper opposite on pit");
    for(int i = 1; i< 6; i++){
      assertSame( player1.getFirstPit().getNextContainer(i),
                  player1.getFirstPit().getNextContainer(i).getOppositeContainer().getOppositeContainer(),
                  "Improper opposite on pit"
      );
    }
  }
}
