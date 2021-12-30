import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {

	/* main menu properties */
	private final int SCENE_HEIGHT = PropertiesSetter.getScreenHeight(); //
	private final int SCENE_WIDTH = PropertiesSetter.getScreenWidth(); //
	private final int POP_UP_WIDTH = PropertiesSetter.getPopUpWidth();
	private final int POP_UP_HEIGHT = PropertiesSetter.getPopUpHeight();
	private final Color MenuColor = Color.rgb(111, 109, 190);



	private final List<Pair<String, Runnable>> menuItems = Arrays.asList(
			new Pair<String, Runnable>("START GAME", () -> initializeLevels()),
			new Pair<String, Runnable>("HOW TO PLAY", () -> displayUserGuide()),
			new Pair<String, Runnable>("SETTINGS", () -> displaySetting()),
			new Pair<String, Runnable>("EXIT", Platform::exit));


	private Pane menuRoot = new Pane();

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		createMenu();
		Scene menuScene = new Scene(menuRoot, SCENE_WIDTH, SCENE_HEIGHT);
		menuScene.setFill(Color.SILVER);
		primaryStage.setScene(menuScene);
		this.primaryStage = primaryStage;
		primaryStage.setTitle(PropertiesSetter.getTitle());
		primaryStage.show();
	}


	public void createMenu() {
		Pane title = createTitle();
		VBox menuArea = createMenuArea();
		menuRoot.getChildren().addAll(title, menuArea);
	}


	private VBox createMenuArea() {
		VBox menu = new VBox();
		menu.setTranslateX(SCENE_WIDTH / 2);
		menu.setTranslateY(SCENE_HEIGHT / 2);
		for(Pair<String, Runnable> menuItem : menuItems) {
			MenuItem item = new MenuItem(menuItem.getKey());
			item.setClick(menuItem.getValue());
			menu.getChildren().add(item);
		}
		return menu;
	}


	private Pane createTitle() {
		Text title = new Text("WELCOME TO THE BREAKOUT GAME!");
		Pane container = new Pane();
		container.getStylesheets().add("myStyle.css");
		title.setId("menuTitle");
		container.getChildren().add(title);
		container.setTranslateX(SCENE_WIDTH / 2 - title.getLayoutBounds().getWidth() / 2);
		container.setTranslateY(SCENE_HEIGHT / 4);
		return container;
	}


	private void displayUserGuide() {
		String UserGuide = PropertiesSetter.getUserGuide();
		setUpDialogBox(UserGuide);
	}


	private void displaySetting() {
		//todo: color setting
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


	private void initializeLevels() {
		System.out.println("play\n");
		//todo: link to GameBoard
	}


	public static void main(String[] args) {
		launch(args);
	}
}
