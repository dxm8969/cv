//imports

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
   MySQLDatabase class
*/
public class MySQLDatabase {
    //attributes
    private String mySQL;
    private String username;
    private String password;
    private Connection connection;
    private String databaseName;

    /*
        Constructor - default
     */
    public MySQLDatabase() {
        this.mySQL = null;
        this.databaseName = null;
        this.password = null;
        this.username = null;
    }


    /*
        Constructor with parameters
        */
    public MySQLDatabase(String mySQL, String username, String password, String databaseName, String connect) {
        this.mySQL = mySQL;
        this.username = username;
        this.password = password;
        this.connection = null;
        this.databaseName = databaseName;
    }


    /*
       DatabaseName setter
    */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /*
       mySQL setter
    */
    public void setMySQL(String mySQL) {
        this.mySQL = mySQL;
    }

    /*
       Username setter
    */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
       Password setter
    */
    public void setPassword(String password) {
        this.password = password;
    }



    /*
       Establishing connection with the database; if it works - true is returned
    */
    public void connect() throws DLException {
        try {
            String timeZoneFixer = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            this.connection = DriverManager.getConnection(this.mySQL + this.databaseName + timeZoneFixer, this.username, this.password);
            //Catching exceptions
        } catch (SQLException e) {
            throw new DLException(e, this.sqlErrorMap(e));
        }
    }

    private ResultSet getResults(String sql) throws DLException {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            throw new DLException(e, this.sqlErrorMap(e));
        }
    }

    /*
        getData method - accepts an SQL statement as a string and returns a two- dimensional array of strings
     */
    public ArrayList<ArrayList<String>> getData(String sql) throws DLException {
        ArrayList<ArrayList<String>> fetchData = new ArrayList<ArrayList<String>>();
        ResultSet resultSet = this.getResults(sql);

        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnNumber = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                ArrayList<String> next = new ArrayList<String>();
                for (int i = 1; i <= columnNumber; i++) {
                    next.add(resultSet.getString(i));
                }
                fetchData.add(next);
            }
        } catch (SQLException e) {

            throw new DLException(e, this.sqlErrorMap(e));
        }
        return fetchData;
    }

    /*
        setData method - accepts an SQL statement as a string and returns a
        boolean value
     */
    public boolean setData(String sql) throws DLException {
        try {
            Statement statement = this.connection.createStatement();
            if (statement.executeUpdate(sql) == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new DLException(e, this.sqlErrorMap(e));
        }
    }


    /*
       Closing the connection; if it closes - true is returned
    */
    public void close() throws DLException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DLException(e, this.sqlErrorMap(e));
        }
    }

    /*
        Method that creates error logs for DLException
     */
    private HashMap<String, String> sqlErrorMap(SQLException e) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Description: ", e.getMessage());
        hashMap.put("Vendor Error Code: ", "" + e.getErrorCode());
        hashMap.put("SQLState", e.getSQLState());
        return hashMap;
    }
}



