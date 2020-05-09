package UserGenerator;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Users.AssociationRepresentative;
import Users.Fan;
import Users.Referee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleUserGeneratorTest {

    private DB dbTest = DB.getInstance();
    private AssociationRepresentative assoTest;
    private MainSystem mainSystem = MainSystem.getInstance();
    private SimpleUserGenerator sim = new SimpleUserGenerator();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generate() {

        //AssociationRepresentative a1 = new AssociationRepresentative("a1", "aa", "yiftah", "szoke@szoke");

        assertNull(dbTest.getUserType("Fan")); //not exists yet.

        Fan testFan = (Fan)sim.generate("talish", "927364929", "", "Fan", "tali schvartz", "tali@tali", null, "crossfit", "", "");
        //assertTrue(dbTest.getUserByFullName("tali schvartz") instanceof Fan);
        assertFalse(dbTest.getUserByFullName("tali schvartz") instanceof Referee);
        assertNotNull(testFan);

    }
}