package DomainLayer.UserGenerator;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Users.AssociationRepresentative;
import DomainLayer.Users.Fan;
import DomainLayer.Users.Referee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleUserGeneratorTest {

    private DBLocal dbLocalTest = DBLocal.getInstance();
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

        assertNull(dbLocalTest.getUserType("Fan")); //not exists yet.

        Fan testFan = (Fan)sim.generate("talish", "927364929", "", "Fan", "tali schvartz", "tali@tali", null, "crossfit", "", "");
        //assertTrue(dbLocalTest.getUserByFullName("tali schvartz") instanceof Fan);
        assertFalse(dbLocalTest.getUserByFullName("tali schvartz") instanceof Referee);
        assertNotNull(testFan);

    }
}