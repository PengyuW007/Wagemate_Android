package pengyuw007.wagemate.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pengyuw007.wagemate.application.Main;
import pengyuw007.wagemate.application.Services;

import pengyuw007.wagemate.objects.User;

import pengyuw007.wagemate.persistence.stub.DataAccessStub;

public class AccessUsersTest {
    private static String dbName = Main.dbName;

    @Test
    public void testAccess(){
        User user;

        Services.closeDataAccess();

        System.out.println("\nStarting test AccessUsers");

//        Services.createDataAccess(new DataAccessStub(dbName));
    }
}
