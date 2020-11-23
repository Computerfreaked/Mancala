package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContainerTest {
  @Test
  public void container_SetNextContainer_NextContainerIsUpdated(){
    Container container1 = new Container();
    Container container2 = new Container();

    container1.setNextContainer(container2);

    assertSame(container2, container1.getNextContainer(1), "Failed to set nextPit");
  }

  @Test
  public void container_GetNextContainerWithHowOften2_Container3IsReturned(){
    Container container1 = new Container();
    Container container2 = new Container();
    Container container3 = new Container();

    container1.setNextContainer(container2);
    container2.setNextContainer(container3);

    assertSame(container3, container1.getNextContainer(2), "Failed to get the proper pit");
  }
}
