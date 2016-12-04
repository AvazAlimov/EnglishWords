package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class DeleteWord implements Initializable{

    public DatePicker datePicker;
    public ListView<Label> container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void deleteWord() {
        if(container.getFocusModel().getFocusedIndex() == -1)
            return;

        String temp = container.getFocusModel().getFocusedItem().getText();

        String name = temp.substring(0, temp.indexOf(" -"));

        SQLiteConnection.deleteWord(name);

        for(Word word : Main.words)
            if(word.getName().equals(name)){
                Main.words.remove(word);
                break;
            }

        searchWords();
    }

    public void backToSettings(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("SettingsWindow.fxml"));
        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }

    public void searchWords() {
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
}
