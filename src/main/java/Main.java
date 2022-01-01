import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


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

	// pop up window properties
	private final int POP_UP_WIDTH = PropertiesSetter.getPopUpWidth();
	private final int POP_UP_HEIGHT = PropertiesSetter.getPopUpHeight();

	// each menu item's properties
	private final int ITEM_SPACE = PropertiesSetter.getItemSpace();		// 20
	private final int MENU_X = PropertiesSetter.getMenuX();		//190
	private final int MENU_Y = PropertiesSetter.getMenuY();		//85


	// array list of menu items
	private final List<Pair<String, Runnable>> menuItems = Arrays.asList(
			new Pair<String, Runnable>("START GAME", () -> initializeGame()),
			new Pair<String, Runnable>("HOW TO PLAY", () -> showUserGuide()),
			new Pair<String, Runnable>("SETTINGS", () -> showSetting()),
			new Pair<String, Runnable>("HIGH SCORES", () -> showScores()),
			new Pair<String, Runnable>("EXIT", Platform::exit));


	private Pane menuRoot = new Pane();


	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws IOException {
//		menuRoot.getStylesheets().add("myStyle.css");
//		createMenu();
		this.primaryStage = primaryStage;
		primaryStage.setTitle(GAME_TITLE);

		Parent root = FXMLLoader.load(getClass().getResource("StartScreenView.fxml"));
//		Scene menuScene = new Scene(menuRoot, SCENE_WIDTH, SCENE_HEIGHT);
		Scene menuScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
//		menuScene.setFill(Color.SILVER);
		primaryStage.setScene(menuScene);
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

		// create a VBox to lay out the menu items
		VBox menu = new VBox();
		menu.setSpacing(ITEM_SPACE);	// spaces between each item
		menu.setTranslateX(MENU_X);
		menu.setTranslateY(MENU_Y);

		// add the menu items to the menu
		menuItems.forEach(menuItem -> {
			MenuItem item = new MenuItem();
			item.setText(menuItem.getKey());
			item.setPrefWidth(item.getBtn_width());
			item.setPrefHeight(item.getBtn_height());
			item.setClick(menuItem.getValue());
			menu.getChildren().add(item);
		});

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
		title.setId("menuTitle");
		container.setTranslateX(MENU_TITLE_X);
		container.setTranslateY(MENU_TITLE_Y);
		container.getChildren().add(title);
		return container;
	}


	private void showUserGuide() {
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


	private void initializeGame() {
		SwingUtilities.invokeLater(() -> new GameFrame().initialize());
//		primaryStage.hide();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
