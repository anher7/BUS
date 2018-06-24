package BUS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddItemsController implements Initializable {

    @FXML
    private TextField bTitle;

    @FXML
    private TextField bAuthor;

    @FXML
    private TextField bPub;

    @FXML
    private AnchorPane ItemPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    @FXML
    private void addItem(ActionEvent event){
        String bookTitle = bTitle.getText();
        String bookAuthor = bAuthor.getText();
        String bookPublisher = bPub.getText();

        if (bookAuthor.isEmpty() || bookPublisher.isEmpty() || bookTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
            return;
        }

        try {

            String query = "INSERT INTO items(title,author,publisher,Availability) VALUES ("+
                    "'" +bookTitle +"',"+
                    "'" +bookAuthor +"',"+
                    "'" +bookPublisher +"',"+
                    "" +1 +"" +
                    ")";

            Statement statement = DB.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate(query);

            int count = statement.getUpdateCount();
            if (count > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("De item is toegevoegd");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Error!");
                alert.showAndWait();
            }

        } catch (SQLIntegrityConstraintViolationException sqli){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("De titel: \'" + bTitle.getText() + "\' bestaat al");
            alert.showAndWait();

        } catch (SQLException sqle){
            System.out.println("SQL ERROR!");
            System.out.println(sqle);
        }
    }

    @FXML
    private void cancel(ActionEvent event){

        Stage stage = (Stage) ItemPane.getScene().getWindow();
        stage.close();

    }
}
