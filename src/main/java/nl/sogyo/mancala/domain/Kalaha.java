package nl.sogyo.mancala.domain;

public class Kalaha extends Container{
  public Kalaha(int containerNumber, Player player){
    super(player);

    this.amountStones = 0;

    if(containerNumber == 7){
      this.nextContainer = new Pit(containerNumber + 1, player.getOpponent());
    }
    else {
      this.nextContainer = player.getOpponent().getFirstPit();
    }
  }

public void distributeStones(int amountStonesToPass) {
    if(this.owner.getHasTurn()){
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
