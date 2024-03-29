package debug;

import component.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DebugPanel extends JPanel {

	private static final Color DEF_BKG = Color.WHITE;


	private JButton skipLevel;
	private JButton resetBalls;

	private JSlider ballXSpeed;
	private JSlider ballYSpeed;

	private Wall wall;

	public DebugPanel(Wall wall){

		this.wall = wall;

		initialize();

		// todo: add confirm button

		skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
		resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

		// todo: slider don't have a name or label display on the screen
		ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
		ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

		this.add(skipLevel);
		this.add(resetBalls);

		this.add(ballXSpeed);
		this.add(ballYSpeed);

	}

	public void initialize(){
		this.setBackground(DEF_BKG);
		this.setLayout(new GridLayout(2,2));
	}

	public JButton makeButton(String title, ActionListener e){
		JButton out = new JButton(title);
		out.addActionListener(e);
		return  out;
	}

	public JSlider makeSlider(int min, int max, ChangeListener e){
		JSlider out = new JSlider(min,max);
		out.setMajorTickSpacing(1);
		out.setSnapToTicks(true);
		out.setPaintTicks(true);
		out.addChangeListener(e);
		return out;
	}

	public void setValues(int x,int y){
		ballXSpeed.setValue(x);
		ballYSpeed.setValue(y);
	}

}
