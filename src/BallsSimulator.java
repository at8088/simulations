public class BallsSimulator implements Simulable {
  private Balls Balls;

  // public BallsSimulator(){
  //   Point[] tab = new Point[3];
  //   tab[0] = new Point(0, 100);
  //   tab[1] = new Point(100, 100);
  //   tab[2] = new Point(200, 100);
  //   this.Balls = new Balls(tab);
  // }

  @Override
  public void next(){
    Balls.translater(10, 10);
  }

  @Override
  public void restart(){
    Balls.reInit();
  }

}
