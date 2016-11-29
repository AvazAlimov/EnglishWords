package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class AddWord implements Initializable {
    public TextField name;
    public TextField meaning;
    public Button addWord;
    public Button cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setPromptText("enter word");
        meaning.setPromptText("enter meaning");
        addWord.setText("Add Word");
        cancel.setText("Cancel");
    }

    public void backToSettings(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("SettingsWindow.fxml"));
        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }

    public void addWord() {
        String name = this.name.getText();
        String meaning = this.meaning.getText();

        if (name.equals("")) {
            this.name.setStyle("-fx-background-color: lightcoral;-fx-prompt-text-fill: #fff;");
            return;
        }
        if (meaning.equals("")) {
            this.meaning.setStyle("-fx-background-color: lightcoral;-fx-prompt-text-fill: #fff;");
            return;
        }

        LocalDateTime date = LocalDateTime.now();
        Date myDate = new Date(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());
        System.out.println(name + "\n" + meaning + "\n" + myDate.toString());
    }

    public void textChanged(KeyEvent inputMethodEvent) {
        ((TextField) inputMethodEvent.getSource()).setStyle("-fx-background-color: white;-fx-prompt-text-fill: #757575;");
    }
}
