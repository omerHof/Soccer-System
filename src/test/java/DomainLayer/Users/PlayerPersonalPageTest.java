package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PlayerPersonalPageTest {
    Player p1;
    DBLocal dbLocal = DBLocal.getInstance();
    PlayerPersonalPage playerPage;

    @Before
    public void setUp() throws Exception {
        LocalDate date = LocalDate.of(1994,7,1);
        p1 = new Player("anat4kosh","1234","anat forkosh","anat@gmail.com",date,"GK");
        playerPage = p1.createPersonalPage(164,50,1,"maccabi netanya");
        dbLocal.addUser(p1);


    }

    @After
    public void tearDown() throws Exception {
        dbLocal.removeUser(p1.getUserName());
        p1=null;
    }

    @Test
    public void getHeight() {
        assertEquals(p1.getHeight(),playerPage.getHeight());
    }

    @Test
    public void setHeight() {
        p1.setHeight(167);
        assertEquals(p1.getHeight(),playerPage.getHeight());
    }

    @Test
    public void getWeight() {
        assertEquals(p1.getWeight(),playerPage.getWeight());

    }

    @Test
    public void setWeight() {
        p1.setWeight(51);
        assertEquals(p1.getWeight(),playerPage.getWeight());

    }

    @Test
    public void getShirtNumber() {
        assertEquals(p1.getNumberOfShirt(),playerPage.getShirtNumber());
    }

    @Test
    public void setShirtNumber() {
        p1.setNumberOfShirt(2);
        assertEquals(p1.getNumberOfShirt(),playerPage.getShirtNumber());
    }

    @Test
    public void getPosition() {
        assertEquals(p1.getCourtRole(),playerPage.getPosition());
    }

    @Test
    public void setPosition() {
        p1.setCourtRole("defender");
        assertEquals(p1.getCourtRole(),playerPage.getPosition());

    }
}