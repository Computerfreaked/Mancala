package nl.sogyo.mancala.domain;

import java.util.function.BooleanSupplier;

public class Player {
  private Player opponent;
  private boolean hasTurn;
  private Pit firstPit;
  private static boolean gameOn = true;

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

  public static boolean getGameOn() {
    return Player.gameOn;
  }

  public static void setGameOn(boolean gameOn) {
    Player.gameOn = gameOn;
  }


  public void switchTurn() {
    if(getAmountOfStonesInPits() == 0){
      Player.setGameOn(false);
    }

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
