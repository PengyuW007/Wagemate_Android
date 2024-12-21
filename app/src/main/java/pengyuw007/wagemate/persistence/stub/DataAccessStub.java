package pengyuw007.wagemate.persistence.stub;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class DataAccessStub implements IPersistenceAccess {
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

    public DataAccessStub(String dbName){
        this.dbName = dbName;
    }
    @Override
    public void open(String dbPath) {

    }

    @Override
    public void close() {

    }

    @Override
    public int addPerson(User user) {
        return 0;
    }

    @Override
    public User getPersonByName(String name) {
        return null;
    }

    @Override
    public ArrayList<User> getPeople() {
        return null;
    }

    @Override
    public boolean isSame(String name, String group) {
        return false;
    }

    @Override
    public boolean isMatch(String name, String password) {
        return false;
    }

    @Override
    public void rename(String name, String newName) {

    }

    @Override
    public void rePassword(String name, String newPassword) {

    }

    @Override
    public void reStatus(String name, boolean status) {

    }

    @Override
    public boolean deletePerson(String name) {
        return false;
    }

    @Override
    public void clearPeople() {

    }
}
