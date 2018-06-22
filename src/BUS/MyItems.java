package BUS;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MyItems implements Initializable {

    ObservableList<Item> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Item> myItemsView;

    @FXML
    private TableColumn<Item, Integer> idCol;

    @FXML
    private TableColumn<Item, Integer> titleCol;

    @FXML
    private TableColumn<Item, String> authCol;

    @FXML
    private TableColumn<Item, Boolean> avaCol;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initCol();
        loadItems();
    }

    private void loadItems() {

        try {

            String query = "SELECT * FROM items";
            Statement statement = DB.getConnection().createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Boolean aval = rs.getBoolean("availability");

                list.add(new Item(title, id, author, aval));

                System.out.println(title);
            }
        } catch (SQLException sqle){
            System.out.println(sqle);
        }

        myItemsView.getItems().setAll(list);
    }

    private void initCol(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        avaCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    public static class Item{
        private final SimpleStringProperty title;
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty author;
        private final SimpleBooleanProperty availability;

        Item(String title, Integer id, String author, Boolean availability){
            this.title = new SimpleStringProperty(title);
            this.id = new SimpleIntegerProperty(id);
            this.author = new SimpleStringProperty(author);
            this.availability = new SimpleBooleanProperty(availability);
        }

        public String getTitle() {
            return title.get();
        }

        public int getId() {
            return id.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public boolean getAvailability() {
            return availability.get();
        }

    }
}
