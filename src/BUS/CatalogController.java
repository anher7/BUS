package BUS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CatalogController implements Initializable {

    @FXML
    private Label rsId;

    @FXML
    private Text rsTitle;

    @FXML
    private Text rsAval;

    @FXML
    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void itemLoader(ActionEvent event) {

        String title = search.getText();
        Boolean found = false;

        try {

            String SQL = "SELECT * FROM items WHERE title = '" + title + "'";
            Statement statement = DB.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                String Ititle = resultSet.getString("title");
                int Iid = resultSet.getInt("ID");
                Boolean isAvailable = resultSet.getBoolean("Availability");

                rsTitle.setText(Ititle);
                rsId.setText("id:" + Iid);
                String aval = (isAvailable)? "Beschikbaar" : "Niet Beschikbaar";
                rsAval.setText(aval);

                found = true;
            }

            if (!found){
                rsTitle.setText("Item niet gevonden");
                rsAval.setText("");
                rsId.setText("");
            }

        } catch (SQLException sqle){
            System.out.println(sqle);
        }
    }

    @FXML
    private void reserveAction(ActionEvent event){

    }

    @FXML
    void renewAction(ActionEvent event){

    }
}
