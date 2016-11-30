package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class Game implements Initializable {
    public Label word;
    public Button oneAnswer;
    public Button twoAnswer;
    public Button threeAnswer;
    public Button fourAnswer;
    public Button backButton;
    public Button nextButton;
    private List<Button> answers;
    private Word currentWord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answers = new ArrayList<>();

        oneAnswer.setStyle("-fx-font-size: 24px;");
        twoAnswer.setStyle("-fx-font-size: 24px;");
        threeAnswer.setStyle("-fx-font-size: 24px;");
        fourAnswer.setStyle("-fx-font-size: 24px;");
        backButton.setText("Menu");
        nextButton.setText("Next");
        backButton.setStyle("-fx-background-color: #ff6f00;");
        nextButton.setStyle("-fx-background-color: #ff6f00;");

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
        answers.add(oneAnswer);
        answers.add(twoAnswer);
        answers.add(threeAnswer);
        answers.add(fourAnswer);
        Word[] questions = new Word[4];
        List<Word> words = shuffleWords((List<Word>) Main.words.clone());
        for (int i = 0; i < 4; i++)
            questions[i] = words.get(i);

        word.setText(questions[0].getName());
        currentWord = questions[0];
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

    private static List<Word> shuffleWords(List<Word> strings) {
        List<Word> teams = new ArrayList<>();
        while (strings.size() != 0) {
            Random random = new Random();
            int index = Math.abs(random.nextInt() % strings.size());
            teams.add(strings.get(index));
            strings.remove(index);
        }
        return teams;
    }

    public void checkAnswer(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.getText().equals(currentWord.getMeaning()))
            btn.setStyle("-fx-background-color: #4caf50;");
        else {
            btn.setStyle("-fx-background-color: #f44336;");
            findAnswer();
        }
    }

    public void nextWord() {
        oneAnswer.setStyle("-fx-background-color: #ffc107;");
        twoAnswer.setStyle("-fx-background-color: #ffc107;");
        threeAnswer.setStyle("-fx-background-color: #ffc107;");
        fourAnswer.setStyle("-fx-background-color: #ffc107;");
        generateTest();
    }

    private void findAnswer() {
        List<Button> buttons = new ArrayList<>();
        buttons.add(oneAnswer);
        buttons.add(twoAnswer);
        buttons.add(threeAnswer);
        buttons.add(fourAnswer);

        buttons.stream().filter(button -> button.getText().equals(currentWord.getMeaning())).forEachOrdered(button -> button.setStyle("-fx-background-color: #4caf50;"));
    }
}
