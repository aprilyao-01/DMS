package game;//package code;

import properties.PropertiesSetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

// This class add the frame window on the screen
public class GameFrame extends JFrame implements WindowFocusListener {


	public final String GAME_TITLE = PropertiesSetter.getTitle();
	private GameBoard gameBoard;
	private boolean gaming;

	// add new frame
	public GameFrame(){
		super();
		gaming = false;
		this.setLayout(new BorderLayout());
//		gameBoard = new GameBoard(this);
		gameBoard = new GameBoard(-1);
		this.add(gameBoard,BorderLayout.CENTER);
		initialize();
		this.addWindowFocusListener(this);
	}

	// initialize the frame
	public void initialize(){
		this.setTitle(GAME_TITLE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.autoLocate();
		this.setVisible(true);
	}

	// auto locate the windows in center of the screen
	public void autoLocate(){
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (size.width - this.getWidth()) / 2;
		int y = (size.height - this.getHeight()) / 2;
		this.setLocation(x,y);
	}

	@Override
	public void windowGainedFocus(WindowEvent windowEvent) {
		/* the first time the frame loses focus is because it has been disposed to install the GameBoard,
		 so went it regains the focus it's ready to play.
		 of course calling a method such as 'onLostFocus' is useful
		 only if the GameBoard as been displayed at least once */
		gaming = true;
	}

	@Override
	public void windowLostFocus(WindowEvent windowEvent) {
		if(gaming)
			gameBoard.onLostFocus();
	}
}
