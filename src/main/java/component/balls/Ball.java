package component.balls;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

abstract public class Ball {

	private Shape ballFace;

	private Point2D center;

	Point2D up;
	Point2D down;
	Point2D left;
	Point2D right;

	private Color border;
	private Color inner;

	// the original speed of the ball
	private int speedX;
	private int speedY;

	private String name;	// give ball a name

	// it's a ball, assume it's a perfect sphere, with radiusA = radiusB = radius
	public Ball(String name,Point2D center,int radius,Color inner,Color border){
		this.name = name;
		this.center = center;

		up = new Point2D.Double();
		down = new Point2D.Double();
		left = new Point2D.Double();
		right = new Point2D.Double();

		// more clear variable names, avoiding magic number
		double half_ball = radius / 2.0;

		up.setLocation(center.getX(),center.getY()-half_ball);
		down.setLocation(center.getX(),center.getY()+half_ball);

		left.setLocation(center.getX()-half_ball,center.getY());
		right.setLocation(center.getX()+half_ball,center.getY());


		ballFace = makeBall(center,radius);
		this.border = border;
		this.inner  = inner;
		speedX = 0;
		speedY = 0;
	}

	protected abstract Shape makeBall(Point2D center,int radius);

	public void move(){
		RectangularShape tmp = (RectangularShape) ballFace;
		center.setLocation((center.getX() + speedX),(center.getY() + speedY));
		double w = tmp.getWidth();
		double h = tmp.getHeight();

		tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
		setPoints(w,h);


		ballFace = tmp;
	}

	public void setSpeed(int x,int y){
		speedX = x;
		speedY = y;
	}

	public void setXSpeed(int s){
		speedX = s;
	}

	public void setYSpeed(int s){
		speedY = s;
	}

	public void reverseX(){
		speedX *= -1;
	}		// Xspeed after bounce

	public void reverseY(){
		speedY *= -1;
	}		// Yspeed after bounce

	public Color getBorderColor(){
		return border;
	}

	public Color getInnerColor(){
		return inner;
	}

	public Point2D getPosition(){
		return center;
	}

	public Shape getBallFace(){
		return ballFace;
	}

	public void moveTo(Point p){
		center.setLocation(p);

		RectangularShape tmp = (RectangularShape) ballFace;
		double w = tmp.getWidth();
		double h = tmp.getHeight();

		tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
		ballFace = tmp;
	}

	public void setPoints(double width,double height){
		up.setLocation(center.getX(),center.getY()-(height / 2));
		down.setLocation(center.getX(),center.getY()+(height / 2));

		left.setLocation(center.getX()-(width / 2),center.getY());
		right.setLocation(center.getX()+(width / 2),center.getY());
	}

	public int getSpeedX(){
		return speedX;
	}

	public int getSpeedY(){
		return speedY;
	}


}
