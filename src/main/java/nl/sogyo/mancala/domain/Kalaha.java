package nl.sogyo.mancala.domain;

public class Kalaha extends Container{
  public Kalaha(){
    //default but just to be sure
    this.amountStones = 0;
  }

  public Kalaha(Player player) {
    this.owner = player;
  }
}
