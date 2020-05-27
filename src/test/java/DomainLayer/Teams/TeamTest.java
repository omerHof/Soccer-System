package DomainLayer.Teams;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Users.Coach;
import DomainLayer.Users.Manager;
import DomainLayer.Users.Player;
import DomainLayer.Users.TeamOwner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeamTest {
    Team hapoel;
    Team maccabi;
    Player vermut;
    Player zahavi;
    Coach klinger;
    DBLocal dbtest;



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
        dbtest = DBLocal.getInstance();
        dbtest.addTeam(hapoel);
        dbtest.addTeam(maccabi);
        dbtest.addUser(vermut);
        dbtest.addUser(zahavi);
        dbtest.addUser(klinger);

    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeUser(zahavi.getUserName());
        dbtest.removeUser(vermut.getUserName());
        dbtest.removeUser(klinger.getUserName());
        dbtest.removeTeam(maccabi.getName());
        dbtest.removeTeam(hapoel.getName());


    }

    @Test
    public void createPage() {

        //before
        assertEquals(null,hapoel.getPage());

        //after
        hapoel.createPage("team in israel, play in red","israel");
        assertFalse(hapoel.getPage()==null);
        assertEquals(hapoel.getPage().getName(),hapoel.getName());

    }

    @Test
    public void getPage() {

        hapoel.createPage("team in israel, play in red","israel");
        maccabi.createPage("team in israel, play in yellow","israel");
        assertFalse(hapoel.getPage()==maccabi.getPage());

    }

    @Test
    public void setStatus() {
        maccabi.setStatus(Team.teamStatus.close);
        assertEquals(maccabi.getStatus(), Team.teamStatus.close);
        maccabi.setStatus(Team.teamStatus.active);
        assertEquals(maccabi.getStatus(),hapoel.getStatus());

    }

    @Test
    public void addPlayer() {
        ///before
        assertEquals(hapoel.getPlayers().size(),0);

        ///after
        hapoel.addPlayer(vermut);
        assertEquals(hapoel.getPlayers().size(),1);
        hapoel.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),2);

        maccabi.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),1);
        assertEquals(maccabi.getPlayers().size(),1);



    }

    @Test
    public void removePlayer() {

        ///before
        assertEquals(hapoel.getPlayers().size(),0);

        ///after
        hapoel.addPlayer(vermut);
        assertEquals(hapoel.getPlayers().size(),1);
        hapoel.removePlayer(vermut);
        assertEquals( vermut.getCurrentTeam(),null);
        assertEquals(hapoel.getPlayers().size(),0);


        /*
        hapoel.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),2);
        maccabi.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),1);
        assertEquals(maccabi.getPlayers().size(),1);
        System.out.println(zahavi.getCurrentTeam().getName());

         */

    }

    @Test
    public void addCoach() {
        ///before
        assertEquals(hapoel.getCoaches().size(),0);

        //aftet
        hapoel.addCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),1);

        maccabi.addCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),0);
        assertEquals(maccabi.getCoaches().size(),1);



    }

    @Test
    public void removeCoach() {
        assertEquals(hapoel.getCoaches().size(),0);

        //aftet
        hapoel.addCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),1);
        hapoel.removeCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),0);




    }

    @Test
    public void addManager() {
        Manager manager = new Manager("aa","aa","new manager","aaaa");
        hapoel.addManager(manager);
        assertEquals(hapoel.getManagers().size(),0);
        dbtest.addUser(manager);
        hapoel.addManager(manager);
        assertEquals(hapoel.getManagers().size(),1);
        maccabi.addManager(manager);
        assertEquals(hapoel.getManagers().size(),0);
        assertEquals(maccabi.getManagers().size(),1);



    }

    @Test
    public void removeManager() {
        Manager manager = new Manager("aa","aa","new manager","aaaa");
        dbtest.addUser(manager);
        hapoel.addManager(manager);
        hapoel.removeManager(manager);
        assertEquals(hapoel.getManagers().size(),0);
    }

    @Test
    public void addTeamOwner() {
        TeamOwner teamOwner = new TeamOwner("leva","rerer","lev lebaiev","rere");
        hapoel.addTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),0);
        dbtest.addUser(teamOwner);
        hapoel.addTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),1);
        maccabi.addTeamOwner(teamOwner.getUserFullName());
        assertEquals(hapoel.getTeamOwners().size(),0);
        assertEquals(maccabi.getTeamOwners().size(),1);


    }

    @Test
    public void removeTeamOwner() {
        TeamOwner teamOwner = new TeamOwner("leva","rerer","lev lebaiev","rere");
        dbtest.addUser(teamOwner);
        hapoel.addTeamOwner(teamOwner.getUserFullName());
        assertEquals(hapoel.getTeamOwners().size(),1);
        hapoel.removeTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),0);

    }


    @Test
    public void getStadium() {

        Stadium stadium = new Stadium(20000,15000,10000000);
        hapoel.setStadium(stadium);
        assertEquals(hapoel.getStadium().getCapacity(),15000);
    }

    @Test
    public void setStadium() {
        Stadium stadium = new Stadium(20000,15000,10000000);
        hapoel.setStadium(stadium);
        assertEquals(hapoel.getStadium().getCapacity(),15000);
        Stadium stadium2 = new Stadium(20000,30000,10000000);
        hapoel.setStadium(stadium2);
        assertEquals(hapoel.getStadium().getCapacity(),30000);

    }



    @Test
    public void getGameList() {
        assertEquals(hapoel.getGameList().size(),0);
        LocalDateTime dateGame = LocalDateTime.of(2020,12,12,17,00);
        Game g = new Game(hapoel,maccabi,dateGame);
        hapoel.addGame(g);
        maccabi.addGame(g);
        assertEquals(hapoel.getGameList().size(),1);


    }

    @Test
    public void setGameList() {
        assertEquals(hapoel.getGameList().size(),0);
        LocalDateTime dateGame = LocalDateTime.of(2020,12,12,17,00);
        Game g = new Game(hapoel,maccabi,dateGame);
        ArrayList<Game>list = new ArrayList<>();
        list.add(g);
        hapoel.setGameList(list);
        maccabi.setGameList(list);

        assertEquals(hapoel.getGameList().size(),1);
    }

    @Test
    public void getName() {
        assertEquals(hapoel.getName(),"hapoel tel aviv");
    }

    @Test
    public void setName() {
        maccabi.setName("macccabi");
        assertFalse(maccabi.getName()=="maccabi tel aviv");
        assertEquals(maccabi.getName(),"macccabi");
    }

    @Test
    public void addGame() {
        assertEquals(hapoel.getGameList().size(),0);
        LocalDateTime dateGame = LocalDateTime.of(2020,12,12,17,00);
        Game g = new Game(hapoel,maccabi,dateGame);
        hapoel.addGame(g);
        maccabi.addGame(g);
        assertEquals(hapoel.getGameList().size(),1);
    }

    @Test
    public void getStatus() {
        assertEquals(hapoel.getStatus(), Team.teamStatus.active);
    }

    @Test
    public void getBudget() {
        assertTrue(hapoel.getBudget()==0);

    }

    @Test
    public void setBudget() {
        hapoel.setBudget(1);
        assertTrue(hapoel.getBudget()==1);
    }


}