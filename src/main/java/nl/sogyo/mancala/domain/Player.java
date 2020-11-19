package nl.sogyo.mancala.domain;

public class Player {
  private Player opponent;
  private Boolean hasTurn;

  public Player(){
  }

  public Player getOpponent(){
    return this.opponent;
  }

  public Boolean getHasTurn(){
    return hasTurn;
  }
}
