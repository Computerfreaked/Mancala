package nl.sogyo.mancala.domain;

public class Player {
  private final Player opponent;
  private boolean hasTurn;
  private Pit firstPit;
  private static boolean gameOn = true;
  private int score = 0;

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

  public int getScore() {
    return score;
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

    if(!gameOn){
      this.score = getAmountOfStonesInPits() + this.firstPit.findKalaha(this).getAmountStones();
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
