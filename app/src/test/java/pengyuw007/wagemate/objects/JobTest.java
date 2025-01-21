package pengyuw007.wagemate.objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    @Test
    public void testJob(){
        Job job;

        System.out.println("\nStarting testJob");

        job = new Job("ab12","Java Developer");
        assertNotNull(job);
        assertEquals("ab12",job.getURL());
        assertEquals("Java Developer",job.getPosition());

        System.out.println("Finished testJob");
    }
}
