package DomainLayer.Teams;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Users.Coach;
import DomainLayer.Users.Manager;
import DomainLayer.Users.Player;
import DomainLayer.Users.TeamOwner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TeamPageTest {
    Team hapoel;
    Team maccabi;
    Player vermut;
    Player zahavi;
    Coach klinger;
    Manager manager;
    TeamOwner owner;
    TeamPage hapoelPage;
    TeamPage maccabiPage;
    DBLocal dbtest = DBLocal.getInstance();

    @Before
    public void setUp() throws Exception {
        LocalDate date1 = LocalDate.of(1985,1,12);
        LocalDate date2 = LocalDate.of(1987,6,12);
        LocalDate date3 = LocalDate.of(1968,6,12);

        hapoel = new Team("hapoel tel aviv");
        maccabi = new Team("maccabi tel aviv");
        vermut = new Player("ver","123","gili vermut","g",date1,"midelfer");
        zahavi = new Player("ez7","123","rean zahavi","eeee",date2,"striker");
        klinger = new Coach("kling","1212","nir klinger","dsdasd","head coach");
        owner = new TeamOwner("anat4","122","anat forkosh","anat@gmail.com");
        manager = new Manager("sinay","122","mosh sinay","sinay@gmail.com");

        dbtest = DBLocal.getInstance();
        dbtest.addTeam(hapoel);
        dbtest.addTeam(maccabi);
        dbtest.addUser(vermut);
        dbtest.addUser(zahavi);
        dbtest.addUser(klinger);
        dbtest.addUser(manager);
        dbtest.addUser(owner);
       hapoel.createPage("israel","israel");
       hapoelPage= hapoel.getPage();
       maccabi.createPage("israel","israel");
       maccabiPage=maccabi.getPage();


    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeUser(zahavi.getUserName());
        dbtest.removeUser(vermut.getUserName());
        dbtest.removeUser(klinger.getUserName());
        dbtest.removeTeam(maccabi.getName());
        dbtest.removeTeam(hapoel.getName());
        dbtest.removeUser(owner.getUserName());
        dbtest.removeUser(manager.getUserName());
    }

    @Test
    public void getName() {
        assertEquals(hapoel.getName(),hapoelPage.getName());
    }

    @Test
    public void setName() {
        hapoel.setName("hapoel");
        assertEquals(hapoel.getName(),hapoelPage.getName());
    }

    @Test
    public void getPlayers() {
        hapoel.addPlayer(vermut);
        assertEquals(hapoelPage.getPlayers().size(),1);
    }

    @Test
    public void getCoaches() {
        hapoel.addCoach("nir klinger");
        assertEquals(hapoelPage.getCoaches().size(),1);
        assertEquals(hapoel.getCoaches().size(),1);
        hapoel.addCoach("dsdasdasdas");
        assertEquals(hapoelPage.getCoaches().size(),1);

    }


    @Test
    public void getManagers() {
        maccabi.addManager(manager);
        assertEquals(maccabi.getManagers(),maccabi.getPage().getManagers());
    }


    @Test
    public void getHistory() {
        assertEquals(hapoelPage.getHistory(),"israel");
    }

    @Test
    public void setHistory() {
        hapoel.getPage().setHistory("play in red");
        assertEquals(hapoelPage.getHistory(),"play in red");

    }

    @Test
    public void getStadium() {
        assertEquals(hapoel.getStadium(),hapoelPage.getStadium());
    }

    @Test
    public void setStadium() {
        Stadium s = new Stadium(2000,200000,2000);
        s.setName("bbb");
        hapoel.setStadium(s);
        assertEquals(hapoel.getStadium(),hapoelPage.getStadium());

    }

    @Test
    public void addPlayer() {
        hapoel.addPlayer("gili vermut");
        assertEquals(hapoel.getPlayers(),hapoelPage.getPlayers());
        maccabi.addPlayer("gili vermut");
        assertEquals(hapoelPage.getPlayers().size(),0);
    }

    @Test
    public void removePlayer() {
        hapoel.addPlayer("gili vermut");
        assertEquals(hapoel.getPlayers(),hapoelPage.getPlayers());
        hapoel.removePlayer("gili vermut");
        assertEquals(hapoelPage.getPlayers().size(),hapoel.getPlayers().size());
    }

    @Test
    public void addCoach() {
        hapoel.addCoach(klinger);
        assertEquals(hapoelPage.getCoaches(),hapoel.getCoaches());
    }

    @Test
    public void removeCoach() {
        hapoel.addCoach(klinger);
        hapoel.removeCoach(klinger);
        assertEquals(hapoelPage.getCoaches(),hapoel.getCoaches());
    }

    @Test
    public void addManager() {
        hapoel.addManager(manager);
        assertEquals(hapoelPage.getManagers(),hapoel.getManagers());
    }

    @Test
    public void removeManager() {
        hapoel.addManager(manager);
        hapoel.removeManager(manager);
        assertEquals(hapoelPage.getManagers(),hapoel.getManagers());
    }

    @Test
    public void getNation() {
        assertEquals(hapoelPage.getNation(),"israel");
    }

    @Test
    public void setNation() {
        maccabi.getPage().setNation("england");
        assertFalse(maccabiPage.getNation()=="israel");
    }
}