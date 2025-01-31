package pengyuw007.wagemate.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import pengyuw007.wagemate.objects.Job;
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
        String name = "Ann";
        assertEquals(name, dataAccess.getUserByName(name).getName());// name is found such user

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
        String msg;
        String ann = "Ann";
        String bob = "Bob";
        String cathy = "Cathy";
        String dave = "Dave";

        //Found user and replace with desired name
        msg = dataAccess.rename(ann, dave);
        assertEquals(dave, users.get(0).getName());
        assertEquals("Success! Rename success.", msg);

        //Cannot find user
        dataAccess.rename(ann, bob); //Ann's name already replaced to Dave
        assertNull(dataAccess.getUserByName(ann));

        // Found user but name already exists, rename fails
        dataAccess.rename(bob, cathy);
        assertEquals(bob, dataAccess.getUserByName(bob).getName());
    }

    @Test
    public void testReSin() {
        String msg;
        String ann = "Ann";
        String dave = "Dave";
        long b = 200;

        //Found user with the required name, re-sin success
        dataAccess.reSin(ann, 400);
        assertEquals(400, dataAccess.getUserByName(ann).getSin());

        // Not found the user
        msg = dataAccess.reSin(dave, 400);
        assertEquals("FAIL Re-SIN! No user named: " + dave + " to re-Sin.", msg);

        // Found the user, but sin repeated
        msg = dataAccess.reSin(ann, b);
        assertEquals("FAIL Re-SIN! SIN: " + 200 + " already exists.", msg);
    }

    @Test
    public void testRePassword() {
        String msg;
        String ann = "Ann";

        //Found user and replaced
        dataAccess.rePassword(ann, "abc");
        assertEquals("abc", dataAccess.getUserByName(ann).getPWD());

        // Not Found the user, replace failed
        msg = dataAccess.rePassword("Dave", "abc");
        assertEquals("FAIL! No user named: Dave", msg);
    }

    @Test
    public void testAddJob() {
        Job job;
        String msg;

        // Added a new job
        job = new Job("d", "Full stack");
        msg = dataAccess.addJob(job);
        assertEquals("Success! New job: Full stack, URL: 'd'", msg);
        assertEquals(4, dataAccess.getJobs().size());

        // Duplicate url job, added failed
        msg = dataAccess.addJob(job);
        assertEquals("FAILED! Position: Full stack, URL: 'd' already exists.", msg);
    }

    @Test
    public void testGetJobs() {
        assertEquals(3, dataAccess.getJobs().size());
    }

    @Test
    public void testGetJobByURL() {
        String a1 = "a1";
        String d4 = "d4";

        // Found job
        assertEquals(a1, dataAccess.getJobByURL(a1).getURL());

        //No such job
        assertNull(dataAccess.getJobByURL(d4));
    }

    @Test
    public void testIsMatchJob() {
        Job job = new Job("a1", "Front-end Dev");

        // Found job URL = 1 Pos = 1
        assertTrue(dataAccess.isMatchJob(job.getURL(), job.getPosition()));

        // Not find job, URL=0 Pos =0
        job = new Job("d4", "Full stack");
        assertFalse(dataAccess.isMatchJob(job.getURL(), job.getPosition()));

        // URL=0 Pos =1
        job = new Job("d4", "Front-end Dev");
        assertFalse(dataAccess.isMatchJob(job.getURL(), job.getPosition()));

        //URL=1 Pos =0
        job = new Job("a1", "Full stack");
        assertFalse(dataAccess.isMatchJob(job.getURL(), job.getPosition()));
    }

    @Test
    public void testRePosition() {
        String msg;
        String a1 = "a1";

        // Found job, updated position
        msg = dataAccess.rePosition(a1, "Back-end");
        assertEquals("Success! Updated position.", msg);
        assertEquals("Back-end", dataAccess.getJobByURL(a1).getPosition());

        // not found job, update fails
        msg = dataAccess.rePosition("d4", "Back-end");
        assertEquals("FAIL! Not found position: Back-end", msg);
    }

    @Test
    public void testReURL() {
        String msg;
        String a1 = "a1";
        String b2 = "b2";
        String c3 = "c3";
        String d4 = "d4";

        // Found url, update success
        msg = dataAccess.reURL(a1, d4);
        assertEquals("Success! Updated URL.", msg);

        // Found url, new URL duplicated
        msg = dataAccess.reURL(d4, b2);
        assertEquals("FAIL! URL: 'b2' already exists.", msg);

        // Not found URL, updates failed
        msg = dataAccess.reURL(a1, c3);
        assertEquals("FAIL! No URL: 'a1'", msg);
    }

    @Test
    public void testDeleteJob() {
        String a1 = "a1";

        //found job, delete success
        assertTrue(dataAccess.deleteJob(a1));

        // not find job, delete failed
        assertFalse(dataAccess.deleteUser(a1));
    }

    @Test
    public void testClearJobs(){
        assertEquals(3,dataAccess.getJobs().size());
        dataAccess.clearJobs();
        assertEquals(0,dataAccess.getJobs().size());
    }
}
