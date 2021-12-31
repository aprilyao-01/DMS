import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {

	/* main menu properties */
	private final String GAME_TITLE = PropertiesSetter.getTitle();
	private final int SCENE_HEIGHT = PropertiesSetter.getScreenHeight(); //
	private final int SCENE_WIDTH = PropertiesSetter.getScreenWidth(); //
	private final int POP_UP_WIDTH = PropertiesSetter.getPopUpWidth();
	private final int POP_UP_HEIGHT = PropertiesSetter.getPopUpHeight();



	private final List<Pair<String, Runnable>> menuItems = Arrays.asList(
			new Pair<String, Runnable>("START GAME", () -> initializeLevels()),
			new Pair<String, Runnable>("HOW TO PLAY", () -> showUserGuide()),
			new Pair<String, Runnable>("SETTINGS", () -> showSetting()),
			new Pair<String, Runnable>("HIGH SCORES", () -> showScores()),
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
		primaryStage.setTitle(GAME_TITLE);
		primaryStage.show();
	}

	/**{@code createMenu} create the menu on the menuRoot,
	 *  and cll the function{@code createMenuArea} to create menu items*/
	public void createMenu() {
		Pane title = createTitle();
		VBox menuArea = createMenuArea();
		menuRoot.getChildren().addAll(title, menuArea);
	}

	/** @return a VBox layout to contain all the menu items*/
	private VBox createMenuArea() {

		// values defined in properties file
		int ITEM_SPACE = PropertiesSetter.getItemSpace();		// 20
		int MENU_X = PropertiesSetter.getMenuX();		//190
		int MENU_Y = PropertiesSetter.getMenuY();		//85

		// create a VBox to lay out the menu items
		VBox menu = new VBox();
		menu.setSpacing(ITEM_SPACE);	// spaces between each item
		menu.setTranslateX(MENU_X);
		menu.setTranslateY(MENU_Y);

		menuItems.forEach(menuItem -> {
			MenuItem item = new MenuItem(menuItem.getKey());
			item.setClick(menuItem.getValue());
			menu.getChildren().add(item);
		});

//		MenuItem menuItems[] = {
//				new MenuItem("START GAME"),
//				new MenuItem("HOW TO PLAY"),
//				new MenuItem("SETTINGS"),
//				new MenuItem("HIGH SCORES"),
//		};
//
//		menuItems[0].setOnMouseClicked(e -> initializeLevels());
//		menuItems[1].setOnMouseClicked(e -> showUserGuide());
//		menuItems[2].setOnMouseClicked(e -> showSetting());
//		menuItems[3].setOnMouseClicked(e -> showScores());
//
//		for(MenuItem item : menuItems){
//			menu.getChildren().add(item);
//		}


		return menu;
	}

	/**{@code createTitle} add the menu title to the Pane,
	 *  and return @return to add in the menuRoot*/
	private Pane createTitle() {

		// values defined in properties file
		String MENU_TITLE = PropertiesSetter.getMenuTitle();
		int MENU_TITLE_X = PropertiesSetter.getMenuTitleX();
		int MENU_TITLE_Y = PropertiesSetter.getMenuTitleY();

		// create title set the style and add to pane
		Text title = new Text(MENU_TITLE);
		Pane container = new Pane();
		container.getStylesheets().add("myStyle.css");
		title.setId("menuTitle");
		container.setTranslateX(MENU_TITLE_X);
		container.setTranslateY(MENU_TITLE_Y);
		container.getChildren().add(title);
		return container;
	}


	private void showUserGuide() {
		System.out.println("UG");
		String UserGuide = PropertiesSetter.getUserGuide();
		setUpDialogBox(UserGuide);
	}


	private void showSetting() {
		//todo: color setting
	}

	private void showScores() {
		//todo: high scores
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
