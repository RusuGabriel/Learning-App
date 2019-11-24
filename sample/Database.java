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

    public static void loadMailsIn(ArrayList<Mail> data, int id_User, String column) {
        if (!data.isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "SELECT IDMesaj,IDUserS,IDUserR,Subiect from Mesaj where " + column + "=" + id_User + " Group By IDMesaj,IDUserS,IDUserR,Subiect,Data order by Data desc ;";
            resultSet = statement.executeQuery(sqlStatement);
            int columnsNumber = getNumberOfColumns(resultSet);

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 2; i <= columnsNumber; i++)
                    attributes.add(resultSet.getString(i));
                data.add(new Mail(attributes.toArray(String[]::new)));
                attributes.clear();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void loadMailsOut(ArrayList<Mail> data, int id_User, String column) {
        if (!data.isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "SELECT IDMesaj,IDUserS,IDUserR,Subiect from Mesaj where " + column + "=" + id_User + " Group By Data order by Data asc ;";
            resultSet = statement.executeQuery(sqlStatement);
            int columnsNumber = getNumberOfColumns(resultSet);

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 2; i <= columnsNumber; i++)
                    attributes.add(resultSet.getString(i));
                data.add(new Mail(attributes.toArray(String[]::new)));
                attributes.clear();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static ArrayList<String> getCNPs() {

        ArrayList<String> list = new ArrayList<String>();
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "SELECT CNP FROM [User];";
            resultSet = st.executeQuery(SQL);
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }

        return list;
    }

    public static ArrayList<String> getPasswords() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "SELECT Parola FROM [User];";
            resultSet = st.executeQuery(SQL);
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
        return list;
    }

    public static void newUser(User u) {
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "INSERT INTO [User] VALUES('" + u.getNumeUser() + "','" + u.getCNP() + "', '" + u.getParola() + "', '" + u.getEsteMentor() + "'," + u.getPuncte() + ");";
            st.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
    }

    public static String getPwd(String iCNP) {
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "SELECT Parola FROM [User] WHERE CNP = " + iCNP + ";";
            resultSet = st.executeQuery(SQL);
            while (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }

        return "";
    }

    public static void changePwd(String iPwd, String iCNP) {
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "UPDATE [User] SET Parola = '" + iPwd + "' WHERE CNP = " + iCNP + ";";
            st.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
    }

    public static int getPuncte(String iCNP) {
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "SELECT puncte FROM [User] WHERE CNP = " + iCNP + ";";
            resultSet = st.executeQuery(SQL);
            while (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
        return 0;
    }

    public static void updatePuncte(String iCNP, int val) {
        try {
            int pct = getPuncte(iCNP);
            pct = pct - val;
            String newPoints = Integer.toString(pct);
            Statement st = instance.connection.createStatement();
            String SQL = "UPDATE [User] SET puncte = '" + newPoints + "' WHERE CNP = " + iCNP + ";";
            st.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
    }


    public static String getNume(String iCNP) {
        try {
            Statement st = instance.connection.createStatement();
            String SQL = "SELECT NumeUser FROM [User] WHERE CNP = " + iCNP + ";";
            resultSet = st.executeQuery(SQL);
            while (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }

        return "";

    }

    public static void loadPrezentari(ArrayList<String> data) {
        if (!data.isEmpty())
            return;
        System.out.println("Am ajuns aici!!");
        try {
            Statement statement = instance.connection.createStatement();
            System.out.println("Inainte de Statement!!");
            System.out.println("Caut dupa: "+MyContentPane.searchValue);
            String sqlStatement = "select ContinutSectiune from CapitolCurs  cc join Sectiune i on cc.IDCapitol = i.IDCapitol where NumeCapitol ='" + MyContentPane.searchValue + "';";
            resultSet = statement.executeQuery(sqlStatement);
            System.out.println("Dupa statement!!");
            //int columnsNumber = getNumberOfColumns(resultSet);

            // Print results from select statement
            System.out.println("Raspuns Query: "+resultSet.next());
            while (resultSet.next()) {
                data.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }
}
