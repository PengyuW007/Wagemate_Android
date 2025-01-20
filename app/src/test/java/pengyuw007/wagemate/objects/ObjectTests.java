package pengyuw007.wagemate.objects;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ObjectTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Object tests");
        suite.addTestSuite(JobTest.class);
        suite.addTestSuite(UserTest.class);
        return suite;
    }
}
