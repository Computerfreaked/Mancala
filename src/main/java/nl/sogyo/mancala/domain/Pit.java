package nl.sogyo.mancala.domain;
public class Pit {
  private Pit nextPit;

  public Pit(){

  }

  public Pit(int amount){
    if(amount > 1){
      this.nextPit = new Pit(amount - 1);
    }
  }

  public void setNextPit(Pit nextPit){
    this.nextPit = nextPit;
  }

  public Pit getNextPit(int howOften){
    if(howOften > 1){
      return nextPit.getNextPit(howOften - 1);
    }
    return this.nextPit;
  }
}
