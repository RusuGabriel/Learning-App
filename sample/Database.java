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
            String sqlStatement = "SELECT IDMesaj,IDUserR,U.NumeUser,Subiect from Mesaj join [User] U on Mesaj.IDUserR = U.IDUser where " + column + "=" + id_User + " Group By U.NumeUser,IDMesaj,IDUserS,IDUserR,Subiect,Data order by Data desc ;";
            resultSet = statement.executeQuery(sqlStatement);
            int columnsNumber = getNumberOfColumns(resultSet);

            // Print results from select statement
            while (resultSet.next()) {
                attributes.add("Me");
                for (int i = 3; i <= columnsNumber; i++)
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
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select ContinutSectiune from CapitolCurs  cc join Sectiune i on cc.IDCapitol = i.IDCapitol where NumeCapitol ='" + MyContentPane.searchValue + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                data.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }

    public static void loadIntrebari(ArrayList<String> data) {
        if (!data.isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select i.Intrebare from CapitolCurs  cc join Sectiune s on cc.IDCapitol = s.IDCapitol join Intrebare i on s.IDSectiune = i.IDSectiune where cc.NumeCapitol ='" + MyContentPane.searchValue + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                data.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void loadTitluri(ArrayList<String> data) {
        if (!data.isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select cc.NumeCapitol from CapitolCurs  cc join Sectiune s on cc.IDCapitol = s.IDCapitol join Intrebare i on s.IDSectiune = i.IDSectiune where cc.NumeCapitol ='" + MyContentPane.searchValue + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                data.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void loadRaspunsuri(ArrayList<String> data) {
        if (!data.isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select i.Raspuns from CapitolCurs  cc join Sectiune s on cc.IDCapitol = s.IDCapitol join Intrebare i on s.IDSectiune = i.IDSectiune  where cc.NumeCapitol ='" + MyContentPane.searchValue + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                data.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void send(String text,int Id) {
        try {
            Statement st = instance.connection.createStatement();
            String SQL = String.format("INSERT INTO Mesaj VALUES("+ Id+","+1+",'"+text+"','"+ new Timestamp(System.currentTimeMillis()))+"');";
            st.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
    }

    public static void getQuestions(ArrayList<String> intrebari) {
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select Intrebare from dbo.IntrebareTest where IDCapitol= '" +1 + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                intrebari.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void getRaspunsuri(ArrayList<String> raspunsuri) {
        if(!QuizGrilaController.all.isEmpty())
                return;
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select Raspuns1,Raspuns2,Raspuns3,Raspuns4,Raspuns,Puncte from dbo.IntrebareTest where IDCapitol= '" +1 + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                ArrayList<String> set = new ArrayList<String>();
                set.add(resultSet.getString(1));
                set.add(resultSet.getString(2));
                set.add(resultSet.getString(3));
                set.add(resultSet.getString(4));
                QuizGrilaController.all.add(set);
                raspunsuri.add(resultSet.getString(5));
                QuizGrilaController.points.add(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public int search(String userCnp) {
        try {
            Statement statement = instance.connection.createStatement();
            String sqlStatement = "select [User].IDUser from [User] where CNP = '" + userCnp + "';";
            resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return 0;
    }

    public static void crestePuncte(String iCNP, int val) {
        try {
            int pct = getPuncte(iCNP);
            pct = pct + val;
            String newPoints = Integer.toString(pct);
            Statement st = instance.connection.createStatement();
            String SQL = "UPDATE [User] SET puncte = '" + newPoints + "' WHERE CNP = " + iCNP + ";";
            st.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error at executing the Query!");
        }
    }
}
