package pengyuw007.wagemate;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RunUnitTests {

    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
//        suite.addTest(ObjectTests.suite());
//        suite.addTest(BusinessTests.suite());
//        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}