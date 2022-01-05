package component.balls;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * <h1>Class: {@link Ball}</h1>
 * an abstract class of the ball, defined how ball move.
 *
 *
 * @version 1.1
 * @since 1.0
 * @see RubberBall
 */
abstract public class Ball {

	private Shape m_ballFace;

	private Point2D m_center;

	private Point2D m_up;
	private Point2D m_down;
	private Point2D m_left;
	private Point2D m_right;

	private Color m_border;
	private Color m_inner;

	// the original speed of the ball
	private int m_speedX;
	private int m_speedY;

	private String m_name;	// give ball a m_name


	 /**
     * This is the constructor of Ball
     *
     * @param m_name ball's type
     * @param m_center ball's m_center
     * @param radius ball's radius
	 * @param m_inner ball's m_inner color
     * @param m_border ball's m_border color
     */
	 // it's a ball, assume it's a perfect sphere, with radiusA = radiusB = radius
	public Ball(String m_name,Point2D m_center,int radius,Color m_inner,Color m_border){
		this.m_name = m_name;
		this.m_center = m_center;

		m_up = new Point2D.Double();
		m_down = new Point2D.Double();
		m_left = new Point2D.Double();
		m_right = new Point2D.Double();

		// more clear variable m_names, avoiding magic number
		double half_ball = radius / 2.0;

		m_up.setLocation(m_center.getX(),m_center.getY()-half_ball);
		m_down.setLocation(m_center.getX(),m_center.getY()+half_ball);

		m_left.setLocation(m_center.getX()-half_ball,m_center.getY());
		m_right.setLocation(m_center.getX()+half_ball,m_center.getY());


		m_ballFace = makeBall(m_center,radius);
		this.m_border = m_border;
		this.m_inner  = m_inner;
		m_speedX = 0;
		m_speedY = 0;
	}

	public Shape getM_ballFace() {return m_ballFace;}

	public void setM_ballFace(Shape m_ballFace) {this.m_ballFace = m_ballFace;}

	public Point2D getM_up() {return m_up;}

	public void setM_up(Point2D m_up) {this.m_up = m_up;}

	public Point2D getM_down() {return m_down;}

	public void setM_down(Point2D m_down) {this.m_down = m_down;}

	public Point2D getM_left() {return m_left;}

	public void setM_left(Point2D m_left) {this.m_left = m_left;}

	public Point2D getM_right() {return m_right;}

	public void setM_right(Point2D m_right) {this.m_right = m_right;}

	/**
	 * set the ball's speed
	 * @param x ball's X speed
	 * @param y ball's Y speed
	 *
	 */
	public void setSpeed(int x,int y){
		m_speedX = x;
		m_speedY = y;
	}

	/**
	 * set the ball's  X speed
	 * @param s ball's X speed
	 *
	 */
	public void setXSpeed(int s){m_speedX = s;}

	/**
	 * set the ball's  Y speed
	 * @param s ball's Y speed
	 *
	 */
	public void setYSpeed(int s){m_speedY = s;}

	/**
	 * when ball hit bricks or paddle set the ball's X speed  to reverse
	 *
	 */
	public void reverseX(){m_speedX *= -1;}		// Xspeed after bounce


	/**
	 * when ball hit bricks or paddle set the ball's Y speed  to reverse
	 *
	 */
	public void reverseY(){m_speedY *= -1;}		// Yspeed after bounce


	/**
	 * get the ball's m_border color
	 * @return m_border color
	 *
	 */
	public Color getBorderColor(){return m_border;}

	/**
	 * get the ball's m_inner color
	 * @return m_inner color
	 *
	 */
	public Color getInnerColor(){return m_inner;}

	/**
	 * get the ball's position
	 * @return ball's m_center
	 *
	 */
	public Point2D getPosition(){return m_center;}

	/**
	 * get the ball's face
	 * @return ball's face
	 *
	 */
	public Shape getBallFace(){return m_ballFace;}

	/**
	 * get the ball's X speed
	 * @return ball's x speed
	 *
	 */
	public int getSpeedX(){return m_speedX;}

	/**
	 * get the ball's Y speed
	 * @return ball's y speed
	 *
	 */
	public int getSpeedY(){return m_speedY;}

	/**
	 * This is the abstract class of Ball
	 *
	 * @param m_center ball's m_center
	 * @param radius ball's radius
	 * @return a shape of ball
	 */
	protected abstract Shape makeBall(Point2D m_center,int radius);


	/**
	 * define how ball move
	 *
	 */
	public void move(){
		RectangularShape tmp = (RectangularShape) m_ballFace;
		m_center.setLocation((m_center.getX() + m_speedX),(m_center.getY() + m_speedY));
		double w = tmp.getWidth();
		double h = tmp.getHeight();

		tmp.setFrame((m_center.getX() -(w / 2)),(m_center.getY() - (h / 2)),w,h);
		setPoints(w,h);


		m_ballFace = tmp;
	}

	/**
	 * move ball to a certain position
	 * @param p move to the location p
	 *
	 */
	public void moveTo(Point p){
		m_center.setLocation(p);

		RectangularShape tmp = (RectangularShape) m_ballFace;
		double w = tmp.getWidth();
		double h = tmp.getHeight();

		tmp.setFrame((m_center.getX() -(w / 2)),(m_center.getY() - (h / 2)),w,h);
		m_ballFace = tmp;
	}

	public void setPoints(double width,double height){
		m_up.setLocation(m_center.getX(),m_center.getY()-(height / 2));
		m_down.setLocation(m_center.getX(),m_center.getY()+(height / 2));

		m_left.setLocation(m_center.getX()-(width / 2),m_center.getY());
		m_right.setLocation(m_center.getX()+(width / 2),m_center.getY());
	}


}
