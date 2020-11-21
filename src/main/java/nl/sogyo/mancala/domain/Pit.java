package nl.sogyo.mancala.domain;
public class Pit extends Container{
  public Pit(){

  }

  public Pit(int amount){
    this.amountStones = 4;

    if(amount > 1){
      this.nextContainer = new Pit(amount - 1);
    }
    else{
      this.nextContainer = new Kalaha();
    }
  }
}
