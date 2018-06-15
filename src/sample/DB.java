package sample;

import java.sql.*;

public class DB {

    private static Connection connection;

    public static Connection getConnection() {
        // Load the JDBC driver
        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/BUS", "root", "admin");

            String query = "SELECT * FROM users";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("name");
                System.out.println(username);
            }

            connection.close();

        } catch (SQLException sqle){
            System.out.println("Sql Exception");
        } catch (ClassNotFoundException notfound){
            System.out.println("class was not found");
        }

        return connection;
    }
}