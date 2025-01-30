package pengyuw007.wagemate.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List.*;

import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.stub.DataAccessStub;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class DataAccessStubTest {
    private IPersistenceAccess dataAccess;

    @BeforeEach
    public void setUp() {
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Finished Persistence test DataAccess (using stub)");
        dataAccess.close();
    }

    // this code will run the tests on the given DAO (Data Access Object)
    public static void dataAccessStubTest(IPersistenceAccess dataAccess) {
        DataAccessStubTest dataAccessTest = new DataAccessStubTest();
        dataAccessTest.dataAccess = dataAccess;
        dataAccessTest.testAddUser();
        dataAccessTest.testGetUserByName();
    }

    /* Delete */
    @Test
    public void testClearUsers() {
        ArrayList<User> users = dataAccess.getUsers();
        assertEquals(3, users.size());

        dataAccess.clearUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void testDeleteUser() {
        String Ann = "Ann";
        String Dave = "Dave";

        // Found this user then delete
        assertTrue(dataAccess.deleteUser(Ann));
        assertEquals(2, dataAccess.getUsers().size());

        //Cannot find this user
        assertFalse(dataAccess.deleteUser(Dave));
        assertEquals(2, dataAccess.getUsers().size());
    }

    /* Read */
    @Test
    public void testGetUsers() {
        ArrayList<User> users = dataAccess.getUsers();
        assertEquals(100, users.get(0).getSin());
    }

    @Test
    public void testGetUserByName() {
        ArrayList<User> users = dataAccess.getUsers();
        String name = "Ann";
        assertEquals(users.get(0), dataAccess.getUserByName(name));// name is found such user

        name = "Dave";
        assertNull(dataAccess.getUserByName(name)); //not found user should be null
    }

    @Test
    public void testIsMatch() {
        User user = new User(100, "Ann", "A");

        //Find a matched user
        assertTrue(dataAccess.isMatch(user.getName(), user.getSin(), user.getPWD()));

        // Cannot find a matched user
        dataAccess.clearUsers();
        assertFalse(dataAccess.isMatch(user.getName(), user.getSin(), user.getPWD()));
    }

    /* Create */
    @Test
    public void testAddUser() {
        ArrayList<User> users = dataAccess.getUsers();
        User user;
        String res;
        // Add duplicate user, sin and name matched
        user = new User(100, "Ann", "A");
        res = dataAccess.addUser(user);
        assertEquals(3, users.size());
        assertEquals("Error! There is already a user with SIN: 100, Name: Ann", res);

        user = new User(100, "Bob", "B"); //duplicate sin
        dataAccess.addUser(user);
        assertEquals(4, users.size());

        user = new User(101, "Ann", "A"); //duplicate name
        dataAccess.addUser(user);
        assertEquals(5, users.size());

        user = new User(101, "Dave", "D");
        dataAccess.addUser(user);
        assertEquals(6, users.size());

        //After clear then normally add
        dataAccess.clearUsers();
        assertEquals(0, users.size());
        dataAccess.addUser(user);
        assertEquals(1, users.size());
    }

    /* Update */
    @Test
    public void testRename() {
        ArrayList<User> users = dataAccess.getUsers();
        String ann = "Ann";
        String bob = "Bob";
        String cathy = "Cathy";
        String dave = "Dave";

        //Found user and replace with desired name
        dataAccess.rename(ann, dave);
        assertEquals(dave, users.get(0).getName());

        //Cannot find user
        dataAccess.rename(ann, bob); //Ann's name already replaced to Dave
        assertNull(dataAccess.getUserByName(ann));

        // Found user but name already exists, rename fails
        dataAccess.rename(bob,cathy);
        assertEquals(bob,dataAccess.getUserByName(bob).getName());
    }

    @Test
    public void testReSin() {
        String msg = null;
        ArrayList<User> users = dataAccess.getUsers();
        String ann = "Ann";
        String dave = "Dave";
        long a = 100;
        long b = 200;
        long c = 101;

        //Found user with the required name, re-sin success
        dataAccess.reSin(ann,400);
        assertEquals(400,dataAccess.getUserByName(ann).getSin());

        // Not found the user
        dataAccess.reSin(dave,400);
        assertNull(dataAccess.getUserByName(dave));

        // Found the user, but sin repeated
        msg = dataAccess.reSin(ann,200);
        assertEquals("FAIL Re-SIN! SIN: "+200+" already exists.",msg);
    }
}
