import java.awt.Point;

public class Balls {
  private Point[] Balls_init_tab;
  private Point[] Balls_tab;

  public Balls(Point[] tab){
      this.Balls_init_tab = new Point[tab.length];
      this.Balls_tab = new Point[tab.length];
      for (int i = 0; i < tab.length; i++){
        this.Balls_tab[i] = tab[i].getLocation();
        this.Balls_init_tab[i] = tab[i].getLocation();
      }
  }

  public void translater(int dx, int dy){
    for (Point Ball : this.Balls_tab){
      Ball.translate(dx, dy);
    }
  }

  public void reInit(){
    for (int i = 0; i < this.Balls_tab.length; i++){
      this.Balls_tab[i] = this.Balls_init_tab[i].getLocation();
    }
  }

  @Override
  public String toString(){
    String result = "";
    for (Point Ball : this.Balls_tab){
      result += Ball.toString() + " ";
    }
    return result;
  }

}
