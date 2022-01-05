package game;

import component.Wall;
import component.balls.Ball;
import component.bricks.Brick;
import component.paddle.Paddle;
import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import setting.DataManager;
import setting.Settings;
import setting.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import debug.DebugConsole;

/**
 * <h1>Class: {@link GameBoard}</h1>
 * the dialog use to debug
 *
 * @version 1.3
 * @since 1.0
 */
public class GameBoard extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0, 255, 0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 500;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;        // todo: Timer not exactly used

    private Wall wall;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

	private DebugConsole debugConsole;


    /**
     * This is the constructor of GameBoard
     *
     * @param level current level of the game
     */
    public GameBoard(int level) {
        super();

        strLen = 0;
        showPauseMenu = false;


        menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);


        this.initialize();
        message = "Press SPACE to start";    // message show on screen, user guide
        wall = new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT),
                30, 3, 6 / 2, new Point(300, 430));

        wall.setCurrentLevel(level);
		debugConsole = new DebugConsole(wall,this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10, e -> {
            wall.move();
            wall.findImpacts();
            // add level message
            message = String.format("Level: %d %n Bricks: %d Balls: %d Scores: %d",
                    wall.getCurrentLevel(), wall.getBrickCount(), wall.getBallCount(), wall.getScore());
            if (wall.isBallLost()) {        // all boll used
                if (wall.ballEnd()) {
                    wall.wallReset();
                    message = "Game over";
                    inputPlayerName();
                }
                wall.ballReset();
                gameTimer.stop();
            } else if (wall.isDone()) {
                if (wall.hasLevel()) {
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                    Settings settings = Settings.getCurrentSettings();
                    if (settings.isSoundEffect()) {
                        SoundEffect.WIN.play();
                    }
                    inputPlayerName();
                } else {
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }


    /**
     * ask player to input their name
     *
     */
    private void inputPlayerName(){
        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Your name");
            dialog.setHeaderText("your score is :" + wall.getScore());
            dialog.setContentText("please input your name");
            Optional<String> optional = dialog.showAndWait();

            if (optional.isPresent()) {
                String playerName = optional.get();
                DataManager.writeData(playerName, wall.getScore());
            }
            wall.resetScore();
        });
    }


    /** initialize the board
     * */
    public void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**draw the board
     * */
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
		String root=System.getProperty("user.dir");
		Font font = loadFont(root+"/src/main/resources/fonts/Adventure.ttf", 18f);
        g2d.setFont(font);
        g2d.drawString(message, 230, 225);

        drawBall(wall.ball, g2d);

        for (Brick b : wall.bricks)
            if (!b.isBroken())
                drawBrick(b, g2d);

        drawPlayer(wall.player, g2d);

        if (showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**load the font
     * @param fontFileName the name of the font
     * @param fontSize the size of the font
     * @return the define font*/
	public static Font loadFont(String fontFileName, float fontSize)
	{
		try
		{
			File file = new File(fontFileName);
			FileInputStream aixing = new FileInputStream(file);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			aixing.close();
			return dynamicFontPt;
		}
		catch(Exception e)  //handle the exception
		{
			e.printStackTrace();
			return new java.awt.Font("Ariel", Font.PLAIN, 14);
		}
	}


    /** clear the current board*/
    public void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }

    /**draw the bricks*/
    public void drawBrick(Brick brick, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**draw the ball*/
    public void drawBall(Ball ball, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**draw the paddle*/
    public void drawPlayer(Paddle p, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = p.getPaddleFace();
        g2d.setColor(Paddle.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Paddle.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

     /**draw the menu*/
    public void drawMenu(Graphics2D g2d) {
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

     /**draw game board*/
    public void obscureGameBoard(Graphics2D g2d) {

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, DEF_WIDTH, DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

     /**draw pause menu*/
    public void drawPauseMenu(Graphics2D g2d) {
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if (strLen == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE, frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE, x, y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if (continueButtonRect == null) {
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE, frc).getBounds();
            continueButtonRect.setLocation(x, y - continueButtonRect.height);
        }

        g2d.drawString(CONTINUE, x, y);

        y *= 2;

        if (restartButtonRect == null) {
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x, y - restartButtonRect.height);
        }

        g2d.drawString(RESTART, x, y);

        y *= 3.0 / 2;

        if (exitButtonRect == null) {
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x, y - exitButtonRect.height);
        }

        g2d.drawString(EXIT, x, y);


        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            wall.player.moveLeft();
        }
        if (code == KeyEvent.VK_RIGHT) {
            wall.player.movRight();
        }
        if (code == KeyEvent.VK_SPACE) {
            if (!showPauseMenu)
                if (gameTimer.isRunning())
                    gameTimer.stop();
                else
                    gameTimer.start();
        }
        if (code == KeyEvent.VK_ESCAPE) {
            showPauseMenu = !showPauseMenu;
            repaint();
            gameTimer.stop();
        }
        if (code == KeyEvent.VK_F1) {
            if (e.isAltDown() && e.isShiftDown())
//				debugConsole.setVisible(true);
                System.out.println("hi");
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
		wall.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!showPauseMenu)
            return;
        if (continueButtonRect.contains(p)) {
            showPauseMenu = false;
            repaint();
        } else if (restartButtonRect.contains(p)) {
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        } else if (exitButtonRect.contains(p)) {
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /** if the board lost focus*/
    public void onLostFocus() {
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }


}
