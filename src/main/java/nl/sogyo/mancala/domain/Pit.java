package nl.sogyo.mancala.domain;
public class Pit extends Container{
  public Pit(){
    this.amountStones = 4;
    
    this.nextContainer = new Pit(2, true);
    this.getNextContainer(13).setNextContainer(this);
  }

  public Pit(int amount, boolean newsetup){
    this.amountStones = 4;

    if((amount + 1) % 7 == 0){
      this.nextContainer = new Kalaha(amount + 1);
    }
    else if(amount + 1 < 7){
      this.nextContainer = new Pit(amount + 1, true);
    }
    else if(amount + 1 < 14){
      this.nextContainer = new Pit(amount + 1, true);
    }
  }

  public Pit(int amount){
    this.amountStones = 4;

    if(amount > 1){
      this.nextContainer = new Pit(amount - 1);
    }
    else{
      this.nextContainer = new Kalaha();
    }
  }

  public Pit(int amount, Player player) {
    this.amountStones = 4;
    this.owner = player;

    if(amount > 1){
      this.nextContainer = new Pit(amount - 1, player);
    }
    else{
      this.nextContainer = new Kalaha(player);
    }
  }

  public void play() {
    if(this.amountStones == 0){
      return;
    }

    this.nextContainer.distributeStones(this.amountStones);
    this.amountStones = 0;
    this.owner.switchTurn();
  }

  public void sendStonesToKalaha(int amountFromOtherPit) {
    Kalaha receivingKalaha = (Kalaha) this.owner.getOpponent().getFirstPit().getNextContainer(6);
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
