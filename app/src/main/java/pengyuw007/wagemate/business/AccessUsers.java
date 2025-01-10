package pengyuw007.wagemate.business;

import java.util.List;

import pengyuw007.wagemate.application.Main;
import pengyuw007.wagemate.application.Services;
import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class AccessUsers {
    private IPersistenceAccess dataAccess;

    public AccessUsers(){
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public void addUser(User user){
        dataAccess.addUser(user);
    }

    public boolean deleteUser(User user){
        return dataAccess.deleteUser(user.getName());
    }

    public User getUserByName(String name){
        return dataAccess.getUserByName(name);
    }

    public void updateUserName(String name, String newName){
        dataAccess.rename(name,newName);
    }

    public void updateUserSin(String name,long newSin){
        dataAccess.reSin(name,newSin);
    }

    public void updateUserPWD(String name,String newPWD){
        dataAccess.rePassword(name, newPWD);
    }
}
