import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;


/**Class {@code StartScreenController} is the controller of the FXML file with the same name
 * that controls what should be done when a button in it is clicked
 *
 * @author Siyu Yao
 * */
public class StartScreenController {

	// pop up window properties
	private final int POP_UP_WIDTH = PropertiesSetter.getPopUpWidth();
	private final int POP_UP_HEIGHT = PropertiesSetter.getPopUpHeight();

	private Stage primaryStage;

    @FXML
    void exit(MouseEvent event) {
//		Platform::exit;
    }

    @FXML
    void initializeGame(MouseEvent event) {
		SwingUtilities.invokeLater(() -> new GameFrame().initialize());

    }

    @FXML
    void showScores(MouseEvent event) {


    }

    @FXML
    void showSetting(MouseEvent event) {

    }

    @FXML
    void showUserGuide(MouseEvent event) {
		String UserGuide = PropertiesSetter.getUserGuide();
		setUpDialogBox(UserGuide);

    }


	private void setUpDialogBox(String information) {
		Stage infoPopUp = new Stage();
		infoPopUp.initOwner(primaryStage);
		VBox dialogBox = new VBox();
		dialogBox.getChildren().add(new Text(information));
		Scene popUpScene = new Scene(dialogBox, POP_UP_WIDTH, POP_UP_HEIGHT);
		infoPopUp.setScene(popUpScene);
		infoPopUp.show();
	}

}
