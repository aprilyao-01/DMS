import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import properties.PropertiesSetter;

import java.nio.file.Paths;


/** Class{@code Main} create a START screen with menu options
 *
 * @author
 * */
public class Main extends Application {

	/* main menu properties */

	// screen properties
	private final String GAME_TITLE = PropertiesSetter.getTitle();


	private Stage startStage;

	@Override
	public void start(Stage startStage){
		try {
//			music();

			this.startStage = startStage;
			startStage.setTitle(GAME_TITLE);

			Parent root = FXMLLoader.load(getClass().getResource("view/StartScreenView.fxml"));
			Scene scene = new Scene(root);
			startStage.setScene(scene);
			StartScreenController startScreenController = new StartScreenController();
			startScreenController.setStartStage(startStage);
			startStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}
	}


//	public void music() {
//		String s = "audio/BGM.wav";
//		Media h = new Media(Paths.get(s).toUri().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(h);
//		mediaPlayer.play();
//	}

	public static void main(String[] args) {
		launch(args);
	}
}
