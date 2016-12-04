package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("duplicate")
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

    public static void main(String[] args) {
        words = new ArrayList<>();
        SQLiteConnection.init();

        for (Word word : words)
            System.out.println(word.toString());

        launch(args);
    }
}

class SQLiteConnection {
    private static String path = "jdbc:sqlite:database.db";
    private static Connection connection = null;

    public static void init() {
        try {
            connection = DriverManager.getConnection(path);
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Words(word VARCHAR(50) PRIMARY KEY, meaning VARCHAR(300), date VARCHAR(10));";
            statement.executeUpdate(query);
            query = "SELECT * FROM Words;";
            ResultSet result = statement.executeQuery(query);

            while (result.next())
                Main.words.add(new Word(result.getString("word"), result.getString("meaning"), new Date(result.getString("date"))));

            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        Connection connection;
        java.sql.Statement statement;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(path);
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String query = "SELECT * FROM Words;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getString("word") + "\t" + result.getString("meaning") + "\t" + result.getString("date"));
            }
            statement.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addWord(String word, String meaning, String date) {
        try {
            connection = DriverManager.getConnection(path);
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Words(word,meaning,date) VALUES ('%s','%s','%s');", word, meaning, date);
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteWord(String word) {
        try {
            connection = DriverManager.getConnection(path);
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM Words WHERE word='%s';", word);
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateWord(String word, String changedWord, String meaning) {
        try {
            connection = DriverManager.getConnection(path);
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Words SET word='%s', meaning='%s' WHERE word='%s';", changedWord, meaning, word);
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
