package component;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * <h1>View</h1>
 *
 * <P>This class is refactored from original {@code Ball} {@code panddle} class and is used as the parent class for all
 * game elements.
 *
 *
 * @author
 * @version 1.0
 * @since 1.0
 */
abstract public class View {

	public abstract void move();

	public abstract void moveTo(Point p);
}
