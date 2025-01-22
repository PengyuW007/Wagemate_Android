package pengyuw007.wagemate.persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pengyuw007.wagemate.persistence.stub.DataAccessStub;

public class DataAccessStubTest {
    private IPersistenceAccess dataAccess;

    public void setUp(){
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");
    }

    public void tearDown() {
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    @Test
    public void testAddUser(){

    }
}
