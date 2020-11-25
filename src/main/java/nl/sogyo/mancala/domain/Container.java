package nl.sogyo.mancala.domain;

public abstract class Container {
  protected Container nextContainer;
  protected int amountStones;
  protected Player owner;
  protected Container opposite;

  public Container getNextContainer(int howOften){
    if(howOften > 1){
      return nextContainer.getNextContainer(howOften - 1);
    }
    return this.nextContainer;
  }

  public void setNextContainer(Container nextPit){
    this.nextContainer = nextPit;
  }

  public int getAmountStones() {
    return amountStones;
  }

  public Player getOwner() {
    return this.owner;
  }

  public void setOppositeContainer(Container opposite){
    this.opposite = opposite;
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

  public abstract void distributeStones(int amountStones);
}
