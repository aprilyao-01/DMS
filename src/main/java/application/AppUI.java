package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import properties.PropertiesSetter;
import setting.BGMEffect;


/** <h1> Class: {@link AppUI} </h1>
 *<p>create a START screen with menu options, call the Ui interface.<br>
 *
 * <br>Please note, to avoid error like:<br>
 * <b>Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException</b>
 * ,which is caused by mix use of javafx and swing<br>
 *
 * <br><b>DO NOT RUN IN THIS CLASS, USE {@link StartGame} INSTEAD</b>
 *
 * @see StartGame
 * @version 1.3
 * @since 1.0
 * @author Siyu Yao
 * */
public class AppUI extends Application {

	/* main menu properties */

	// screen properties
	/** The title of the game */
	private final String GAME_TITLE = PropertiesSetter.getTitle();
	/** The bgm effect states */
	private static BGMEffect m_bgmEffect;
	/** Current stage of the start screen */
	private static Stage m_startStage;


	// accessor methods
	/** Get the BGM states of the game
	 * @return current BGM states */
	public static BGMEffect getBgmEffect() {return m_bgmEffect;}
	/** Get the stage of the start screen
	 * @return current stage */
	public static Stage getStartStage() {return m_startStage;}



	/**
     * Store the present stage of the game and jump to the start menu {@link controller.StartScreenController}
     * and launch the start screen scene as the initial game stage.
     *
     * @param m_startStage: Solid parameter, change it to switch between different stages
     */
	@Override
	public void start(Stage m_startStage){
		try {
			m_bgmEffect = BGMEffect.getBGM();

			AppUI.m_startStage = m_startStage;
			m_startStage.setResizable(false);
			m_startStage.setTitle(GAME_TITLE);
			Parent root = FXMLLoader.load(getClass().getResource("/view/StartScreenView.fxml"));
			Scene scene = new Scene(root);
			m_startStage.setScene(scene);
			m_startStage.show();
			m_bgmEffect.play();
		} catch (Exception e){
			e.printStackTrace();
		}
	}


//	public static void main(String[] args) {launch(args);}
}
