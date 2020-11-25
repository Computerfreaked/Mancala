package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class KalahaTest {
  @Test
  public void kalaha_PlayPassingOwnKalaha_StonesAcceptedByKalaha(){
    Pit pit1 = new Pit();

    Pit pitToPlay = (Pit) pit1.getNextContainer(3);
    pitToPlay.play();

    assertEquals(0, pitToPlay.getAmountStones(), "Played pit not empty");
    assertEquals(5, pitToPlay.getNextContainer(1).getAmountStones(), "Stones not properly distributed to pits");
    assertEquals(5, pitToPlay.getNextContainer(2).getAmountStones(), "Stones not properly distributed to pits");
    assertEquals(1, pitToPlay.getNextContainer(3).getAmountStones(), "Stones not properly distributed to Kalaha");
    assertEquals(5, pitToPlay.getNextContainer(4).getAmountStones(), "Stones not properly distributed to pits");
    assertEquals(4, pitToPlay.getNextContainer(5).getAmountStones(), "Too many stones distributed");
  }

  @Test
  public void kalaha_PlayPassingOpponentKalaha_StonesNotAcceptedByKalaha(){
    Pit pit1 = new Pit();

    Pit pitToPlay = (Pit) pit1.getNextContainer(12);
    pitToPlay.play();

    assertEquals(0, pitToPlay.getAmountStones(), "Played pit not empty");
    assertEquals(0, pitToPlay.getNextContainer(1).getAmountStones(), "Stones not properly distributed to Kalaha");
    for(int i = 2; i <6; i++){
      assertEquals(5, pitToPlay.getNextContainer(i).getAmountStones(), "Stones not properly distributed to pits");
    }
    assertEquals(4, pitToPlay.getNextContainer(6).getAmountStones(), "Too many stones distributed");
  }

  @Test
  public void kalaha_ReceiveStonesWithAmount5_KalahaHas5StonesMore(){
    Kalaha kalaha1 = new Kalaha();

    int amountBefore = kalaha1.getAmountStones();
    kalaha1.receiveStones(5);

    assertEquals(amountBefore + 5, kalaha1.getAmountStones(), "Kalaha did not accept the stones");
  }
}
