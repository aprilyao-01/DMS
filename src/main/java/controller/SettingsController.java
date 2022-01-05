package controller;

import application.AppUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
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


/**
 * <h1>Class: {@link SettingsController}</h1>
 *
 * This class is a controller for the setting screen,
 * implements the interface Initializable to easy initialize.
 * it can change theme colour and music effect according to player's choice:
 *          <p>1. Change the theme colour {@link ThemeColour};
 *          <p>2. Change the sound effect {@link setting.SoundEffect};
 *          <p>3. Change the BGM effect {@link setting.SoundEffect};
 *
 * @author Siyu Yao
 * @version 1.7
 * @since 1.0
 * @see Initializable
 * @see ThemeColour
 * @see Settings
 * @see setting.SoundEffect
 */
public class SettingsController implements Initializable {

	// different screens properties
	private final String GAME_TITLE = PropertiesSetter.getTitle();
	private Stage settingStage;
	private Stage m_stage;
	private Scene m_scene;
	private Parent m_root;


    @FXML private AnchorPane settingPane;
	@FXML private CheckBox checkBoxBGM;
    @FXML private CheckBox checkBoxSoundEffect;
    @FXML private RadioButton radioButtonLight;
    @FXML private RadioButton radioButtonDark;


	private final Settings setting = Settings.getCurrentSettings();

	/**This function will be called when user choose the radio button
	 * */
	@FXML
	void setColour(ActionEvent event){
		if(radioButtonLight.isSelected()){
			settingPane.getStylesheets().remove("DarkStyle.css");
			settingPane.getStylesheets().add("LightStyle.css");
		} else if(radioButtonDark.isSelected()){
			settingPane.getStylesheets().remove("LightStyle.css");
			settingPane.getStylesheets().add("DarkStyle.css");
		}
	}


	/**This function will be called when user click the BGM checkbox
	 * */
    @FXML
     void BGM(MouseEvent event) {
		if(checkBoxBGM.isSelected()){
			setting.setm_bgm(true);
		}else{
			setting.setm_bgm(false);
		}
    }

    /**This function will be called when user click the sound effect checkbox
	 * */
	@FXML
    void soundEffect(MouseEvent event) {
		if(checkBoxSoundEffect.isSelected()){
			setting.setSoundEffect(true);
		}else{
			setting.setSoundEffect(false);
		}
    }

	/** When user click cancel, all changes will not be stored
	 *
	 *  @throws IOException Invalid FXML file
	 */
    @FXML
    void cancelSetting(MouseEvent event) throws IOException {
		m_root = FXMLLoader.load(getClass().getResource("/view/StartScreenView.fxml"));

		m_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		m_stage.setTitle(GAME_TITLE);

		m_scene = new Scene(m_root);
		m_stage.setScene(m_scene);
		m_stage.show();
    }

	/** When user click save,
	 * all changes will be stored and effect whole game
	 * take user back to start screen
	 *
	 *  @throws IOException Invalid FXML file
	 */
    @FXML
    void saveSetting(MouseEvent event) throws IOException {
		if(radioButtonLight.isSelected()){
			setting.setThemeColour(ThemeColour.Light);
		}else{
			setting.setThemeColour(ThemeColour.Dark);
		}

		BGMEffect bgmEffect = AppUI.getBgmEffect();
		if(checkBoxSoundEffect.isSelected()){
			setting.setSoundEffect(true);
		}else{
			setting.setSoundEffect(false);
		}

		if(checkBoxBGM.isSelected()){
			setting.setm_bgm(true);
			if (!bgmEffect.isPlay()) {
				System.out.println(" start ");
				AppUI.getBgmEffect().play();
			}
		}else{
			setting.setm_bgm(false);
			if (bgmEffect.isPlay()) {
				System.out.println(" stop ");
				AppUI.getBgmEffect().stop();
			}
		}

		// return to start screen
		m_root = FXMLLoader.load(getClass().getResource("/view/StartScreenView.fxml"));

		m_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		m_stage.setTitle(GAME_TITLE);

		m_scene = new Scene(m_root);
		m_stage.setScene(m_scene);
		m_stage.show();
    }


	/**
     * This function override the method in {@link Initializable},
	 * easy manage the user settings and change the setting page.
	 */
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
		checkBoxBGM.setSelected(setting.ism_bgm());
		checkBoxSoundEffect.setSelected(setting.isSoundEffect());
	}
}