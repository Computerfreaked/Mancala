package nl.sogyo.mancala.domain;
public class Pit extends Container{
  public Pit(){
    super(new Player(), 1);
    this.amountStones = 4;
    
    attachOppositeContainer(1);
  }

  public Pit(int containerNumber, Player player){
    super(player, containerNumber);
    this.amountStones = 4;
  }

  public boolean play() {
    int amountStonesToPass = this.amountStones;

    if(this.amountStones == 0 || !this.owner.getHasTurn()){
      return false;
    }

    this.amountStones = 0;
    this.nextContainer.distributeStones(amountStonesToPass);
    this.owner.switchTurn();

    return true;
  }

  public void sendStonesToKalaha(int amountFromOtherPit) {
    Kalaha receivingKalaha = this.nextContainer.findKalaha(this.owner.getOpponent());
    receivingKalaha.receiveStones(this.amountStones + amountFromOtherPit);
    this.amountStones = 0;
  }

  public void distributeStones(int amountStonesReceived) {
    this.amountStones = this.amountStones + 1;

    if(amountStonesReceived > 1){
      this.nextContainer.distributeStones(amountStonesReceived - 1);
    }
    else if (this.amountStones == 1 && this.owner.getHasTurn()){
        Pit oppositePit = (Pit) this.opposite;
        oppositePit.sendStonesToKalaha(this.amountStones);
        this.amountStones = 0;
    }
  }
}
