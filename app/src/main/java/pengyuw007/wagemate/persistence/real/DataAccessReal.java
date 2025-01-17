package pengyuw007.wagemate.persistence.real;

import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import pengyuw007.wagemate.objects.Job;
import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class DataAccessReal implements IPersistenceAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<User> users;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessReal(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void open(String dbPath) {
        String url;
        try {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

            /*** Alternate setups for different DB engines, just given as examples. Don't use them. ***/

            /*
             * // Setup for SQLite. Note that this is undocumented and is not guaranteed to work.
             * // See also: https://github.com/SQLDroid/SQLDroid
             * dbType = "SQLite";
             * Class.forName("SQLite.JDBCDriver").newInstance();
             * url = "jdbc:sqlite:" + dbPath;
             * c1 = DriverManager.getConnection(url);
             *
             * ... create statements
             */

            /*** The following two work on desktop builds: ***/

            /*
             * // Setup for Access
             * dbType = "Access";
             * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
             * url = "jdbc:odbc:SC";
             * c1 = DriverManager.getConnection(url,"userid","userpassword");
             *
             * ... create statements
             */

            /*
             * //Setup for MySQL
             * dbType = "MySQL";
             * Class.forName("com.mysql.jdbc.Driver");
             * url = "jdbc:mysql://localhost/database01";
             * c1 = DriverManager.getConnection(url, "root", "");
             *
             * ... create statements
             */
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    @Override
    public void close() {
        try {    // commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        String myName = EOF, myPWD = EOF;
        //String name;

        try {
            cmdString = "Select * from Students where NAME=" + name;
            rs3 = st1.executeQuery(cmdString);
            // ResultSetMetaData md2 = rs3.getMetaData();
            while (rs3.next()) {
                //mySin = rs3.getLong("SIN");
                myName = rs3.getString("Name");
                myPWD = rs3.getString("Address");
                //user = new User(mySin, myName, myPWD);
            }
            rs3.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        return user;
    }

    @Override
    public String addUser(User user) {
        int count;
        String values;

        result = null;
//        try{
//            values = user.getSin()+",'"+user.getName()+",'"+user.getPWD()+"'";
//            cmdString = "Insert into Users "+" Values("+values+")";
//
//        }
        return null;
    }

    private boolean isFound(long sin, String name) {
        boolean found = false;

        try {
            cmdString = "Select * from Users where (Sin = '" + sin + "' AND Name = '" + name + "')";
            rs4 = st2.executeQuery(cmdString);
            if (!rs4.isBeforeFirst()) {
                System.out.println("No SIN: " + sin + ", " + name + " exist!");
            } else {
                found = true;
            }
            rs4.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return found;
    }

    /********************************
     ************* Jobs **************
     ********************************/
    @Override
    public boolean isMatch(String sin, long name, String password) {
        return false;
    }

    @Override
    public void rename(String name, String newName) {

    }

    @Override
    public void rePassword(String name, String newPassword) {

    }

    @Override
    public void reSin(String name, long newSin) {

    }

    @Override
    public boolean deleteUser(String name) {
        return false;
    }

    @Override
    public void clearUsers() {

    }

    @Override
    public String addJob(Job job) {
        return "";
    }

    @Override
    public Job getJobByURL(String name) {
        return null;
    }

    @Override
    public boolean isMatchJob(String url, String name) {
        return false;
    }

    @Override
    public void renameJob(String url, String name) {

    }

    @Override
    public void reURL(String url, String newURL) {

    }

    @Override
    public boolean deleteJob(String url) {
        return false;
    }

    @Override
    public void clearJobs() {

    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }

    public String getUserSequential(List<User> usersRes) {
        User user;
        String currSin = EOF, currName = EOF, currPWD = EOF;
        long Sin;
        result = null;
        try {
            cmdString = "Select * from Users";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                currSin = rs2.getString("Sin");
                currName = rs2.getString("Name");
                currPWD = rs2.getString("Password");
                Sin = Long.parseLong(currSin);
                user = new User(Sin, currName, currPWD);
                usersRes.add(user);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public String checkWarning(Statement st) {
        String result;

        result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }
}
