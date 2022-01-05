package component;


import component.balls.RubberBall;
import component.bricks.CementBrick;
import component.bricks.ClayBrick;
import component.bricks.SteelBrick;
import component.paddle.Paddle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * The class {@code ComponentFactory} is used as a factory to create different components to {@link }
 *
 * <p>
 * @author
 * @version 1.0
 * @since 1.0
// * @see
 */
public class ComponentFactory {


	/**
     * This method implements the SINGLETON design pattern {@link Paddle}
     *
     * @return The instance of paddle
     */
    public static Paddle createPaddle(){
		// todo
        return null;
    }


	public static ClayBrick createClayBrick(Point point, Dimension size){
		return new ClayBrick(point, size);
	}

	public static SteelBrick createSteelBrick(Point point, Dimension size){
		return new SteelBrick(point, size);
	}

	public static CementBrick createCementBrick(Point point, Dimension size){
		return new CementBrick(point, size);
	}

	public static RubberBall createRubberBall(Point2D center){
		return new RubberBall(center);

	}


}
