import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


/**
 * Class{@code MenuItem} is used to specify the basic properties
 * and response events of each <b>Button</b> on the menu
 *
 * @author Siyu Yao
 */

public class MenuItem extends Pane {

	/**
	 * Make an item (button) for placement in the main menu
	 *
	 * @param itemName - the name of this button (e.g., "START GAME")
	 */

	// Use the resource: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html#labeled
	public MenuItem(String itemName) {

		// create the button and set the style
		getStylesheets().add("myStyle.css");
		Button btn = new Button(itemName);
		btn.getStyleClass().add("button");

		// add to the pane
		getChildren().add(btn);

	}

	/**
	 * Set the button's behavior upon clicking
	 *
	 * @param action - action to be performed on when clicking the item
	 */
	public void setClick(Runnable action) {
		setOnMouseClicked(e -> action.run());
	}
}