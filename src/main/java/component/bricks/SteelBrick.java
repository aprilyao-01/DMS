package component.bricks;//package code;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.GeneralPath;
import java.util.Random;


// renamed the class to match "clay=1, steel=2, cement=3"
/**
 * <h1>Class: {@link SteelBrick}</h1>
 * one type of brick, extends abstract class {@code Brick}
 *
 * @version 1.1
 * @since 1.0
 * @see CementBrick
 * @see ClayBrick
 * @see SteelBrick
 */
public class SteelBrick extends Brick {

	private static final String NAME = "Steel component.bricks.Brick";
	private static final Color DEF_INNER = new Color(203, 203, 201);
	private static final Color DEF_BORDER = DEF_INNER.darker().darker();		// original black doesn't look so harmonious
	private static final int STEEL_STRENGTH = 2;	// as steel, it should have middle strength
	private static final double STEEL_PROBABILITY = 0.4;

	private Random rnd;
	private Shape brickFace;
	private Crack crack;		// set cracks, make impact more visible to user

	public SteelBrick(Point point, Dimension size){
		super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
		rnd = new Random();
		crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
		brickFace = super.brickFace;
		m_score = 10;		// if Steel brick in broken, get 10 scores
	}


	@Override
	protected Shape makeBrickFace(Point pos, Dimension size) {
		return new Rectangle(pos,size);
	}

	@Override
	public Shape getBrick() {
		return brickFace;
	}

	private void updateBrick(){
		if(!super.isBroken()){
			GeneralPath gp = crack.draw();
			gp.append(super.brickFace,false);
			brickFace = gp;
		}
	}

	@Override
	public  boolean setImpact(Point2D point , int dir){
		if(super.isBroken())
			return false;
		super.impact();
		if(!super.isBroken()){
			crack.makeCrack(point,dir);
			updateBrick();
			return false;
		}
		return true;
	}

	public void impact(){
		if(rnd.nextDouble() < STEEL_PROBABILITY){
			super.impact();
		}
	}

	public void repair(){
		super.repair();
		crack.reset();
		brickFace = super.brickFace;
	}

}
