package nl.sogyo.mancala.domain;
public class Pit extends Container{
  public Pit(){

  }

  public Pit(int amount){
    if(amount > 1){
      this.nextContainer = new Pit(amount - 1);
    }
  }

  public void setNextContainer(Pit nextPit){
    this.nextContainer = nextPit;
  }
}
