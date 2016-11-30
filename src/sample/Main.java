package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<Word> words;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("BuildVocab");
        primaryStage.getIcons().add(new Image("sample/logo.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        writeFile();
        super.stop();
    }

    public static void main(String[] args) {
        words = new ArrayList<>();
        try {
            readFile();
        } catch (IOException e) {
            try {
                writeFile();
            } catch (IOException ignored) {
            }
        }
        launch(args);
    }

    private static void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter("words.txt", false);
        for (Word word : words)
            fileWriter.write(word.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    private static void readFile() throws IOException {
        File file = new File("words.txt");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String str = new String(data, "UTF-8");

        if (str.length() == 0)
            return;

        int lineIndex = 0;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == '\n') {
                Word word = new Word(str.substring(lineIndex, i));
                words.add(word);
                lineIndex = i + 1;
            }
    }
}
