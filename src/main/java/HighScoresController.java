import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import properties.PropertiesSetter;

import java.io.IOException;
public class HighScoresController {

	private Stage stage;
    private Scene scene;
    private Parent root;

	@FXML
	void back(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/StartScreenView.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
