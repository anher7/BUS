package BUS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {

    @FXML
    private TextField _email;

    @FXML
    Label pTitle;

    @FXML
    private PasswordField _pass;

    @FXML
    private Label lTitle;

    @FXML
    private GridPane LoginPane;

    @FXML
    private AnchorPane ProfilePane;

    @FXML
    private Button addItemB;


    private String email;

    private String pass;

    public boolean Login() throws Exception{

        String SQL = "SELECT email, password FROM users WHERE email = '" + _email.getText() + "' && password='" + _pass.getText() + "'";

        Statement Cstatement = DB.getConnection().createStatement();

        ResultSet rs = Cstatement.executeQuery(SQL);

        while (rs.next()){
            email = rs.getString("email");
            pass = rs.getString("password");
        }

        if(_email.getText().equals(email) && _pass.getText().equals(pass)) {
            Stage stage = (Stage) LoginPane.getScene().getWindow();
            stage.close();

            Stage primaryStage2 = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            primaryStage2.setTitle("Profile BUS");
            primaryStage2.setScene(new Scene(root, 400, 475));
            primaryStage2.show();

            return true;

        } else {
            lTitle.setText("login Failed");
        }
        return false;
    }

    @FXML
    private boolean logout(ActionEvent event) throws Exception{
        Stage stageOut = (Stage) ProfilePane.getScene().getWindow();
        stageOut.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login BUS");
        primaryStage.setScene(new Scene(root, 500, 475));
        primaryStage.show();
        return true;
    }

    @FXML
    private void addItem(ActionEvent event) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("items.fxml"));
        primaryStage.setTitle("Items Toevoegen");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    @FXML
    private void myItems(ActionEvent event) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("myItems.fxml"));
        primaryStage.setTitle("Mijn Items");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }
}
