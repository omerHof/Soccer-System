package DomainLayer.SystemLogic;

import DBLayer.DataBase;
import DomainLayer.Games.Game;
import DomainLayer.LeagueSeasonsManagment.League;
import DomainLayer.Teams.Team;
import DomainLayer.Users.Administrator;
import DomainLayer.Users.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBLocalTest {
    DBLocal dbLocal = DBLocal.getInstance();
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {
        game = dbLocal.getLeague("Champions league").getSeasonByYear(2021).getAllGames().get(1).get(0);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getResultsInstance() {
        try {
            DBLocal.getInstance();
            DBLocal dbLocal1 = DBLocal.getInstance();
            assertEquals("need to be equals", dbLocal, dbLocal1);
        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void getUser() {
        try {
            assertEquals("test failed", "referee1", dbLocal.getUser("referee1").getUserName());
            assertEquals("test failed",null, dbLocal.getUser("The Queen"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getUserByFullName() {
        try {
            assertEquals("test failed", "a", dbLocal.getUserByFullName("a").getUserFullName());
            assertEquals("test failed",null, dbLocal.getUserByFullName("The Queen"));
        }catch (Exception e){
            System.out.println("test failed");
        }

    }

    @Test
    public void userExist() {
        try {
            assertTrue("test failed", dbLocal.userExist("The King"));
            assertFalse("test failed", dbLocal.userExist("The Queen"));
        }catch (Exception e){
            System.out.println("test failed");
        }

    }
    @Test
    public void setUser() {
        try {
            User goodUser = new Administrator("testUser", "1111", "good","e");
            User badUser = new Administrator("testUser", "1111", "bad","e");

            dbLocal.setUser(goodUser);
            dbLocal.setUser(badUser);
            assertEquals("test failed",goodUser.getUserName(), dbLocal.getUserByFullName("good").getUserName());
            assertEquals(null, dbLocal.getUserByFullName("bad"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void addUser() {
        try {
            User goodUser = new Administrator("testUser", "1111", "good", "e");

            assertTrue("test failed", dbLocal.addUser(goodUser));
            assertFalse("test failed", dbLocal.addUser(null));
            assertFalse("test failed", dbLocal.addUser(goodUser));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void removeUser() {
        try {
            assertTrue("test failed", dbLocal.removeUser("The King"));
            assertFalse("test failed", dbLocal.removeUser("The King"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getLeague() {
        try {
            assertEquals("test failed", "Champions league", dbLocal.getLeague("Champions league").getName());
            assertEquals("test failed",null, dbLocal.getLeague("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void leagueExist() {
        try {
            assertTrue("test failed", dbLocal.leagueExist("Champions league"));
            assertFalse("test failed", dbLocal.leagueExist("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void setLeague() {
        try {
            League goodLeague = new League("A", 6);
            //League badLeague = new League("A", 6);

            dbLocal.setLeague(goodLeague);
            //dbLocal.setLeague(badLeague);
            assertEquals("test failed", goodLeague.getName(), dbLocal.getLeague("A").getName());
            //assertNotEquals("test failed", badLeague, dbLocal.getLeague("A"));
        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void addLeague() {
        try {
            League goodLeague = new League("A", 6);

            assertTrue("test failed", dbLocal.addLeague(goodLeague));
            assertFalse("test failed", dbLocal.addLeague(null));
            assertFalse("test failed", dbLocal.addLeague(goodLeague));

        } catch (Exception e) {
            System.out.println("test failed");
        }
    }
    @Test
    public void addSeason() {

    }

    @Test
    public void removeLeague() {
        try {
            assertTrue("test failed", dbLocal.removeLeague("Champions league"));
            assertFalse("test failed", dbLocal.removeLeague("Champions league"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getTeam() {
        try {
            assertEquals("test failed", "barca", dbLocal.getTeam("barca").getName());
            assertEquals("test failed",null, dbLocal.getTeam("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void teamExist() {
        try {
            assertTrue("test failed", dbLocal.teamExist("barca"));
            assertFalse("test failed", dbLocal.teamExist("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void setTeam() {
        try {
            Team goodTeam = new Team("A");
            //Team badTeam = new Team("A");

            dbLocal.setTeam(goodTeam);
            //dbLocal.setTeam(badTeam);
            assertEquals("test failed", goodTeam.getName(), dbLocal.getTeam("A").getName());
            //assertNotEquals("test failed", badTeam.getName(), dbLocal.getTeam("A").getName());
        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void addTeam() {
        try {
            Team goodTeam = new Team("A");

            assertTrue("test failed", dbLocal.addTeam(goodTeam));
            assertFalse("test failed", dbLocal.addTeam(null));
            assertFalse("test failed", dbLocal.addTeam(goodTeam));

        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void removeTeam() {
        try {
            assertTrue("test failed", dbLocal.removeTeam("barca"));
            assertFalse("test failed", dbLocal.removeTeam("barca"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getUserType() {
        try {
            User result= dbLocal.getUserType("Administrator");
            assertNotEquals("test failed",null,result);

        }
        catch (Exception e){
            System.out.println("test failed");
        }
    }


}