package pengyuw007.wagemate.persistence;

import java.util.ArrayList;

import pengyuw007.wagemate.objects.User;

public interface IPersistenceAccess {
    /*** Data Access ***/
    void open(String dbPath);
    void close();
    /*******************************************************/
    /************************* CRUD ************************/
    /*******************************************************/

    /*** CREATE ***/
    int addPerson(User user);

    /*** READ ***/
    User getPersonByName(String name);

    ArrayList<User> getPeople();

    boolean isSame(String name,String group);

    boolean isMatch(String name,String password);

    /*** UPDATE ***/
    void rename(String name, String newName);

    void rePassword(String name, String newPassword);

    void reStatus(String name, boolean status);

    /*** DELETE ***/
    boolean deletePerson(String name);

    void clearPeople();
}
