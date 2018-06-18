package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Auth auth = new Auth();
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login BUS");
        primaryStage.setScene(new Scene(root, 300, 475));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {

        launch(args);

        DB.getConnection().close();
    }
}
