package nl.sogyo.mancala.domain;

public class Container {
  protected Container nextContainer;

  public Container getNextContainer(int howOften){
    if(howOften > 1){
      return nextContainer.getNextContainer(howOften - 1);
    }
    return this.nextContainer;
  }
}
