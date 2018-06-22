package BUS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddItem implements Initializable {

    @FXML
    private TextField bTitle;

    @FXML
    private TextField bAuthor;

    @FXML
    private TextField bPub;

    @FXML
    private Button addBook;

    @FXML
    private Button cancel;

    @FXML
    private AnchorPane ItemPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        checkItem();
    }

    @FXML
    private void addBook(ActionEvent event){
        String bookTitle = bTitle.getText();
        String bookAuthor = bAuthor.getText();
        String bookPublisher = bPub.getText();

        if (/*bookId.isEmpty() || */bookAuthor.isEmpty() || bookPublisher.isEmpty() || bookTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
            return;
        }

        try {

//            PreparedStatement stmt = DB.getConnection().

//            String query = "INSERT INTO items VALUES ("+
//                    "'" + bId +"',"+
//                    "'" +bookTitle +"',"+
//                    "'" +bookAuthor +"',"+
//                    "'" +bookPublisher +"',"+
//                    "" +1 +"" +
//                    ")";

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

    private void checkItem() {

        try {

            String query = "SELECT * FROM items WHERE title = '" + bTitle.getText() + "'";
            Statement statement = DB.getConnection().createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                String title = rs.getString("title");
                System.out.println(title);

                if (title.equals(bTitle.getText())){
                    System.out.println(title);

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("De Item! : " + title + "Bestaat al");
                    alert.showAndWait();
                }
            }
        } catch (SQLException sqle){
            System.out.println("SQL ERROR!");
            System.out.println(sqle);
        }
    }
}
