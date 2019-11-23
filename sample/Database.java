package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Database instance;
    private static final String connectionString = "jdbc:sqlserver://localhost:1433;database=DevHacks;integratedSecurity=true";
    private Connection connection;
    static ResultSet resultSet = null;
    private static ArrayList<String> attributes = new ArrayList<String>();

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    private Database() {
        try {
            connection = DriverManager.getConnection(connectionString);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("Error at connecting to the database!");
            System.out.println(e.getMessage());
        }
    }

    public static void loadChapters(ArrayList<Chapters> data, int idCurs) {
        if (!data.isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "SELECT * from CapitolCurs where IDCurs = " + idCurs;
            resultSet = statement.executeQuery(sqlStatement);
            int columnsNumber = getNumberOfColumns(resultSet);

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 2; i <= columnsNumber; i++)
                    attributes.add(resultSet.getString(i));
                data.add(new Chapters(attributes.toArray(String[]::new)));
                attributes.clear();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    private static int getNumberOfColumns(ResultSet set) throws SQLException {
        ResultSetMetaData rsmd = set.getMetaData();
        return rsmd.getColumnCount();
    }
}
