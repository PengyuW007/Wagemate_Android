package pengyuw007.wagemate.business;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        AccessUsersTest.class,
        AccessJobsTest.class,
        CalculateWageTest.class
})
public class BusinessTests {

}
