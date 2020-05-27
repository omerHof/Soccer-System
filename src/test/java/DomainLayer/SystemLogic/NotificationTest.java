package DomainLayer.SystemLogic;

import DomainLayer.Users.Fan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationTest {
    Fan fan1;
    Fan fan2;
    @Before
    public void setUp() throws Exception {
        fan1 = new Fan("f", "1234", "f f", "f.com");
        fan2 = new Fan("g", "1234", "g g", "g.com");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void send() {
        Notification notification = new Notification(fan1, "hello", fan2);
        notification.send();
        assertTrue(fan2.isNonReadNotifications());
    }

    @Test
    public void read() {
        Notification notification = new Notification(fan2, "goodbye", fan1);
        notification.send();
        String str = notification.read();
        assertEquals(str, "goodbye");
        assertFalse(fan1.isNonReadNotifications());
    }
}