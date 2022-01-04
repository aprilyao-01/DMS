import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class GameController {
	@FXML private AnchorPane anchorPaneGame;

	private Stage stage;

	public void init() {
		SwingNode swingNode = new SwingNode();
		createSwingContent(swingNode);

		anchorPaneGame.getChildren().add(swingNode);
		stage.setScene(new Scene(anchorPaneGame));
		stage.show();
	}

	private void createSwingContent(SwingNode swingNode){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {

				swingNode.setContent(new GameBoard());
			}
		});
	}
}