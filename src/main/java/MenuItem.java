import javafx.scene.control.Button;


/**
 * Class{@code MenuItem} is used to specify the basic properties
 * and response events of each <b>Button</b> on the menu
 *
 * @author Siyu Yao
 */

public class MenuItem extends Button {

	/**
	 * Make an item (button) for placement in the main menu
	 *
	 * @param itemName - the name of this button (e.g., "START GAME")
	 */

	// Use the resource: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html#labeled
	public MenuItem(String itemName) {

		// item preset properties
		int btn_width = PropertiesSetter.getItemWidth();	//200
		int btn_height = PropertiesSetter.getItemHeight();	//5

		// create the button and set the style
		getStylesheets().add("myStyle.css");
		Button btn = new Button(itemName);
		btn.getStyleClass().add("button");
		btn.setPrefWidth(btn_width);
		btn.setPrefHeight(btn_height);
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