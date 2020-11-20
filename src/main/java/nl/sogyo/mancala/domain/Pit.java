package nl.sogyo.mancala.domain;
public class Pit {
  public Pit nextPit;

  public void setNextPit(Pit nextPit){
    this.nextPit = nextPit;
  }

  public Pit getNextPit(){
    return this.nextPit;
  }
}
