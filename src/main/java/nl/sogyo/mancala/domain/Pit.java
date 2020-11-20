package nl.sogyo.mancala.domain;
public class Pit {
  private Pit nextPit;

  public Pit(){
    
  }

  public Pit(int amount){

  }

  public void setNextPit(Pit nextPit){
    this.nextPit = nextPit;
  }

  public Pit getNextPit(int howOften){
    if(howOften != 1){
      return nextPit.getNextPit(howOften - 1);
    }
    return this.nextPit;
  }
}
