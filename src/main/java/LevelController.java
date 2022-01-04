import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import properties.PropertiesSetter;

import java.io.IOException;
import java.util.Properties;

public class LevelController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private final String LEVEL1_TITLE = PropertiesSetter.getTitle()+" = Level 1";
	private final String LEVEL2_TITLE = PropertiesSetter.getTitle()+" = Level 2";
	private final String LEVEL3_TITLE = PropertiesSetter.getTitle()+" = Level 3";
	private final String LEVEL4_TITLE = PropertiesSetter.getTitle()+" = Level 4";

	@FXML
	void back(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/StartScreenView.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	@FXML
    void goLevel1(MouseEvent event) throws IOException {
		setScene(event, LEVEL1_TITLE);
		GameController newGame = new GameController();
		newGame.init();




    }

    @FXML
    void goLevel2(MouseEvent event) throws IOException {
		setScene(event, LEVEL2_TITLE);

    }

    @FXML
    void goLevel3(MouseEvent event) throws IOException {
		setScene(event, LEVEL3_TITLE);

    }

    @FXML
    void goLevel4(MouseEvent event) throws IOException {
		setScene(event, LEVEL4_TITLE);

    }


	private void setScene(MouseEvent event, String title) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/GameView.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle(title);

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
