//package code;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

// This class add the frame window on the screen
public class GameFrame extends JFrame implements WindowFocusListener {

	// this line too long, more like user guide instead of title
	public static final String DEF_TITLE =
			"Breakout Clone\t" +
			"space = start/pause\t" +
			"←/→ = move\t" +
			"left/right\t" +
			"esc = menu";
	private GameBoard gameBoard;
	private boolean gaming;

	// add new frame
	public GameFrame(){
		super();
		gaming = false;
		this.setLayout(new BorderLayout());
		gameBoard = new GameBoard(this);
		this.add(gameBoard,BorderLayout.CENTER);
		initialize();
		this.addWindowFocusListener(this);
	}

	// initialize the frame
	public void initialize(){
		this.setTitle(DEF_TITLE);
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
