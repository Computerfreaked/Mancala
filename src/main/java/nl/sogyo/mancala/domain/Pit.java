package nl.sogyo.mancala.domain;
public class Pit extends Container{
  private Pit nextContainer;

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

  public Container getNextContainer(int howOften){
    if(howOften > 1){
      return nextContainer.getNextContainer(howOften - 1);
    }
    return this.nextContainer;
  }
}
