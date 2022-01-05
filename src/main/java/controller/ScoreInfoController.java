package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import setting.Settings;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * <h1>Class: {@link ScoreInfoController}</h1>
 *
 * This class is a controller for the setting screen,
 * implements the interface Initializable to easy initialize.
 * it can change theme colour and music effect according to m_player's choice:
 *          <p>2. Change the sound effect {@link setting.SoundEffect};
 *          <p>3. Change the BGM effect {@link setting.SoundEffect};
 *
 * @author Siyu Yao
 * @version 1.1
 * @since 1.0
 * @see Initializable
 */
public class ScoreInfoController implements Initializable {

    @FXML private AnchorPane scorePane;

    private static String m_player;
    private static int m_score;
    public static void setPlayer(String m_player) {
        ScoreInfoController.m_player = m_player;
    }
    public static void setScore(int m_score) {
        ScoreInfoController.m_score = m_score;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Settings currentSettings = Settings.getCurrentSettings();
        scorePane.getStylesheets().add(currentSettings.getThemeColour().getUrl());
    }
}
