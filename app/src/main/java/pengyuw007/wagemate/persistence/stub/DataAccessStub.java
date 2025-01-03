package pengyuw007.wagemate.persistence.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class DataAccessStub implements IPersistenceAccess {
    private String dbName;
    private String dbType;

    private ArrayList<User> users;

    public DataAccessStub(String dbName){
        this.dbName = dbName;
    }

    @Override
    public void open(String dbPath) {
        users= new ArrayList<>();
    }

    @Override
    public void close() {

    }

    @Override
    public String addUser(User user) {
        String res = null;
        if(!isFound(user.getSin(),user.getName())){
            users.add(user);
        }else{
            res = "There is already a user with SIN: "+user.getSin()+", Name: "+user.getName();
        }
        return res;
    }

    @Override
    public User getUserBySin(long sin) {
        User user = null;

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getSin()==sin){
                user = users.get(i);
            }
        }
        return user;
    }

    @Override
    public boolean isMatch(String name, String sin, String password) {
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

    private boolean isFound(long sin,String name) {
        boolean found = true;

        for (int i = 0;i<users.size();i++){
            User currUser = users.get(i);
            if(currUser.getSin()==sin&& Objects.equals(currUser.getName(), name)){
                found = false;
            }
        }

        return found;
    }

    public String getUserSequential(List<User> userRes) {
        return null;
    }
}
