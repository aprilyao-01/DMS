package controller;

import application.AppUI;
import game.GameBoard;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import properties.PropertiesSetter;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>Class: {@link GameController}</h1>
 *
 * This class is a controller for the Game screen,
 * implements the interface Initializable to easy initialize.
 * It call the swing game and add to the anchorPane.
 *
 * @author Siyu Yao
 * @version 1.3
 * @since 1.0
 * @see Initializable
 * @see GameBoard
 */
public class GameController implements Initializable {
	@FXML
	private AnchorPane anchorPaneGame;

	private Stage stage;
	private static int m_level;

	public static void setLevel(int m_level) {
		System.out.println(m_level);
		GameController.m_level = m_level;
	}

	private void createSwingContent(SwingNode swingNode){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				swingNode.setContent(new GameBoard(m_level));
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SwingNode swingNode = new SwingNode();
		createSwingContent(swingNode);
		stage = AppUI.getStartStage();
		stage.setHeight(PropertiesSetter.getScreenHeight());
		anchorPaneGame.getChildren().add(swingNode);
	}
}