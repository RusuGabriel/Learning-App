package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Database instance;
    private static final String connectionString = "jdbc:sqlserver://localhost:1433;database=BikeManagement;integratedSecurity=true";
    private Connection connection;
    static ResultSet resultSet = null;
    private static ArrayList<String> attributes = new ArrayList<String>();

    public static Database getInstance() {
        if(instance==null)
            instance = new Database();
        return instance;
    }

    private Database(){
        try {
            connection = DriverManager.getConnection(connectionString);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("Error at connecting to the database!");
            System.out.println(e.getMessage());
        }
    }

    public static void loadChapters(ArrayList<Chapters> data)
    {
        try{
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "SELECT * ";
            resultSet = statement.executeQuery(sqlStatement);
        }catch (SQLException e) {
        }
    }
}
