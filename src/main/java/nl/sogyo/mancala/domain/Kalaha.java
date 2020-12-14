package nl.sogyo.mancala.domain;

public class Kalaha extends Container{
  public Kalaha(int containerNumber, Player player){
    super(player, containerNumber);

    this.amountStones = 0;
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
