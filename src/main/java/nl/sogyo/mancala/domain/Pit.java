package nl.sogyo.mancala.domain;
public class Pit extends Container{
  public Pit(){
    this.amountStones = 4;
    this.owner = new Player();
    this.owner.setFirstPit(this);
    
    this.nextContainer = new Pit(2, this.owner);
    attachOppositePits(1);
  }

  public Pit(int containerNumber, Player player){
    this.amountStones = 4;

    if((containerNumber + 1) % 14 == 0){
      this.owner = player.getOpponent();
      this.nextContainer = new Kalaha(containerNumber + 1, player);
    }
    else if((containerNumber + 1) % 7 == 0){
      this.owner = player;
      this.nextContainer = new Kalaha(containerNumber + 1, player);
      attachOppositePits(containerNumber);
    }
    else if(containerNumber + 1 < 7){
      this.owner = player;
      this.nextContainer = new Pit(containerNumber + 1, player);
      attachOppositePits(containerNumber);
    }
    else if(containerNumber + 1 < 14){
      this.owner = player.getOpponent();
      if(containerNumber == 8){
        this.owner.setFirstPit(this);
      }
      this.nextContainer = new Pit(containerNumber + 1, player);
    }
    else{
      throw new UnsupportedOperationException();
    }
  }

  private void attachOppositePits(int amount){
    this.opposite = this.getNextContainer((7 - amount) * 2);
    this.opposite.setOppositeContainer(this);
  }

  public void play() {
    if(this.amountStones == 0 || !this.owner.getHasTurn()){
      return;
    }

    this.nextContainer.distributeStones(this.amountStones);
    this.amountStones = 0;
    this.owner.switchTurn();
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
