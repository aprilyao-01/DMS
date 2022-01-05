package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import setting.DataManager;
import setting.Rank;
import setting.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {

	private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private AnchorPane scorePane;

    @FXML private VBox scoreBox;

	@FXML
	void back(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/view/StartScreenView.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Settings currentSettings = Settings.getCurrentSettings();
		scoreBox.getChildren().clear();
		scorePane.getStylesheets().add(currentSettings.getThemeColour().getUrl());
		List<Rank> ranks = DataManager.loadData();
		//  <Text strokeType="OUTSIDE" strokeWidth="0.0" styleClass="title" text="HIGH SCORE" />
		for (Rank rank : ranks) {
			String info = rank.getPlayer() + "        " + rank.getScore();
			Text text = new Text(info);
			text.setStrokeType(StrokeType.OUTSIDE);
			text.getStyleClass().add("title");
			text.setStrokeWidth(0.0);
			scoreBox.getChildren().add(text);
		}
	}
}
