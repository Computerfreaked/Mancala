package nl.sogyo.mancala.domain;

public abstract class Container {
  protected Container nextContainer;
  protected int amountStones;
  protected final Player owner;
  protected Container opposite;

  protected Container(Player owner){
    this.owner = owner;
  }

  public Container getNextContainer(int howOften){
    if(howOften > 1){
      return nextContainer.getNextContainer(howOften - 1);
    }
    return this.nextContainer;
  }

  public int getAmountStones() {
    return amountStones;
  }

  public Player getOwner() {
    return this.owner;
  }

  public Container getOppositeContainer(){
    return this.opposite;
  }

  public Kalaha findKalaha(Player owner){
    if(this instanceof Kalaha && this.owner == owner){
      return (Kalaha) this;
    }
    else {
      return this.getNextContainer(1).findKalaha(owner);
    }
  }

  public void attachOppositeContainer(int containerNumber){
    if (containerNumber < 7){
      this.opposite = this.getNextContainer((7 - containerNumber) * 2);
    }
    else if(containerNumber < 14 && containerNumber > 7){
      this.opposite = this.getNextContainer((14 - containerNumber) * 2);
    }
    else if (containerNumber == 14){
      return;
    }

    this.nextContainer.attachOppositeContainer(containerNumber +1);
  }

  public abstract void distributeStones(int amountStones);
}
