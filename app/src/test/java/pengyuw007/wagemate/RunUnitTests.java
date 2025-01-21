package pengyuw007.wagemate;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import pengyuw007.wagemate.objects.ObjectTests;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@Suite
@SelectClasses({
        ObjectTests.class // Add your test suites or individual test classes here
        // BusinessTests.class,   // Uncomment when needed
        // PersistenceTests.class // Uncomment when needed
})

public class RunUnitTests {

}