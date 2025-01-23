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

    @Test
    public void testAddUser() {
        ArrayList<User>users;
        User user;

        users = dataAccess.getUsers();


    }

    @Test
    public void testGetUserByName() {

    }
}
