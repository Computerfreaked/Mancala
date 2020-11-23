package nl.sogyo.mancala.domain;
public class Pit extends Container{
  private Pit opposite;

  public Pit(){
    this.amountStones = 4;
  }

  public Pit(int amount){
    this();

    if(amount > 1){
      this.nextContainer = new Pit(amount - 1);
    }
    else{
      this.nextContainer = new Kalaha();
    }
  }

  public Pit(int amount, Player player) {
    this();
    this.owner = player;

    if(amount > 1){
      this.nextContainer = new Pit(amount - 1, player);
    }
    else{
      this.nextContainer = new Kalaha(player);
    }
  }

  public void setOppositePit(Pit opposite){
    this.opposite = opposite;
  }
}
