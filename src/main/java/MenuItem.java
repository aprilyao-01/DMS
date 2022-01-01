import javafx.scene.control.Button;


/**
 * Class{@code MenuItem} is used to specify the basic properties
 * and response events of each <b>Button</b> on the menu
 *
 * @author Siyu Yao
 */

public class MenuItem extends Button{


	// item preset properties
	private final int btn_width = PropertiesSetter.getItemWidth();	//200
	private final int btn_height = PropertiesSetter.getItemHeight();	//5

	/**
	 * Make an item (button) for placement in the main menu
	 * use css style to the button
	 *
	 */

	// Use the resource: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html#labeled
	public MenuItem() {

		// create the button and set the style
		Button btn = new Button();
//		getStylesheets().add("myStyle.css");
		btn.getStyleClass().add("button");
	}


	/**
	 * Set the button's behavior upon clicking
	 *
	 * @param action - action to be performed on when clicking the item
	 */
	public void setClick(Runnable action) {
		setOnMouseClicked(e -> action.run());
	}


	// Getter
	public int getBtn_width() {return btn_width;}

	public int getBtn_height() {
		return btn_height;
	}
}