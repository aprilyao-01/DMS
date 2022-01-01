import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class SettingsController {



    private Stage stage;
	private StartScreenController controller;

    @FXML private CheckBox checkBoxBGM;
    @FXML private CheckBox checkBoxSoundEffect;
    @FXML private ToggleGroup colour;
    @FXML private RadioButton radioButtonLight;
    @FXML private RadioButton radioButtonDark;


    public void init(Stage stage, StartScreenController controller)
	{
		this.stage = stage;
		this.controller = controller;

		radioButtonLight.setUserData("LightStyle");
		radioButtonDark.setUserData("DarkStyle");



	}



	@FXML
	void setColour(ActionEvent event){
		colour.selectedToggleProperty().addListener(
				(ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
					if (colour.getSelectedToggle() != null) {
						String newColor = colour.getSelectedToggle().getUserData().toString();
						System.out.println(newColor);
					}
				});
	}



    @FXML
    void BGM(MouseEvent event) {

    }

    @FXML
    void soundEffect(MouseEvent event) {


    }

    @FXML
    void cancelSetting(MouseEvent event) {
        stage.close();
		controller.getStartStage().show();
    }

    @FXML
    void saveSetting(MouseEvent event) {

    }

}