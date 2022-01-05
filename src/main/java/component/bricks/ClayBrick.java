package component.bricks;//package code;

import java.awt.*;
import java.awt.Point;

// renamed the class to match "clay=1, steel=2, cement=3"
/**
 * <h1>Class: {@link ClayBrick}</h1>
 * one type of brick, extends abstract class {@code Brick}
 *
 * @version 1.1
 * @since 1.0
 * @see CementBrick
 * @see ClayBrick
 * @see SteelBrick
 */
public class ClayBrick extends Brick {

	private static final String NAME = "Clay component.bricks.Brick";
	private static final Color DEF_INNER = new Color(178, 34, 34).darker();
	private static final Color DEF_BORDER = Color.GRAY;
	private static final int CLAY_STRENGTH = 1;		// as clay, it should have the lowest strength




	public ClayBrick(Point point, Dimension size){
		super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
		m_score = 5;	// if clay brick in broken, get 5 scores
	}

	@Override
	protected Shape makeBrickFace(Point pos, Dimension size) {
		return new Rectangle(pos,size);
	}

	@Override
	public Shape getBrick() {
		return super.brickFace;
	}


}
