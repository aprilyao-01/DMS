package component.balls;//package code;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class RubberBall extends Ball {


	private static final String NAME = "Rubber component.balls.Ball";
	private static final int DEF_RADIUS = 10;
	private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
	private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

	// radiusA = radiusB
	public RubberBall(Point2D center){
		super(NAME,center,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
	}


	@Override
	protected Shape makeBall(Point2D center, int radius) {

		double half_ball = radius / 2.0;
		double x = center.getX() - half_ball;
		double y = center.getY() - half_ball;

		return new Ellipse2D.Double(x,y,radius,radius);
	}
}
