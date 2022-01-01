import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** Class{@code Main} create a START screen with menu options
 *
 * @author Siyu Yao
 * */
public class Main extends Application {

	/* main menu properties */

	// screen properties
	private final String GAME_TITLE = PropertiesSetter.getTitle();


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
