package nl.sogyo.mancala.domain;

public class Player {
  private Player opponent;
  private boolean hasTurn;
  private Pit firstPit;

  public Player(){
    this.hasTurn = true;
    this.opponent = new Player(this);
  }

  public Player(Player opponent){
    this.hasTurn = false;
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

  public void setFirstPit(Pit pit) {
    this.firstPit = pit;
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

  public int getAmountOfStonesInPits(){
    int amount = this.firstPit.getAmountStones();
    for(int i = 1; i <= 5; i++){
      amount = amount + this.firstPit.getNextContainer(i).getAmountStones();
    }

    return amount;
  }
}
