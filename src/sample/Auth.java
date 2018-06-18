package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class Auth {

    @FXML
    private TextField _email;

    @FXML
    private PasswordField _pass;

    private String email;

    private String pass;

    public void Login() throws Exception{

        String sqlT = "SELECT email, password from users";
//        String sql = "SELECT * users WHERE email = ? AND password = ?";


        Statement Cstatement = DB.getConnection().createStatement();
//        Statement Pstatement = DB.getConnection().prepareStatement(sql);

        ResultSet rs = Cstatement.executeQuery(sqlT);
//        ResultSet ls = Pstatement.getResultSet();

        while (rs.next()){
            email = rs.getString("email");
            pass = rs.getString("password");
        }

        if (_email.getText().equals(email) && _pass.getText().equals(pass)){
            Stage primaryStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            primaryStage.setTitle("Profile BUS");
            primaryStage.setScene(new Scene(root, 300, 475));
            primaryStage.show();
        }

    }

}
