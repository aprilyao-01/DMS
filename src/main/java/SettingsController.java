import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class SettingsController {



	// different screens properties
	private final String GAME_TITLE = PropertiesSetter.getTitle();
	private Stage settingStage;
	private Stage startStage;
	private Scene startScene;
	private Parent startRoot;

    @FXML private AnchorPane settingPane;
	@FXML private CheckBox checkBoxBGM;
    @FXML private CheckBox checkBoxSoundEffect;
    @FXML private ToggleGroup colour;
    @FXML private RadioButton radioButtonLight;
    @FXML private RadioButton radioButtonDark;



	@FXML
	void setColour(ActionEvent event){
		if(radioButtonLight.isSelected()){
			settingPane.getStylesheets().remove("DarkStyle.css");
			settingPane.getStylesheets().add("LightStyle.css");
			// todo: change start screen change game screen

		} else {
			settingPane.getStylesheets().remove("LightStyle.css");
			settingPane.getStylesheets().add("DarkStyle.css");
			// todo: change start screen change game screen
		}
	}



    @FXML
    void BGM(MouseEvent event) {
		if(checkBoxBGM.isSelected()){
			// todo: add music
		}else{

		}

    }

    @FXML
    void soundEffect(MouseEvent event) {
		if(checkBoxSoundEffect.isSelected()){
			// todo: add sound effect
		}else{

		}

    }

    @FXML
    void cancelSetting(MouseEvent event) throws IOException {
		startRoot = FXMLLoader.load(getClass().getResource("StartScreenView.fxml"));

		startStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		startStage.setTitle(GAME_TITLE);

		startScene = new Scene(startRoot);
		startStage.setScene(startScene);
		startStage.show();
    }

    @FXML
    void saveSetting(MouseEvent event) {


    }

}