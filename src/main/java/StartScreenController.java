import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import javax.swing.*;
import java.io.IOException;


/**Class {@code StartScreenController} is the controller of the FXML file with the same name
 * that controls what should be done when a button in it is clicked
 *
 * @author Siyu Yao
 * */
public class StartScreenController {

	// pop up window properties
	private final int POP_UP_WIDTH = PropertiesSetter.getPopUpWidth();
	private final int POP_UP_HEIGHT = PropertiesSetter.getPopUpHeight();

	// different screens title
	private final String SETTING_TITLE = PropertiesSetter.getTitle() + " - Settings";
	private final String SCORE_TITLE = PropertiesSetter.getTitle() + " - High Scores";


	// different screens properties
	private Stage settingStage;
	private Stage startStage;
	private Stage scoreStage;
	private Scene settingScene;
	private Scene scoreScene;
	private Parent settingRoot;
	private Parent scoreRoot;

    @FXML
    void exit(MouseEvent event) {
		Platform.exit();
    }

    @FXML
    void initializeGame(MouseEvent event) {
//		primaryStage.hide();
		SwingUtilities.invokeLater(() -> new GameFrame().initialize());

    }

    @FXML
    void showScores(MouseEvent event) throws IOException {
		//todo: high scores
		scoreRoot = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));

		scoreStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scoreStage.setTitle(SCORE_TITLE);

		scoreScene = new Scene(scoreRoot);
		scoreStage.setScene(scoreScene);
		scoreStage.show();

    }

    @FXML
    void showSetting(MouseEvent event) throws IOException {
		settingRoot = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));

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

	public Stage getSettingStageStage() {
		return settingStage;
	}

	public Stage getStartStage() {return startStage;}
}