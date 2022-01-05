package debug;

import component.Wall;
import component.balls.Ball;
import game.GameBoard;
import properties.PropertiesSetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * <h1>Class: {@link DebugConsole}</h1>
 * the dialog use to debug
 *
 * @version 1.1
 * @since 1.0
 * @see DebugPanel
 */
public class DebugConsole extends JDialog implements WindowListener{

	private static final String TITLE = "Debug Console";

	private DebugPanel debugPanel;
	private GameBoard gameBoard;
	private Wall wall;


	/**
     * This is the constructor of DebugConsole
     *
     * @param wall the wall
     * @param gameBoard gameBoard
     */
	public DebugConsole(Wall wall, GameBoard gameBoard){

		this.wall = wall;
		this.gameBoard = gameBoard;
		initialize();

		debugPanel = new DebugPanel(wall);
		this.add(debugPanel,BorderLayout.CENTER);


		this.pack();
	}

	public void initialize(){
		this.setModal(true);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.addWindowListener(this);
		this.setFocusable(true);
	}


	public void setLocation(){
		int x = PropertiesSetter.getPopUpWidth();
		int y = PropertiesSetter.getPopUpHeight();
		this.setLocation(x,y);
	}


	@Override
	public void windowOpened(WindowEvent windowEvent) {

	}

	@Override
	public void windowClosing(WindowEvent windowEvent) {
		gameBoard.repaint();
	}

	@Override
	public void windowClosed(WindowEvent windowEvent) {

	}

	@Override
	public void windowIconified(WindowEvent windowEvent) {

	}

	@Override
	public void windowDeiconified(WindowEvent windowEvent) {

	}

	@Override
	public void windowActivated(WindowEvent windowEvent) {
		setLocation();
		Ball b = wall.ball;
		debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
	}

	@Override
	public void windowDeactivated(WindowEvent windowEvent) {

	}
}
