package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button startGame;
    public Button settings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startGame.setText("Start Game");
        settings.setText("Settings");
    }

    public void openSettings(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("SettingsWindow.fxml"));
        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }
}