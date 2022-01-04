import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import properties.PropertiesSetter;

import java.io.IOException;


/**Class {@code StartScreenController} is the controller of the FXML file with the same name
 * that controls what should be done when a button in it is clicked
 *
 * @author
 * */
public class StartScreenController {

	// pop up window properties
	private final int POP_UP_WIDTH = PropertiesSetter.getPopUpWidth();
	private final int POP_UP_HEIGHT = PropertiesSetter.getPopUpHeight();

	// different screens title
	private final String LEVEL_TITLE = PropertiesSetter.getTitle() + " - Levels";
	private final String SETTING_TITLE = PropertiesSetter.getTitle() + " - Settings";
	private final String SCORE_TITLE = PropertiesSetter.getTitle() + " - High Scores";


	// different screens properties
	private Stage startStage;
	private Stage settingStage;
	private Stage scoreStage;
	private Stage levelStage;
	private Scene settingScene;
	private Scene scoreScene;
	private Scene levelScene;
	private Parent settingRoot;
	private Parent scoreRoot;
	private Parent levelRoot;

	@FXML private AnchorPane startPane;

    @FXML
    void exit(MouseEvent event) {
		Platform.exit();
    }

    @FXML
    void chooseLevel(MouseEvent event) throws IOException {
	////		primaryStage.hide();
	//		SwingUtilities.invokeLater(() -> new GameFrame().initialize());

	//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
	//
	//		gameRoot = fxmlLoader.load();

	//		GameContronller gameContronller = fxmlLoader.getController();
	//		gameContronller.init();

	//		GameFrame gameFrame = new GameFrame();
	//		JFXPanel gamePanel = new JFXPanel();;
	//
	//		gameFrame.getContentPane().add(gamePanel);
	////		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
	//
	////		Parent gameRoot = fxmlLoader.load();
	//		gameScene = new Scene(gameRoot);
	//		gamePanel.setScene(gameScene);


	//		Parent root = FXMLLoader.load(getClass().getResource("StartScreenView.fxml"));
	//		Scene scene = new Scene(root);
	//		startStage.setScene(scene);



//		gameStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		gameStage.setTitle(GAME_TITLE);

//		gameScene = new Scene(gameRoot);
//		gameStage.setScene(gameScene);
//		gameStage.show();

//		final SwingNode swingNode = new SwingNode();
//		createAndSetSwingContent(swingNode);
//
//		Pane pane = new Pane();
//		pane.getChildren().add(swingNode);
//		gameStage.setScene(new Scene(pane, 600, 450));
//		gameStage.show();

		levelRoot = FXMLLoader.load(getClass().getResource("view/LevelView.fxml"));

		levelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		levelStage.setTitle(LEVEL_TITLE);

		levelScene = new Scene(levelRoot);
		levelStage.setScene(levelScene);
		levelStage.show();

    }


//	private void createAndSetSwingContent(final SwingNode swingNode){
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
////				GameBoard gameBoard = new GameBoard();
////				gameBoard
//				JPanel panel = new JPanel();
//				panel.add(new JButton("Click!"));
//				swingNode.setContent(panel);
//			}
//		});
//	}

    @FXML
    void showScores(MouseEvent event) throws IOException {
		//todo: high scores
		scoreRoot = FXMLLoader.load(getClass().getResource("view/HighScoresView.fxml"));

		scoreStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scoreStage.setTitle(SCORE_TITLE);

		scoreScene = new Scene(scoreRoot);
		scoreStage.setScene(scoreScene);
		scoreStage.show();

    }

    @FXML
    void showSetting(MouseEvent event) throws IOException {
		settingRoot = FXMLLoader.load(getClass().getResource("view/SettingsView.fxml"));

		settingStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		settingStage.setTitle(SETTING_TITLE);

		settingScene = new Scene(settingRoot);
		settingStage.setScene(settingScene);
		settingStage.show();
    }

    @FXML
    void showUserGuide(MouseEvent event) {
		String UserGuide = PropertiesSetter.getUserGuide();
		setUpDialogBox(UserGuide);
    }


	private void setUpDialogBox(String information) {
		Stage infoPopUp = new Stage();
		infoPopUp.initOwner(startStage);
		VBox dialogBox = new VBox();
		dialogBox.getChildren().add(new Text(information));
		Scene popUpScene = new Scene(dialogBox, POP_UP_WIDTH, POP_UP_HEIGHT);
		infoPopUp.setScene(popUpScene);
		infoPopUp.show();
	}

	public void setStartStage(Stage startStage) {
		this.startStage = startStage;
	}

	public Stage getSettingStageStage() {
		return settingStage;
	}

	public Stage getStartStage() {return startStage;}

	public AnchorPane getStartPane() {return startPane;}
}
