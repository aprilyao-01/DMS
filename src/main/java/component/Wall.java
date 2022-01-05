package component;

import component.balls.Ball;
import component.balls.RubberBall;
import component.bricks.Brick;
import component.bricks.ClayBrick;
import component.bricks.SteelBrick;
import component.bricks.CementBrick;
import component.paddle.Paddle;
import setting.Settings;
import setting.SoundEffect;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * <h1>Class: {@link Wall}</h1>
 * this class define how brick become wall
 *
 *
 *
 * @version 1.2
 * @since 1.0
 */
public class Wall {

	/** totalGameLevels of the game, rename as: GAME_LEVELS_COUNT*/
	public static final int GAME_LEVELS_COUNT = 4;

	// different level of walls
	private static final int CLAY = 1;
	private static final int STEEL = 2;
	private static final int CEMENT = 3;

	private Random rnd;
	private Rectangle area;

	public Brick[] bricks;
	public Ball ball;
	public Paddle player;

	private Brick[][] totalGameLevels;
	private int currentLevel;

	private Point startPoint;
	private int brickCount;
	private int ballCount;
	private boolean ballLost;

	private int score;

	/** game begin, reset the score*/
	public void resetScore(){
		this.score = 0;
	}

	/** according to hit brick, add the player's score
	 * @param added the increase
	 * */
	public void addScore(int added){
		this.score = this.score + added;
	}

	/** get the score when game end
	 * @return  the score that player get
	 * */
	public int getScore() {
		return score;
	}

	public int getBrickCount() {
		return brickCount;
	}

	public int getBallCount(){
		return ballCount;
	}

	public int getCurrentLevel(){
		return currentLevel;
	}

	public boolean isBallLost(){
		return ballLost;
	}

	/** set the game board level
	 * @param currentLevel  the level the player play currently
	 * */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}


 	/**
     * This is the constructor of Ball
     *
     * @param drawArea the bricks' position
     * @param brickCount total bricks
     * @param lineCount total lines
	 * @param brickDimensionRatio bricks'type
     * @param ballPos ball's position
     */
	public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

		this.startPoint = new Point(ballPos);

		totalGameLevels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
		currentLevel = 0;

		ballCount = 3;
		ballLost = false;

		rnd = new Random();

		makeBall(ballPos);
		int speedX,speedY;

		// let system set the original ball speed randomly
		do{
			speedX = rnd.nextInt(8) - 2;
		}while(speedX == 0);
		do{
			speedY = -rnd.nextInt(5);
		}while(speedY == 0);

		ball.setSpeed(speedX,speedY);

		player = new Paddle((Point) ballPos.clone(),150,10, drawArea);

		area = drawArea;


	}

	/**
     * This is the constructor of Ball
     *
     * @param drawArea the bricks' position
     * @param brickCnt total bricks
     * @param lineCnt total lines
	 * @param brickSizeRatio each brick's size
     * @param type brick's type
	 * @return one line of brick
     */
	public Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
		/* if brickCount is not divisible by line count,
		brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount */
		brickCnt -= brickCnt % lineCnt;

		int brickOnLine = brickCnt / lineCnt;		// one line of bricks

		double brickLen = drawArea.getWidth() / brickOnLine;	// each brick's length in one line
		double brickHgt = brickLen / brickSizeRatio;			// each brick's height

		brickCnt += lineCnt / 2;

		Brick[] tmp  = new Brick[brickCnt];

		Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
		Point p = new Point();

		int i;
		for(i = 0; i < tmp.length; i++){
			int line = i / brickOnLine;
			if(line == lineCnt)
				break;
			double x = (i % brickOnLine) * brickLen;
			x =(line % 2 == 0) ? x : (x - (brickLen / 2));
			double y = (line) * brickHgt;
			p.setLocation(x,y);
			tmp[i] = makeBrick(p,brickSize,type);
		}

		for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
			double x = (brickOnLine * brickLen) - (brickLen / 2);
			p.setLocation(x,y);
			tmp[i] = new ClayBrick(p,brickSize);
		}
		return tmp;

	}

	/**
     * This is the constructor of Ball
     *
     * @param drawArea the bricks' position
     * @param brickCnt total bricks
     * @param lineCnt total lines
	 * @param brickSizeRatio each brick's size
     * @param typeA brick's type1
	 * @param typeB brick's type2
     */
	public Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
		/* if brickCount is not divisible by line count,
		brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount */
		brickCnt -= brickCnt % lineCnt;

		int brickOnLine = brickCnt / lineCnt;

		int centerLeft = brickOnLine / 2 - 1;
		int centerRight = brickOnLine / 2 + 1;

		double brickLen = drawArea.getWidth() / brickOnLine;
		double brickHgt = brickLen / brickSizeRatio;

		brickCnt += lineCnt / 2;

		Brick[] tmp  = new Brick[brickCnt];

		Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
		Point p = new Point();

		int i;
		for(i = 0; i < tmp.length; i++){
			int line = i / brickOnLine;
			if(line == lineCnt)
				break;
			int posX = i % brickOnLine;
			double x = posX * brickLen;
			x =(line % 2 == 0) ? x : (x - (brickLen / 2));
			double y = (line) * brickHgt;
			p.setLocation(x,y);

			boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
			tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
		}

		for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
			double x = (brickOnLine * brickLen) - (brickLen / 2);
			p.setLocation(x,y);
			tmp[i] = makeBrick(p,brickSize,typeA);
		}
		return tmp;
	}

	/**
     * This is the constructor of Ball
     *
     * @param ballPos a new boll
     */
	public void makeBall(Point2D ballPos){
		ball = new RubberBall(ballPos);
	}

	/**
     * This is the constructor of Ball
     *
     * @param drawArea the bricks' position
     * @param brickCount total bricks
     * @param lineCount total lines
	 * @param brickDimensionRatio each brick's size
	 * @return a board of bricks
     */
	public Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
		Brick[][] tmp = new Brick[GAME_LEVELS_COUNT][];
		tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
		tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL); // change the CEMENT to STEEL
		tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT); // change the STEEL to CEMENT
		tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
		return tmp;
	}

	/**
	 * define how ball and paddle move
	 *
	 */
	public void move(){
		player.move();
		ball.move();
	}


	/** find if the brick is impacted
	 * */
	public void findImpacts(){
		Settings settings = Settings.getCurrentSettings();
		if(player.impact(ball)){
			ball.reverseY();
			if (settings.isSoundEffect()) {
				SoundEffect.PADDLE_HIT.play();
			}
 		}
		else if(impactWall()){
			/* for efficiency reverse is done into method impactWall
			because for every brick program checks for horizontal and vertical impacts */
			brickCount--;
			if (settings.isSoundEffect()) {
				SoundEffect.BRICK_HIT.play();
			}
		}
		else if(impactBorder()) {
			ball.reverseX();
			if (settings.isSoundEffect()) {
				SoundEffect.BORDER_HIT.play();
			}
		}
		else if(ball.getPosition().getY() < area.getY()){
			ball.reverseY();
			if (settings.isSoundEffect()) {
				SoundEffect.BORDER_HIT.play();
			}
		}
		else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
			ballCount--;
			ballLost = true;
			if (settings.isSoundEffect()) {
				SoundEffect.LOSE.play();
			}
		}
	}

	/** find if the brick is impacted
	 * @return if the wall is impacted
	 * */
	public boolean impactWall(){
		for(Brick b : bricks){
			switch (b.findImpact(ball)) {
				//Vertical Impact
				case Brick.UP_IMPACT :{
					ball.reverseY();
					boolean broken = b.setImpact(ball.getM_down(), Brick.Crack.UP);
					if (broken) {
						addScore(b.getScore());
					}
					return broken;
				}
				case Brick.DOWN_IMPACT : {
					ball.reverseY();
					boolean broken = b.setImpact(ball.getM_up(), Brick.Crack.DOWN);
					if (broken) {
						addScore(b.getScore());
					}
					return broken;
				}

				//Horizontal Impact
				case Brick.LEFT_IMPACT : {
					ball.reverseX();
					boolean broken = b.setImpact(ball.getM_right(), Brick.Crack.RIGHT);
					if (broken) {
						addScore(b.getScore());
					}
					return broken;
				}
				case Brick.RIGHT_IMPACT :{
					ball.reverseX();
					boolean broken = b.setImpact(ball.getM_left(), Brick.Crack.LEFT);
					if (broken) {
						addScore(b.getScore());
					}
					return broken;
				}
			}
		}
		return false;
	}

	public boolean impactBorder(){
		Point2D p = ball.getPosition();
		return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
	}

	/** reset the ball */
	public void ballReset(){
		player.moveTo(startPoint);
		ball.moveTo(startPoint);
		int speedX,speedY;
		do{
			speedX = rnd.nextInt(5) - 2;
		}while(speedX == 0);
		do{
			speedY = -rnd.nextInt(3);
		}while(speedY == 0);

		ball.setSpeed(speedX,speedY);
		ballLost = false;
	}

	/** reset the wall */
	public void wallReset(){
		for(Brick b : bricks)
			b.repair();
		brickCount = bricks.length;
		ballCount = 3;
	}

	public boolean ballEnd(){
		return ballCount == 0;
	}

	public boolean isDone(){
		return brickCount == 0;
	}

	/** go to the next level */
	public void nextLevel(){
		if(hasLevel()){
			System.out.println(currentLevel);
			bricks = totalGameLevels[currentLevel++];
			this.brickCount = bricks.length;
		} else{
			System.out.println("WINNER\n");
		}
	}

	/** reset the ball */
	public boolean hasLevel(){
		return currentLevel < totalGameLevels.length;
	}

	public void setBallXSpeed(int s){
		ball.setXSpeed(s);
	}

	public void setBallYSpeed(int s){
		ball.setYSpeed(s);
	}

	/** reset the total ball number */
	public void resetBallCount(){
		ballCount = 3;
	}

	/** create the wall
	 * @param point the brick location
	 * @param size the brick's size
	 * @param type the brick's type
	 * @return the brick
	 * */
	public Brick makeBrick(Point point, Dimension size, int type){
		Brick out;
		 switch (type) {
			case CLAY :
				out = ComponentFactory.createClayBrick(point,size); break;
			case STEEL : out =  ComponentFactory.createSteelBrick(point,size);break;
			case CEMENT : out = ComponentFactory.createCementBrick(point,size);break;
			default :  throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
		};
		return  out;
	}

}
