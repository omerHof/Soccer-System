package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PlayerTest {
   Player p1;
   Player p2;
    Team t1;
    Team t2;
    DBLocal dbtest;




    @Before
    public void setUp() throws Exception {
       // LocalDateTime date1 = LocalDateTime.now();
        LocalDate localDate = LocalDate.now(); //creating LocalDate instance
        LocalDate localDate2 = LocalDate.of(1999,1,1);
        p1 = new Player("cr7","777","cristiano ronaldo","cr7@gmail.com",localDate,"striker");
        p2 = new Player("neymarrrr","7757","neymar","nnn@gmail.com",localDate2,"striker");
        t1=new Team("juventus");
        t2=new Team("psg");
        dbtest = DBLocal.getInstance();
        dbtest.addTeam(t1);
        dbtest.addTeam(t2);
        dbtest.addUser(p1);
        dbtest.addUser(p2);
    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeTeam(t1.getName());
        dbtest.removeTeam(t2.getName());
        dbtest.removeUser(p1.getUserName());
        dbtest.removeUser(p2.getUserName());
    }

    @Test
    public void createPersonalPage() {

        ///before adding player to team
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1.getName());
        assertEquals(null,p1.getCurrentTeam());

        ///after adding player to team

        t1.addPlayer(p1);
        assertEquals(p1.getCurrentTeam(),t1);


        /*
        t1.addPlayer(p1);
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1.getName());
        assertFalse(ronaldoPage.getCurrentTeam().getName()=="Manchester city");
        assertEquals(ronaldoPage.getName(),"cristiano ronaldo");

         */
    }

    @Test
    public void getAge() {
        assertEquals(p2.getAge(),21);
        assertEquals(p1.getAge(),0);
    }



    @Test
    public void setCourtRole() {
        assertEquals(p1.getCourtRole(),"striker");
        p1.setCourtRole("midelfer");
        assertFalse(p1.getCourtRole()=="striker");
    }

    @Test
    public void getCourtRole() {
        assertEquals(p1.getCourtRole(),"striker");
        p1.setCourtRole("midelfer");
        assertFalse(p1.getCourtRole()=="striker");
    }

    @Test
    public void getSalary() {
        assertEquals(0,p1.getSalary());
        assertEquals(p1.getSalary(),p2.getSalary());
    }

    @Test
    public void setSalary() {
        p1.setSalary(100000);
        assertFalse(p1.getSalary()==p2.getSalary());



    }


    @Test
    public void getPage() {
        //before crtating a page
        assertEquals(p1.getPage(),null);

        //after
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1.getName());
        assertFalse(p1.getPage()==null);
        t1.addPlayer(p1);

        assertEquals(p1.getPage().getCurrentTeam(),t1);

    }

    @Test
    public void setCurrentTeam() {
        //before adding player to team
        assertEquals(p1.getCurrentTeam(),null);

        //after adding player to team
        t1.addPlayer(p1);
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1.getName());
        assertEquals(p1.getCurrentTeam(),t1);
        t2.addPlayer(p1);
        assertEquals(p1.getCurrentTeam(),t2);
        assertEquals(ronaldoPage.getCurrentTeam(),t2);
    }

    @Test
    public void getTeamHistory() {
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1.getName());
        t1.addPlayer(p1);
        t2.addPlayer(p1);
        t1.addPlayer(p1);
        assertTrue(p1.getTeamHistory().contains(t1.getName()));
        assertTrue(p1.getTeamHistory().contains(t2.getName()));


    }


}