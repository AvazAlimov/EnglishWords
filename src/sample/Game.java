package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Game implements Initializable{

    public Label word;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        word.setText("Word Game");

    }
}
