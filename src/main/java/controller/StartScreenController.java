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

/**
 * <h1>Class: {@link StartScreenController}</h1>
 *
 * This class is a controller for the start screen,
 * implements the interface Initializable to easy initialize.
 * it can jump to different pages according to player's choice:
 *          <p>1. Game difficulty selection page {@link LevelController};
 *          <p>2. How to play the game {@code showUserGuide};
 *          <p>3. Color theme and sound effect settings {@link SettingsController};
 *          <p>4. High score page {@link HighScoresController}.
 *
 * @author Siyu Yao
 * @version 1.7
 * @since 1.0
 * @see Initializable
 * @see LevelController
 * @see SettingsController
 * @see  HighScoresController
 */
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
	private Stage m_stage;
	private Scene m_scene;
	private Parent m_root;

	@FXML private AnchorPane startPane;
	@FXML private VBox btnBox;


	/** When user click exit it will exit the program*/
    @FXML
    void exit(MouseEvent event) {Platform.exit();}


	/** When user click Start Game it will go to choose game level page
	 *  @throws IOException Invalid FXML file
	 */
    @FXML
    void chooseLevel(MouseEvent event) throws IOException {

		m_root = FXMLLoader.load(getClass().getResource("/view/LevelView.fxml"));

		m_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		m_stage.setTitle(LEVEL_TITLE);

		m_scene = new Scene(m_root);
		m_stage.setScene(m_scene);
		m_stage.show();

    }

	/** When user click High Scores it will go to the high scores page
	 *  @throws IOException Invalid FXML file
	 */
    @FXML
    void showScores(MouseEvent event) throws IOException {
		m_root = FXMLLoader.load(getClass().getResource("/view/HighScoresView.fxml"));

		m_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		m_stage.setTitle(SCORE_TITLE);

		m_scene = new Scene(m_root);
		m_stage.setScene(m_scene);
		m_stage.show();

    }

	/** When user click Settings it will go to the setting page*
	 *  @throws IOException Invalid FXML file
	 */
    @FXML
    void showSetting(MouseEvent event) throws IOException {
		m_root = FXMLLoader.load(getClass().getResource("/view/SettingsView.fxml"));

		m_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		m_stage.setTitle(SETTING_TITLE);

		m_scene = new Scene(m_root);
		m_stage.setScene(m_scene);
		m_stage.show();
    }

	/** When user click How to play it pup up a user guide*/
    @FXML
    void showUserGuide(MouseEvent event) {
		String UserGuide = PropertiesSetter.getUserGuide();
		setUpDialogBox(UserGuide);
    }

	/** pup up a dialog box to show user guide
	 * @param information message the display on the dialog
	 * */
	private void setUpDialogBox(String information) {
		Stage infoPopUp = new Stage();
		infoPopUp.initOwner(startStage);
		VBox dialogBox = new VBox();
		dialogBox.getChildren().add(new Text(information));
		Scene popUpScene = new Scene(dialogBox, POP_UP_WIDTH, POP_UP_HEIGHT);
		infoPopUp.setScene(popUpScene);
		infoPopUp.show();
	}

	/**
     * This function override the method in {@link Initializable},
	 * easy manage the user settings.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Settings currentSettings = Settings.getCurrentSettings();
		startPane.getStylesheets().add(currentSettings.getThemeColour().getUrl());
		btnBox.getStylesheets().add(currentSettings.getThemeColour().getUrl());
	}
}
