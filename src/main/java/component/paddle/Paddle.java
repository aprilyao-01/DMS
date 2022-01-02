package component.paddle;

import component.balls.Ball;

import java.awt.*;

/**
 * This method implements the SINGLETON design pattern
 *
 * */

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

	private static Paddle newPaddle = null;

	public static Paddle getPaddle(){
		if(Paddle.newPaddle == null)
//			Paddle.newPaddle = new Paddle();
			System.out.println("todo");
		return newPaddle;
	}



	public Paddle(Point ballPoint, int width, int height, Rectangle container) {
		this.ballPoint = ballPoint;
		moveAmount = 0;
		paddleFace = makeRectangle(width, height);
		min = container.x + (width / 2);
		max = min + container.width - width;

	}

	public Rectangle makeRectangle(int width,int height){
		Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
		return  new Rectangle(p,new Dimension(width,height));
	}

	public boolean impact(Ball b){
		return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.down) ;
	}

	public void move(){
		double x = ballPoint.getX() + moveAmount;
		if(x < min || x > max)
			return;
		ballPoint.setLocation(x,ballPoint.getY());
		paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
	}

	public void moveLeft(){
		moveAmount = -DEF_MOVE_AMOUNT;
	}

	public void movRight(){
		moveAmount = DEF_MOVE_AMOUNT;
	}

	public void stop(){
		moveAmount = 0;
	}

	public Shape getPaddleFace(){
		return paddleFace;
	}

	public void moveTo(Point p){
		ballPoint.setLocation(p);
		paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
	}
}