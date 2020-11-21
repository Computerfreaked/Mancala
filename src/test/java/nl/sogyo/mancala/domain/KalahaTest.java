package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class KalahaTest {
  @Test
  public void kalaha_NewKalaha_getAmountStones0(){
    Kalaha kalaha1 = new Kalaha();

    assertEquals(0, kalaha1.getAmountStones(), "The amount of stones for a new kalaha is not 0");
  }
}
