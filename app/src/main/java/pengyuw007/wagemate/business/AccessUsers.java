package pengyuw007.wagemate.business;

import java.util.List;

import pengyuw007.wagemate.application.Main;
import pengyuw007.wagemate.application.Services;
import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class AccessUsers {
    private IPersistenceAccess dataAccess;
    private List<User> users;
    private User user;
    private int currUserPos;

    public AccessUsers(){
        dataAccess = Services.getDataAccess(Main.dbName);
        users = null;
        user = null;
        currUserPos = 0;
    }

    public String getUsers(List<User> users){
        users.clear();
        return null;
    }
}
