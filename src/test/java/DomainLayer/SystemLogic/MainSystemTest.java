package DomainLayer.SystemLogic;

import DomainLayer.UserGenerator.SimpleUserGenerator;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainSystemTest {
    private static MainSystem mainSystem;
    private static DBLocal data_base;

    @BeforeClass
    public static void beforeClass() {
        data_base = DBLocal.getInstance();
        mainSystem = MainSystem.getInstance();
    }

    @AfterClass
    public static void afterClass() {
        data_base = null;
        mainSystem = null;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void AGetInstance() {
        MainSystem same1 = MainSystem.getInstance();
        MainSystem same2 = MainSystem.getInstance();
        assertSame(same1, same2);
    }

    @Test
    public void BInitializeSystem() {

    }

    @Test
    public void CSingUp() {
        SimpleUserGenerator simpleUserGenerator = new SimpleUserGenerator();

        String result = mainSystem.singUp("Kroi","kod66666", "", "",
                "Roi Katz","roik16@gmail.com", null, "", "", "",
                simpleUserGenerator);
        assertEquals(result, "signed up and logged in");
        assertTrue(data_base.userExist("Kroi"));
        assertEquals(mainSystem.getCurrentUser().getUserName(),"Kroi" );

        String result2 = mainSystem.singUp("carlo","kod11111", "", "",
                "Roberto Carlos","carlos@gmail.com", null, "", "", "",
                simpleUserGenerator);

        assertEquals(result2, "signed up already");//todo: cahnge to only
        assertTrue(data_base.userExist("carlo"));
        assertEquals(mainSystem.getCurrentUser().getUserName(),"Kroi" );

    }

    @Test
    public void ELogIn() {
        String str1 = mainSystem.logIn("noExist","doesn't matter");
        assertEquals(str1, "name");

        String str2 = mainSystem.logIn("carlo","wrong_password");
        assertEquals(str2, "password");

        String str3 = mainSystem.logIn("carlo","kod11111");
        assertEquals(str3, "logged in");

        String str4 = mainSystem.logIn("kroi","kod66666");
        assertEquals(str4, "occupied");
    }

    @Test
    public void DLogOut() {
        mainSystem.logOut();
        assertNull(mainSystem.getCurrentUser());
    }
}
