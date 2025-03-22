/**
 * Main application class for the Online Quiz System, extending JavaFX Application.
 *
 * This class initializes and launches the primary stage of the application,
 * loading the login view from the FXML file.
 */

package main.java.com.example.onlinequiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/onlinequiz/views/LoginView.fxml")); // Correct
                                                                                                               // path
        Scene scene = new Scene(root);
        stage.setTitle("Online Quiz System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
