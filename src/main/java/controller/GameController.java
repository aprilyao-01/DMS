package controller;

import application.AppUI;
import game.GameBoard;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
	@FXML
	private AnchorPane anchorPaneGame;

	private Stage stage;

	private static int level;

	public static void setLevel(int level) {
		System.out.println(level);
		GameController.level = level;
	}

	private void createSwingContent(SwingNode swingNode){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				swingNode.setContent(new GameBoard(level));
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SwingNode swingNode = new SwingNode();
		createSwingContent(swingNode);
		stage = AppUI.getStartStage();
		stage.setHeight(500);
		anchorPaneGame.getChildren().add(swingNode);
	}
}