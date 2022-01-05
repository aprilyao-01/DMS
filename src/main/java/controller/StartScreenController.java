package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import properties.PropertiesSetter;
import setting.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**Class {@code StartScreenController} is the controller of the FXML file with the same name
 * that controls what should be done when a button in it is clicked
 *
 * use the reference in
 * https://stackoverflow.com/questions/43996806/initialize-a-javafx-scene-without-the-use-of-a-button-event
 * @author
 * */
public class StartScreenController implements Initializable {

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

	@FXML private VBox btnBox;

    @FXML
    void exit(MouseEvent event) {
		Platform.exit();
    }

    @FXML
    void chooseLevel(MouseEvent event) throws IOException {

		levelRoot = FXMLLoader.load(getClass().getResource("/view/LevelView.fxml"));

		levelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		levelStage.setTitle(LEVEL_TITLE);

		levelScene = new Scene(levelRoot);
		levelStage.setScene(levelScene);
		levelStage.show();

    }


    @FXML
    void showScores(MouseEvent event) throws IOException {
		//todo: high scores
		scoreRoot = FXMLLoader.load(getClass().getResource("/view/HighScoresView.fxml"));

		scoreStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scoreStage.setTitle(SCORE_TITLE);

		scoreScene = new Scene(scoreRoot);
		scoreStage.setScene(scoreScene);
		scoreStage.show();

    }

    @FXML
    void showSetting(MouseEvent event) throws IOException {
		settingRoot = FXMLLoader.load(getClass().getResource("/view/SettingsView.fxml"));

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Settings currentSettings = Settings.getCurrentSettings();
		startPane.getStylesheets().add(currentSettings.getThemeColour().getUrl());
		btnBox.getStylesheets().add(currentSettings.getThemeColour().getUrl());
	}
}
