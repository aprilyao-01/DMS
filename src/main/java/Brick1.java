//package code;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

// renamed the class to match "clay=1, steel=2, cement=3"
public class Brick1 extends Brick {

	private static final String NAME = "Clay Brick";
	private static final Color DEF_INNER = new Color(178, 34, 34).darker();
	private static final Color DEF_BORDER = Color.GRAY;
	private static final int CLAY_STRENGTH = 1;		// as clay, it should have the lowest strength






	public Brick1(Point point, Dimension size){
		super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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
