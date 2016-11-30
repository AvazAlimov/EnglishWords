package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {

    public Label word;
    public Button oneAnswer;
    public Button twoAnswer;
    public Button threeAnswer;
    public Button fourAnswer;
    public Button backButton;
    private List<Button> answers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        word.setText("Word Game");
        oneAnswer.setText("A");
        twoAnswer.setText("B");
        threeAnswer.setText("C");
        fourAnswer.setText("D");
        backButton.setText("Back");
        answers = new ArrayList<>();
        answers.add(oneAnswer);
        answers.add(twoAnswer);
        answers.add(threeAnswer);
        answers.add(fourAnswer);
        generateTest();
    }

    public void backToMenu(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }

    private void generateTest() {
        Word[] questions = new Word[4];

        for (int i = 0; i < 4; i++) {
            questions[i] = Main.words.get(new Random().nextInt(Main.words.size()));
            for (int j = 0; j <= i; j++) {

            }
        }

        word.setText(questions[0].getName());
        List<Button> answers = shuffle(this.answers);
        for (int i = 0; i < 4; i++)
            answers.get(i).setText(questions[i].getMeaning());
    }

    private static List<Button> shuffle(List<Button> strings) {
        List<Button> teams = new ArrayList<>();
        while (strings.size() != 0) {
            Random random = new Random();
            int index = Math.abs(random.nextInt() % strings.size());
            teams.add(strings.get(index));
            strings.remove(index);
        }
        return teams;
    }
}
