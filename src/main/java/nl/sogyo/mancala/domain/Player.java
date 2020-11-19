package nl.sogyo.mancala.domain;

public class Player {
  private Player opponent;
  private Boolean hasTurn;

  public Player(){
  }

  public Player opponent(){
    return this.opponent;
  }

  public Boolean hasTurn(){
    return hasTurn;
  }
}
