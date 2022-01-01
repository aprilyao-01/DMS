import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/** Class{@code Main} create a START screen with menu options
 *
 * @author Siyu Yao
 * */
public class Main extends Application {

	/* main menu properties */

	// screen properties
	private final String GAME_TITLE = PropertiesSetter.getTitle();
	private final int SCENE_HEIGHT = PropertiesSetter.getScreenHeight(); //450
	private final int SCENE_WIDTH = PropertiesSetter.getScreenWidth(); //600


	private Stage startStage;

	@Override
	public void start(Stage startStage){
		try {
			this.startStage = startStage;
			startStage.setTitle(GAME_TITLE);

			Parent root = FXMLLoader.load(getClass().getResource("StartScreenView.fxml"));
			Scene scene = new Scene(root);
			startStage.setScene(scene);
			startStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
