import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Class {@code PropertiesSetter} is a static utility class for setting the game's properties.
 *
 * @author Siyu YAo
 * */

// Used the resource on : https://mkyong.com/java/java-properties-file-examples/

public final class PropertiesSetter {

	/** name of the file storing the properties for the breakout game variant
	 * All properties of the breakout game are setting in this Class
	 * */
	private static final String PROPERTIES_FILE = "src/main/resources/breakout.properties";

	/**
	 * this class uses java.util.Properties as a base and extends its functionality
	 */
	private static final Properties GAME_CONFIG;

	/* keys the setter needs to remember */



	// menu item configuration keys
	private static final String USER_GUIDE = "user-guide";
	private static final String ITEM_WIDTH = "item-width";
	private static final String ITEM_HEIGHT = "item-height";


	// game environment configuration keys
	private static final String TITLE_KEY = "title";
	private static final String SCREEN_HEIGHT_KEY = "screen-height";
	private static final String SCREEN_WIDTH_KEY = "screen-width";

	// pop-up window configuration keys
	private static final String POP_UP_WIDTH_KEY = "pop-up-width";
	private static final String POP_UP_HEIGHT_KEY = "pop-up-height";



	/**
	 * Blank constructor to ensure no other class tries to create an instance of the
	 * utility class.
	 */
	private PropertiesSetter() {
		// do nothing
	}

	/** static block to initialize the static Properties member */
	static {
		GAME_CONFIG = new Properties();
		try {
			GAME_CONFIG.load(new FileInputStream(PROPERTIES_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Augments java.util.Properties functionality by getting a property we know
	 * will be an integer
	 *
	 * @param key - the key used to index this
	 * @return value - the value of the key field in the properties file
	 */


	private static int getIntegerProperty(String key) {
		String value = GAME_CONFIG.getProperty(key);
		// if the key is not found, Properties will return null, and we should return a
		// default value
		if (value == null) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	/** @return the user guide of the game */
	public static String getUserGuide() {return GAME_CONFIG.getProperty(USER_GUIDE);}


	/** @return the space between each item*/
	public static int getItemWidth(){return getIntegerProperty(ITEM_WIDTH);}

	/** @return the space between each item*/
	public static int getItemHeight(){return getIntegerProperty(ITEM_HEIGHT);}

	/** @return the height of the screen */
	public static int getScreenHeight() {
		return getIntegerProperty(SCREEN_HEIGHT_KEY);
	}

	/** @return the width of the screen */
	public static int getScreenWidth() {
		return getIntegerProperty(SCREEN_WIDTH_KEY);
	}

	/** @return the width of pop-up windows in the main menu */
	public static int getPopUpWidth() {
		return getIntegerProperty(POP_UP_WIDTH_KEY);
	}

	/** @return the height of pop-up windows in the main menu */
	public static int getPopUpHeight() {
		return getIntegerProperty(POP_UP_HEIGHT_KEY);
	}

	/** @return the title of the game */
	public static String getTitle() {
		return GAME_CONFIG.getProperty(TITLE_KEY);
	}

}