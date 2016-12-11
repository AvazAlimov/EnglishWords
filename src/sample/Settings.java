package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    public Button addWord;
    public Button deleteWord;
    public Button editWord;
    public Button back;
    public Button search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addWord.setText("Add Word");
        deleteWord.setText("Delete Word");
        editWord.setText("Edit Word");
        back.setText("Back");
    }

    public void doAction(ActionEvent event) throws IOException {
        Parent parent = null;
        switch (((Control) event.getSource()).getId()) {
            case "backButton":
                parent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                break;
            case "addWordButton":
                parent = FXMLLoader.load(getClass().getResource("AddWordWindow.fxml"));
                break;
            case "deleteWordButton":
                parent = FXMLLoader.load(getClass().getResource("DeleteWord.fxml"));
                break;
            case "editWordButton":
                parent = FXMLLoader.load(getClass().getResource("EditWord.fxml"));
                break;
            case "searchButton":
                parent = FXMLLoader.load(getClass().getResource("SearchWord.fxml"));
                break;
            default:
                break;
        }

        if (parent == null)
            return;

        Scene settings_scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(settings_scene);
        stage.show();
    }
}
