package nl.sogyo.mancala.domain;

public class Player {
  private Player opponent;
  private Boolean hasTurn;

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

  public Boolean getHasTurn(){
    return this.hasTurn;
  }

  public Pit getFirstPit(){
    return null;
  }
}
