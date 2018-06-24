package BUS;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {

    @FXML
    private TextField Iname;

    @FXML
    private TextField Imail;

    @FXML
    private TextField Ipass;

    @FXML
    private AnchorPane addMem;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    public void cancel(ActionEvent event) throws Exception{
        Stage stage = (Stage) addMem.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addUser(ActionEvent event) {

        try {
            String InputName = Iname.getText();

            //TODO: make an email validation
            String InputEmail = Imail.getText();

            String InputPass = Ipass.getText();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Boolean emptyInput = InputName.isEmpty() || InputEmail.isEmpty() || InputPass.isEmpty();
            if (emptyInput){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Vul alle velden in");
                alert.showAndWait();
                return;
            }

            String query = "INSERT INTO users(name,email,password,created_at) VALUES ("
                    + "'" + InputName + "',"
                    + "'" + InputEmail + "',"
                    + "'" + InputPass + "',"
                    + "'" + timestamp + "'"

                    + ")";

            Statement statement = DB.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate(query);
            int count = statement.getUpdateCount();

            if (count != 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Lid is toegevoegd");
                alert.showAndWait();
                return;
            }

        } catch (SQLIntegrityConstraintViolationException sqli){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("het emailadres: \'" + Imail.getText() + "\' bestaat al");
            alert.showAndWait();
        } catch (SQLException sqle){
            System.out.println(sqle);
        }
    }
}
