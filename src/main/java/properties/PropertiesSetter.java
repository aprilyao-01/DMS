package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** <h1> Class: {@link PropertiesSetter} </h1>
 *
 *<p> This class is a static utility class for retrieving the game's properties.<br>
 * All properties of the breakout game are setting in this class,
 * the file `breakout.properties` storing the properties for the breakout game variant
 *
 * @see Properties
 * @version 1.1
 * @since 1.0
 * @author Siyu Yao
 * */

// Used the resource on : https://mkyong.com/java/java-properties-file-examples/

public final class PropertiesSetter {

	/**
	 * Path of the file
	 * */
	private static final String PROPERTIES_FILE = "src/main/resources/breakout.properties";

	/**
	 * This class uses java.util.Properties as a base and extends its functionality
	 */
	private static final Properties GAME_CONFIG;

	/* keys the setter needs to remember */
	// menu item configuration keys
	private static final String USER_GUIDE = "user-guide";

	// properties in GameBoard
	private static final String DEF_HEIGHT = "def-height";
	private static final String CONTINUE = "continue";
	private static final String RESTART = "restart";
	private static final String EXIT = "exit";
	private static final String PAUSE =  "pause";
	private static final String TEXT_SIZE = "test-size";



	// game environment configuration keys
	private static final String TITLE_KEY = "title";
	private static final String SCREEN_HEIGHT = "screen-height";
	private static final String SCREEN_WIDTH = "screen-width";

	// pop-up window configuration keys
	private static final String POP_UP_WIDTH_KEY = "pop-up-width";
	private static final String POP_UP_HEIGHT_KEY = "pop-up-height";

	// max number of the high scores record
	private static final String  MAX_HIGH_SCORE = "max-high-score";





	/**
	 * Blank constructor to ensure no other class tries to create an instance of the
	 * utility class.
	 */
	private PropertiesSetter() {
		// do nothing
	}

	/** A static block to initialize the static Properties member */
	static {
		GAME_CONFIG = new Properties();
		try {
			GAME_CONFIG.load(new FileInputStream(PROPERTIES_FILE));
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

	/** @return def-height */
	public static int getDefHeight() {return getIntegerProperty(DEF_HEIGHT);}

	/** @return continue */
	public static String getContinue() {return GAME_CONFIG.getProperty(CONTINUE);}

	/** @return restart */
	public static String getRestart() {return GAME_CONFIG.getProperty(RESTART);}

	/** @return exit */
	public static String getExit() {return GAME_CONFIG.getProperty(EXIT);}

	/** @return pause */
	public static String getPause() {return GAME_CONFIG.getProperty(PAUSE);}

	/** @return text size */
	public static int getTextSize() {return getIntegerProperty(TEXT_SIZE);}

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

	/** @return the height of pop-up windows in the main menu */
	public static int getMaxHighScore() {return getIntegerProperty(MAX_HIGH_SCORE);}

	/** @return the width of screen in the main menu */
	public static int getScreenWidth() {return getIntegerProperty(SCREEN_WIDTH);}

	/** @return the height of screen in the main menu */
	public static int getScreenHeight() {return getIntegerProperty(SCREEN_HEIGHT);}

}