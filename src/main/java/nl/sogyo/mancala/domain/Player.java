package nl.sogyo.mancala.domain;

public class Player {
  private Player opponent;
  private boolean hasTurn;
  private Pit firstPit;

  public Player(){
    this.hasTurn = true;
    this.firstPit = new Pit(6, this);
    this.opponent = new Player(this);
    linkPitSeq(this.opponent.linkPitSeq(this.firstPit));
    this.firstPit.setAllOpposites();
  }

  public Player(Player opponent){
    this.hasTurn = false;
    this.firstPit = new Pit(6, this);
    this.opponent = opponent;
  }

  public Player getOpponent(){
    return this.opponent;
  }

  public boolean getHasTurn(){
    return this.hasTurn;
  }

  public Pit getFirstPit(){
    return this.firstPit;
  }

  public Pit linkPitSeq(Pit otherPlayerFirstPit){
    this.firstPit.getNextContainer(6).setNextContainer(otherPlayerFirstPit);
    return this.firstPit;
  }

  public void switchTurn() {
    if(this.hasTurn){
      this.hasTurn = false;
    }
    else {
      this.hasTurn = true;
    }

    if(this.hasTurn == this.opponent.getHasTurn()){
      this.opponent.switchTurn();
    }
  }
}
