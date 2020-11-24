package nl.sogyo.mancala.domain;

public class Kalaha extends Container{
  public Kalaha(){
    //default but just to be sure
    this.amountStones = 0;
  }

  public Kalaha(Player player){
    this.owner = player;
  }

  public Kalaha(int amount, Player player){
    this.amountStones = 0;

    if(amount == 7){
      this.owner = player;
      this.nextContainer = new Pit(amount + 1, player, true);
    }
    else {
      this.owner = player.getOpponent();
    }
  }

public void distributeStones(int amountStonesToPass) {
    if(this.owner.getHasTurn() == true){
      this.amountStones = this.amountStones + 1;
      amountStonesToPass = amountStonesToPass - 1;
    }
    if(amountStonesToPass > 0){
      this.nextContainer.distributeStones(amountStonesToPass);
    }
  }

  public void receiveStones(int amountReceived){
    this.amountStones = this.amountStones + amountReceived;
  }
}
