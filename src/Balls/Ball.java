package Balls;

import java.awt.Point;

public class Ball {
	private Point ball;
	private int direction;
	private int x_ini;
	private int y_ini;
	private int x;
	private int y;
	
	public Ball(int x, int y, int direction) {
		this.ball = new Point(x, y);
		this.x_ini = x;
		this.y_ini = y;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Point getBall() {
		return ball;
	}
	
	public void setBall(Point ball) {
		this.ball = ball;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getX_ini() {
		return x_ini;
	}

	public void setX_ini(int x_ini) {
		this.x_ini = x_ini;
	}

	public int getY_ini() {
		return y_ini;
	}

	public void setY_ini(int y_ini) {
		this.y_ini = y_ini;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	@Override
	public String toString() {
		return this.ball.toString();
	}

	public void translate(int dx, int dy) {
		this.ball.translate(dx, dy);
	}
}
