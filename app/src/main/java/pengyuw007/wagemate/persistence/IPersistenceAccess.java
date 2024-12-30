package pengyuw007.wagemate.persistence;

import java.util.ArrayList;
import java.util.List;

import pengyuw007.wagemate.objects.User;

public interface IPersistenceAccess {
    /*** Data Access ***/
    void open(String dbPath);
    void close();
    /*******************************************************/
    /************************* CRUD ************************/
    /*******************************************************/

    /*** CREATE ***/
    String addUser(User user);

    /*** READ ***/
    User getUserBySin(long sin);

    boolean isMatch(String name,String sin, String password); // Find whether info of this user matched

    /*** UPDATE ***/
    void rename(String name, String newName);

    void rePassword(String name, String newPassword);

    void reSin(String name, long newSin );

    /*** DELETE ***/
    boolean deleteUser(String name);

    void clearUsers();
}
