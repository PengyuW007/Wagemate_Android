package pengyuw007.wagemate.persistence;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
//        DataAccessRealTest.class,
        DataAccessStubTest.class
})
public class PersistenceTests {
}
