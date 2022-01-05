package component.paddle;

import component.balls.Ball;

import java.awt.*;

/**
 * <h1>Class: {@link Paddle}</h1>
 * class of paddle
 *
 *
 * @version 1.1
 * @since 1.0
 */
// ball control panel
public class Paddle {

	// color of the paddle
	public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
	public static final Color INNER_COLOR = Color.GREEN;


	// default move distance per press
	public static final int DEF_MOVE_AMOUNT = 5;

	private Rectangle paddleFace;
	private Point ballPoint;		// locate the ball on the paddle
	private int moveAmount;
	private int min;
	private int max;

	 /**
     * This is the constructor of Paddle
     *
     * @param ballPoint paddle's hit point
     * @param width paddle's width
     * @param height paddle's height
	 * @param container the paddle's container
     */
	public Paddle(Point ballPoint, int width, int height, Rectangle container) {
		this.ballPoint = ballPoint;
		moveAmount = 0;
		paddleFace = makeRectangle(width, height);
		min = container.x + (width / 2);
		max = min + container.width - width;

	}

	/**
     * create a Paddle use rectangle
     *
     * @param width paddle's width
     * @param height paddle's height
	 * @return a rectangle represent the paddle
     */
	public Rectangle makeRectangle(int width,int height){
		Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
		return  new Rectangle(p,new Dimension(width,height));
	}

	/**
     * if the paddle is catch the ball
     *
     * @param b the ball
	 * @return if the paddle is catch the ball
     */
	public boolean impact(Ball b){
		return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.getM_down()) ;
	}

	/**
     * how hte paddle move
     *
     */
	public void move(){
		double x = ballPoint.getX() + moveAmount;
		if(x < min || x > max)
			return;
		ballPoint.setLocation(x,ballPoint.getY());
		paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
	}

	/**
	 * if paddle move left, the move amount is negative
	 *
	 */
	public void moveLeft(){
		moveAmount = -DEF_MOVE_AMOUNT;
	}

	/**
	 * if paddle move right, the move amount is positive
	 *
	 */
	public void movRight(){
		moveAmount = DEF_MOVE_AMOUNT;
	}

	/**
	 * if paddle stop move, the move amount is 0
	 *
	 */
	public void stop(){
		moveAmount = 0;
	}

	public Shape getPaddleFace(){
		return paddleFace;
	}

	/**
	 * move ball to a certain position
	 * @param p move to the location p
	 *
	 */
	public void moveTo(Point p){
		ballPoint.setLocation(p);
		paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
	}
}