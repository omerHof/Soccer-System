package DomainLayer.UserGenerator;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Users.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PremiumUserGenertatorTest {

    private DBLocal dbLocalTest = DBLocal.getInstance();
    private AssociationRepresentative assoTest = new AssociationRepresentative("aa", "", "alal", "a@a");
    private MainSystem mainSystem = MainSystem.getInstance();
    private PremiumUserGenerator preG = new PremiumUserGenerator();

    @Before
    public void setUp() throws Exception {
        dbLocalTest.addUser(assoTest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generate() {

        Player testPlayer = (Player) preG.generate("p1", "94929", "", "player", "Aviv Alush", "tali@aviv", null, "", "balam", "");
        assertNotNull(testPlayer);

        Manager testManager = (Manager) preG.generate("m1", "94929", "", "manager", "omer hof", "o@o", null, "", "", "");
        assertNotNull(testManager);

        Coach testCoach = (Coach) preG.generate("c1", "94929", "", "coach", "roi katz", "r@r", null, "none", "", "whattt");
        assertNotNull(testCoach);

        TeamOwner testTeamOwner = (TeamOwner) preG.generate("o1", "94", "", "teamOwner", "ido katsan", "i@i", null, "", "", "");
        assertNotNull(testTeamOwner);

        Referee testReferee = (Referee) preG.generate("r1", "9", "", "referee", "tali me", "t@t", null, "what??", "", "");
        assertNotNull(testReferee);

        assertTrue((testReferee) instanceof Referee);
        assertTrue((testPlayer) instanceof Player);
        assertTrue((testTeamOwner) instanceof TeamOwner);
        assertTrue((testCoach) instanceof Coach);
        assertTrue((testManager) instanceof Manager);

    }

    @Test
    public void whichUserAmI() {
    }

    @Test
    public void askForApproval() {
    }
}