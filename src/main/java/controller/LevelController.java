package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import properties.PropertiesSetter;
import setting.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * <h1>Class: {@link LevelController}</h1>
 *
 * <p>This class is a controller for the level choose page,
 * implements the interface Initializable to easy initialize.
 * it will lwt user choose the level they want to play.
 *
 * The level is refactored from {@code Wall}
 *
 * @author Siyu Yao
 * @version 1.3
 * @since 1.0
 * @see Initializable
 */
public class LevelController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private AnchorPane levelPane;

    private final String LEVEL1_TITLE = PropertiesSetter.getTitle()+" = Level 1";
	private final String LEVEL2_TITLE = PropertiesSetter.getTitle()+" = Level 2";
	private final String LEVEL3_TITLE = PropertiesSetter.getTitle()+" = Level 3";
	private final String LEVEL4_TITLE = PropertiesSetter.getTitle()+" = Level 4";

	/** When user click back, user will return to start screen
	 *
	 *  @throws IOException Invalid FXML file
	 */
	@FXML
	void back(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/view/StartScreenView.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	@FXML
    void goLevel1(MouseEvent event) throws IOException {
		setScene(event, LEVEL1_TITLE, 0);
	}

    @FXML
    void goLevel2(MouseEvent event) throws IOException {
		setScene(event, LEVEL2_TITLE, 1);

    }

    @FXML
    void goLevel3(MouseEvent event) throws IOException {
		setScene(event, LEVEL3_TITLE, 2);
    }

    @FXML
    void goLevel4(MouseEvent event) throws IOException {
		setScene(event, LEVEL4_TITLE, 3);
    }


	private void setScene(MouseEvent event, String title, int level) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/view/GameView.fxml"));
		GameController.setLevel(level);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Settings currentSettings = Settings.getCurrentSettings();
		levelPane.getStylesheets().add(currentSettings.getThemeColour().getUrl());
	}
}
