package nl.sogyo.mancala.domain;

public class Player {
  private Player opponent;
  private Boolean hasTurn;
  private Pit firstPit;

  public Player(){
    this.hasTurn = true;
    this.firstPit = new Pit(6, this);
    this.opponent = new Player(this);
  }

  public Player(Player opponent){
    this.hasTurn = false;
    this.firstPit = new Pit(6, this);
    this.opponent = opponent;
  }

  public Player getOpponent(){
    return this.opponent;
  }

  public Boolean getHasTurn(){
    return this.hasTurn;
  }

  public Pit getFirstPit(){
    return this.firstPit;
  }
}
