package pengyuw007.wagemate.objects;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void testUser() {
        User user;

        System.out.println("\nStarting testUser");

        user = new User(123, "A", "awd");
        assertNotNull(user);
        assertEquals(123, user.getSin());
        assertEquals("A", user.getName());
        assertEquals("awd", user.getPWD());

        user.setSin(456);
        assertEquals(456, user.getSin());

        user.setName("B");
        assertEquals("B", user.getName());

        user.setPWD("awd");
        assertEquals("awd", user.getPWD());

        System.out.println("Finished testUser");
    }
}
