package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class EditWord implements Initializable {

    public ListView<Label> container;
    public DatePicker datePicker;
    public GridPane firstPane;
    public GridPane secondPane;
    public TextField word;
    public TextField meaning;
    public Button cancel;
    private String currentWord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToSettings(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("SettingsWindow.fxml"));
        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }

    public void searchWords(ActionEvent event) {
        container.getItems().clear();
        String date = String.format("%02d/%02d/%04d", datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue(), datePicker.getValue().getYear());
        for (int i = 0; i < Main.words.size(); i++) {
            if (Main.words.get(i).getEnteredDate().toString().equals(date)) {
                Label item = new Label(String.format("%s - %s", Main.words.get(i).getName(), Main.words.get(i).getMeaning()));
                item.setStyle("-fx-font-size: 20; -fx-font-family: Cocon");
                container.getItems().add(item);
            }
        }
    }

    public void selectItem(ActionEvent event) {
        if(container.getFocusModel().getFocusedIndex() == -1)
            return;

        firstPane.setVisible(false);
        secondPane.setVisible(true);

        String temp = ((Label) container.getFocusModel().getFocusedItem()).getText();

        String name = temp.substring(0, temp.indexOf(" -"));
        String meaning = temp.substring(temp.indexOf("- ") + 2, temp.length());
        currentWord = name;

        word.setText(name);
        this.meaning.setText(meaning);
    }

    public void textChanged(KeyEvent keyEvent) {
        meaning.setStyle("-fx-background-color: white; -fx-prompt-text-fill: #424242;");
    }

    public void cancelEditing(ActionEvent event) {
        secondPane.setVisible(false);
        firstPane.setVisible(true);
    }

    public void editWord(ActionEvent event) {
        if (meaning.getText().equals("")) {
            meaning.setStyle("-fx-background-color: lightcoral; -fx-prompt-text-fill: white;");
            return;
        }

        SQLiteConnection.updateWord(word.getText(), currentWord, meaning.getText());

        for(Word word : Main.words) {
            if (word.getName().equals(currentWord)) {
                word.setName(this.word.getText());
                word.setMeaning(meaning.getText());
            }
        }

        cancel.getOnAction().handle(event);
        searchWords(event);
    }
}
