package nl.sogyo.mancala.domain;
public class Pit extends Container{
  public Pit(){
    this.amountStones = 4;
    this.owner = new Player();
    
    this.nextContainer = new Pit(2, this.owner, true);
    this.getNextContainer(13).setNextContainer(this);

    this.setAllOpposites();
  }

  public Pit(int amount, Player player, boolean newsetup){
    this.amountStones = 4;

    if((amount + 1) % 14 == 0){
      this.owner = player.getOpponent();
      this.nextContainer = new Kalaha(amount + 1, player);
    }
    else if((amount + 1) % 7 == 0){
      this.owner = player;
      this.nextContainer = new Kalaha(amount + 1, player);
    }
    else if(amount + 1 < 7){
      this.owner = player;
      this.nextContainer = new Pit(amount + 1, player, true);
    }
    else if(amount + 1 < 14){
      this.owner = player.getOpponent();
      this.nextContainer = new Pit(amount + 1, player, true);
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
    Kalaha receivingKalaha = this.getNextContainer(1).findKalaha(this.getOwner().getOpponent());
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
