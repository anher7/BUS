package BUS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    private AnchorPane adminDb;

    @FXML
    private Button addItemB;

    @FXML
    private Label profileL;

    private String email;

    private String pass;

    private String name;

    private String adminEmail;

    private String adminPass;

    public boolean Login() {

        try {
            String SQL = "SELECT email, password, name FROM users WHERE email = '" + _email.getText() + "' && password='" + _pass.getText() + "' && isAdmin is null";
            Statement Cstatement = DB.getConnection().createStatement();
            ResultSet rs = Cstatement.executeQuery(SQL);

            String adminSQL = "SELECT email, password FROM users WHERE email = '" + _email.getText() + "' && password='" + _pass.getText() + "' && isAdmin is not null";
            Statement AdminStatement = DB.getConnection().createStatement();
            ResultSet ars = AdminStatement.executeQuery(adminSQL);

            while (ars.next()) {
                adminEmail = ars.getString("email");
                adminPass = ars.getString("password");
            }

            if (_email.getText().equals(adminEmail) && _pass.getText().equals(adminPass)) {
                Stage stage = (Stage) LoginPane.getScene().getWindow();
                stage.close();

                try {
                    Stage adminStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("adminDB.fxml"));
                    adminStage.setTitle("Admin DashBoard");
                    adminStage.setScene(new Scene(root, 350, 475));
                    adminStage.show();
                } catch (Exception e){
                    System.out.println(e);
                }
            }

            while (rs.next()) {
                email = rs.getString("email");
                pass = rs.getString("password");
                name = rs.getString("name");
            }

            if (_email.getText().equals(email) && _pass.getText().equals(pass)) {
                Stage stage = (Stage) LoginPane.getScene().getWindow();
                stage.close();

                try {
                    Stage primaryStage = new Stage();

                    Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                    primaryStage.setTitle("Profile van " + name);
                    primaryStage.setScene(new Scene(root, 400, 475));
                    primaryStage.show();

                } catch (Exception e){
                    System.out.println(e);
                }
                return true;

            } else {
                lTitle.setText("login Failed");
            }
        } catch (SQLException sqle){
            System.out.println(sqle);
        }
        return false;
    }

    @FXML
    private boolean logout(ActionEvent event) throws Exception{
        Stage stageOut = (Stage) ProfilePane.getScene().getWindow();
        stageOut.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("U bent uitgelogd");
        alert.showAndWait();
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login BUS");
        primaryStage.setScene(new Scene(root, 500, 475));
        primaryStage.show();
        return true;
    }

    @FXML
    private boolean logoutAdmin(ActionEvent event) throws Exception{
        Stage stageOut = (Stage) adminDb.getScene().getWindow();
        stageOut.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("U bent uitgelogd");
        alert.showAndWait();
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
        Parent root = FXMLLoader.load(getClass().getResource("addItems.fxml"));
        primaryStage.setTitle("Items Toevoegen");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    @FXML
    private void showItems(ActionEvent event) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("showItems.fxml"));
        primaryStage.setTitle("Items");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
        Stage stageOut = (Stage) adminDb.getScene().getWindow();
        stageOut.close();
    }

    @FXML
    public void addUserP(ActionEvent event) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addUser.fxml"));
        primaryStage.setTitle("Lid Toevoegen");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    @FXML
    public void viewCatalog(ActionEvent event) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("catalog.fxml"));
        primaryStage.setTitle("Reserveren/Verlengen");
        primaryStage.setScene(new Scene(root, 600, 420));
        primaryStage.show();
    }
}
