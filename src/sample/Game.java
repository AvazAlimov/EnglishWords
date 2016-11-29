package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Game implements Initializable {

    public Label word;
    public Button oneAnswer;
    public Button twoAnswer;
    public Button threeAnswer;
    public Button fourAnswer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        word.setText("Word Game");
        oneAnswer.setText("A");
        twoAnswer.setText("B");
        threeAnswer.setText("C");
        fourAnswer.setText("D");
    }
}
