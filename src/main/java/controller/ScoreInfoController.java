package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import setting.Settings;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreInfoController implements Initializable {


    @FXML private AnchorPane scorePane;

    private static String player;

    private static int score;

    public static void setPlayer(String player) {
        ScoreInfoController.player = player;
    }

    public static void setScore(int score) {
        ScoreInfoController.score = score;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Settings currentSettings = Settings.getCurrentSettings();
        scorePane.getStylesheets().add(currentSettings.getThemeColour().getUrl());
    }

    @FXML
    public void ok(){

    }
}
