package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class SearchWord implements Initializable{
    public TextField search;
    public ListView<Label> container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Word word : Main.words)
            container.getItems().add(new Label(word.getName() + " - " + word.getMeaning()));
    }

    public void addWords() {
        container.getItems().clear();

        for (Word word : Main.words) {
            String name = word.getName();
            if (contains(name.toLowerCase(), search.getText().toLowerCase()))
                container.getItems().add(new Label(word.getName() + " - " + word.getMeaning()));
        }
    }

    private boolean contains(String word, String part) {
        if (part.length() > word.length())
            return false;
        for (int i = 0; i < word.length(); i++) {
            if ((i + part.length() - 1) < word.length()) {
                boolean isPart = true;
                for (int j = 0; j < part.length(); j++)
                    if (word.charAt(i + j) != part.charAt(j))
                        isPart = false;
                if (isPart)
                    return true;
            }
        }
        return false;
    }

    public void backToSettings(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("SettingsWindow.fxml"));
        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }
}
