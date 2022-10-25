import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import properties.PropertiesSetter;
import setting.BGMEffect;
import setting.Settings;
import setting.ThemeColour;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {



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


	private Settings setting = Settings.getCurrentSettings();

	@FXML
	void setColour(ActionEvent event){
		if(radioButtonLight.isSelected()){
			settingPane.getStylesheets().remove("DarkStyle.css");
			settingPane.getStylesheets().add("LightStyle.css");
			// todo: change start screen change game screen
		} else if(radioButtonDark.isSelected()){
			settingPane.getStylesheets().remove("LightStyle.css");
			settingPane.getStylesheets().add("DarkStyle.css");
			// todo: change start screen change game screen
		}
	}



    @FXML
    void BGM(MouseEvent event) {
		// todo: add BGM
		if(checkBoxBGM.isSelected()){
			setting.setBGM(true);
		}else{
			setting.setBGM(false);
		}

    }

    @FXML
    void soundEffect(MouseEvent event) {
		// todo: add sound effect
		if(checkBoxSoundEffect.isSelected()){
			setting.setSoundEffect(true);
		}else{
			setting.setSoundEffect(false);
		}

    }

    @FXML
    void cancelSetting(MouseEvent event) throws IOException {
		startRoot = FXMLLoader.load(getClass().getResource("view/StartScreenView.fxml"));

		startStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		startStage.setTitle(GAME_TITLE);

		startScene = new Scene(startRoot);
		startStage.setScene(startScene);
		startStage.show();
    }

    @FXML
    void saveSetting(MouseEvent event) throws IOException {
		if(radioButtonLight.isSelected()){
			setting.setThemeColour(ThemeColour.Light);
		}else{
			setting.setThemeColour(ThemeColour.Dark);
		}

		BGMEffect bgmEffect = Main.getBgmEffect();
		if(checkBoxSoundEffect.isSelected()){
			setting.setSoundEffect(true);
		}else{
			setting.setSoundEffect(false);
		}

		if(checkBoxBGM.isSelected()){
			setting.setBGM(true);
			if (!bgmEffect.isPlay()) {
				System.out.println(" start ");
				Main.getBgmEffect().play();
			}
		}else{
			setting.setBGM(false);
			if (bgmEffect.isPlay()) {
				System.out.println(" stop ");
				Main.getBgmEffect().stop();
			}
		}


		startRoot = FXMLLoader.load(getClass().getResource("view/StartScreenView.fxml"));

		startStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		startStage.setTitle(GAME_TITLE);

		startScene = new Scene(startRoot);
		startStage.setScene(startScene);
		startStage.show();


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (setting.getThemeColour() == ThemeColour.Dark) {
			radioButtonDark.setSelected(true);
			radioButtonLight.setSelected(false);
		} else {
			radioButtonLight.setSelected(true);
			radioButtonDark.setSelected(false);
		}
		settingPane.getStylesheets().add(setting.getThemeColour().getUrl());
		checkBoxBGM.setSelected(setting.isBGM());
		checkBoxSoundEffect.setSelected(setting.isSoundEffect());
	}
}