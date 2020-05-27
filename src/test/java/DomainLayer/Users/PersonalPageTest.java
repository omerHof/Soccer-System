package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PersonalPageTest {

    Coach c1;
    Coach c2;
    Coach c3;
    Team t1;
    Team t2;
    Team t3;
    DBLocal dbtest;
    PersonalPage pepPage;

    @Before
    public void setUp() throws Exception {
        dbtest= DBLocal.getInstance();
        c1 = new Coach("pep","12345","pep guardiola","pep@gmail.com","Head coach");
        c2 = new Coach("klopp","12345","jorgen klopp","klopp@gmail.com","Head coach");
        c3 = new Coach("kling","12345","nir klinger","nk@gmail.com","Head coach");
        t1=new Team("Manchester city");
        t2=new Team("Liverpool");
        t3=new Team("Hapoel tel aviv");
        LocalDate localDate2 = LocalDate.of(1999,1,1);
        dbtest.addTeam(t1);
        dbtest.addTeam(t2);
        dbtest.addTeam(t3);
        dbtest.addUser(c1);
        dbtest.addUser(c2);
        dbtest.addUser(c3);

        pepPage = c1.createCoachPersonalPage(localDate2,t1.getName());
        t1.addCoach(c1);



        dbtest.addTeam(t1);
        dbtest.addTeam(t2);
        dbtest.addTeam(t3);
        dbtest.addUser(c1);
        dbtest.addUser(c2);
        dbtest.addUser(c3);
    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeUser(c1.getUserName());
        dbtest.removeUser(c2.getUserName());
        dbtest.removeUser(c3.getUserName());
        dbtest.removeTeam(t1.getName());
        dbtest.removeTeam(t2.getName());
        dbtest.removeTeam(t3.getName());
        pepPage=null;
    }

    @Test
    public void getName() {
        assertEquals(pepPage.getName(),c1.getUserFullName());

    }

    @Test
    public void getCurrentTeam() {
        assertEquals(pepPage.getCurrentTeam(),t1);

    }

    @Test
    public void setName() {
        assertEquals(pepPage.getName(),c1.getUserFullName());

        //after change
        pepPage.setName("Peps");
        assertFalse(pepPage.getName().equals("pep guardiola"));
    }

    @Test
    public void setCurrentTeam() {
        t2.addCoach(c1);
        assertEquals(t2,c1.getPage().getCurrentTeam());
    }

    @Test
    public void getAge() {
        assertEquals(pepPage.getAge(),21);
    }

    @Test
    public void getTeamHistory() {
        assertEquals(c1.getTeamHistory(),pepPage.getTeamHistory());
    }

    @Test
    public void setTeamHistory() {
        ArrayList<String>teams = new ArrayList<>();
        teams.add("Liverpool");
        teams.add("Hapoel tel aviv");
        c1.setTeamHistory(teams);
        assertEquals(3,pepPage.getTeamHistory().size());
    }

    @Test
    public void setOneTeamToHistory() {
        t2.addCoach(c1);
        assertEquals(pepPage.getTeamHistory().size(),c1.getTeamHistory().size());
    }
}