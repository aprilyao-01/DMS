import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class GameContronller {

    @FXML
    private AnchorPane gamePane;

	private Stage gameStage;



	public void init() throws IOException {
//		this.gameStage = gameStage;
//		SwingNode swingNode = new SwingNode();
////		createSwingContent(swingNode);
//		swingNode.setContent(new GameBoard());
//		gamePane.getChildren().add(swingNode);

		GameFrame gameFrame = new GameFrame();
		JFXPanel gamePanel = new JFXPanel();;

		gameFrame.getContentPane().add(gamePanel);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));

		Parent gameRoot = fxmlLoader.load();
		Scene gameScene = new Scene(gameRoot);
		gamePanel.setScene(gameScene);


	}

	private void createSwingContent(SwingNode swingNode){
		SwingUtilities.invokeLater(()->{
//			swingNode.setContent(new GameFrame().initialize());
		});
	}

}
