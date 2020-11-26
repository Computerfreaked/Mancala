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

    Pit pitToPlay = (Pit) pit1.getNextContainer(4);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(7);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(3);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(7);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(2);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(12);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(3);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(11);
    pitToPlay.play();

    pitToPlay = (Pit) pit1.getNextContainer(5);
    pitToPlay.play();

    assertEquals(2, pit1.findKalaha(pit1.getOwner().getOpponent()).getAmountStones(), "Stones not properly distributed to Kalaha");
  }

  @Test
  public void kalaha_ReceiveStonesWithAmount5_KalahaHas5StonesMore(){
    //7 is a kalaha, Player is not used in this test
    Kalaha kalaha1 = new Kalaha(7, new Player());

    int amountBefore = kalaha1.getAmountStones();
    kalaha1.receiveStones(5);

    assertEquals(amountBefore + 5, kalaha1.getAmountStones(), "Kalaha did not accept the stones");
  }
}
