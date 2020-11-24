package nl.sogyo.mancala.domain;

public class Kalaha extends Container{
  public Kalaha(){
    //default but just to be sure
    this.amountStones = 0;
  }

  public Kalaha(Player player) {
    this.owner = player;
  }

  public void distributeStones(int amountStonesReceived) {
    if(this.owner.getHasTurn() == true){
      this.amountStones = this.amountStones + 1;
      amountStonesReceived = amountStonesReceived - 1;
    }
    if(amountStonesReceived > 1){
      this.nextContainer.distributeStones(amountStonesReceived);
    }
  }
}
