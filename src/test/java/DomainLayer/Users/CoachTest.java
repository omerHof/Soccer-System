package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CoachTest {
    Coach c1;
    Coach c2;
    Coach c3;
    Team t1;
    Team t2;
    Team t3;
    DBLocal dbtest;
    LocalDate localDate;

    @Before
    public void setUp() throws Exception {
        dbtest= DBLocal.getInstance();
         c1 = new Coach("pep","12345","pep guardiola","pep@gmail.com","Head coach");
        c2 = new Coach("klopp","12345","jorgen klopp","klopp@gmail.com","Head coach");
        c3 = new Coach("kling","12345","nir klinger","nk@gmail.com","Head coach");
        t1=new Team("Manchester city");
        t2=new Team("Liverpool");
        t3=new Team("Hapoel tel aviv");
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

    }

    @Test
    public void createPersonalPage() {
        t1.addCoach(c1);
        LocalDate localDate = LocalDate.of(1970,4,4);
        PersonalPage pepPage = c1.createCoachPersonalPage(localDate,t1.getName());
        assertEquals(pepPage.getCurrentTeam().getName(),"Manchester city");
        assertEquals(pepPage.getName(),c1.getUserFullName());

        /*
        Date date = new Date();

        CoachPersonalPage page = new CoachPersonalPage("pep guardiola",date,"Head coach",t1 );
        c1.setPage(page);
        assertEquals(page.getCurrentTeam().getName(),"Manchester city");

         */

    }

    @Test
    public void getUserName() {
        assertEquals(c1.getUserName(),"pep");
        c1.setUserName("pepep");
        assertFalse(c1.getUserName().equals("pep"));
        assertTrue(c1.getUserName().equals("pepep"));


    }

    @Test
    public void setUserName() {

        c1.setUserName("pepo");
        assertEquals(c1.getUserName(),"pepo");
        /*
        c2.userName="jk1";
        assertEquals(c1.getUserName(),"jk1");

         */

    }


    @Test
    public void getTeamRole() {
        assertEquals(c1.getTeamRole(),"Head coach");
        assertEquals(c1.getTeamRole(),c2.getTeamRole());
    }

    @Test
    public void setTeamRole() {
        c1.setTeamRole("general manager");
        assertFalse(c1.getTeamRole().equals(c2.getTeamRole()));
        c1.setTeamRole("Head coach");
        assertEquals(c1.getTeamRole(),c2.getTeamRole());

    }

    @Test
    public void getSalary() {
        assertEquals(0,c1.getSalary());
        assertEquals(c1.getSalary(),c2.getSalary());
    }

    @Test
    public void setSalary() {
        c1.setSalary(100000);
        assertFalse(c1.getSalary()==c2.getSalary());



    }


    @Test
    public void getPage() {
        //before crtating a page
        LocalDate localDate = LocalDate.of(1970,4,4);
        assertEquals(c1.getPage(),null);

        //after
        PersonalPage pepPage = c1.createCoachPersonalPage(localDate,t1.getName());
        assertFalse(c1.getPage()==null);
        assertEquals(c1.getPage().getCurrentTeam(),t1);
        
    }

    @Test
    public void setCurrentTeam() {
        //before crtating a page
        assertEquals(c1.getCurrentTeam(),null);

        //after adding team
        t1.addCoach(c1);
        //PersonalPage pepPage = c1.createCoachPersonalPage(localDate,t1.getName());
        assertEquals(c1.getCurrentTeam(),t1);
        assertEquals(c1.getCurrentTeam().getName(),t1.getName());

    }

}