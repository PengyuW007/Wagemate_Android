package pengyuw007.wagemate.persistence.stub;

import java.util.ArrayList;

import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class DataAccessStub implements IPersistenceAccess {
    private String dbName;
    private String dbType;

    private ArrayList<User> users;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void open(String dbPath) {
        users = new ArrayList<>();
    }

    @Override
    public void close() {

    }

    @Override
    public String addUser(User user) {
        String res = null;
        if (!isFound(user.getSin(), user.getName())) {
            users.add(user);
        } else {
            res = "There is already a user with SIN: " + user.getSin() + ", Name: " + user.getName();
        }
        return res;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                user = users.get(i);
                found = true;
            }
        }

        if (!found){
            System.out.println("No such user named "+name+" exists!");
        }
        return user;
    }

    @Override
    public boolean isMatch(String name, long sin, String password) {
        boolean isMatch = false;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getSin() == sin && user.getName().equals(name) && user.getPWD().equals(password)) {
                isMatch = true;
            }
        }
        return isMatch;
    }

    @Override
    public void rename(String name, String newName) {
        User user = getUserByName(name);
        user.setName(newName);
    }

    @Override
    public void rePassword(String name, String newPassword) {
        User user = getUserByName(name);
        user.setPWD(newPassword);
    }

    @Override
    public void reSin(String name, long newSin) {
        User user = getUserByName(name);
        user.setSin(newSin);
    }

    @Override
    public boolean deleteUser(String name) {
        boolean isRemoved = false;
        User user = getUserByName(name);

        if(user!=null){
            users.remove(user);
            isRemoved = true;
        }

        return isRemoved;
    }

    @Override
    public void clearUsers() {
        users.clear();
    }

    private boolean isFound(long sin, String name) {
        boolean found = true;

        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getSin() == sin && currUser.getName().equals(name)) {
                found = false;
            }
        }

        return found;
    }
}
