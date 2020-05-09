package SystemLogic;

import DataForTest.DataBase;
import Games.Game;
import LeagueSeasonsManagment.League;
import Teams.Team;
import Users.Administrator;
import Users.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBTest {
    DB db = DB.getInstance();
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {
        game = db.getLeague("Champions league").getSeasonByYear(2021).getAllGames().get(1).get(0);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getResultsInstance() {
        try {
            DB.getInstance();
            DB db1 = DB.getInstance();
            assertEquals("need to be equals", db, db1);
        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void getUser() {
        try {
            assertEquals("test failed", "referee1", db.getUser("referee1").getUserName());
            assertEquals("test failed",null,db.getUser("The Queen"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getUserByFullName() {
        try {
            assertEquals("test failed", "a", db.getUserByFullName("a").getUserFullName());
            assertEquals("test failed",null,db.getUserByFullName("The Queen"));
        }catch (Exception e){
            System.out.println("test failed");
        }

    }

    @Test
    public void userExist() {
        try {
            assertTrue("test failed",db.userExist("The King"));
            assertFalse("test failed",db.userExist("The Queen"));
        }catch (Exception e){
            System.out.println("test failed");
        }

    }
    @Test
    public void setUser() {
        try {
            User goodUser = new Administrator("testUser", "1111", "good","e");
            User badUser = new Administrator("testUser", "1111", "bad","e");

            db.setUser(goodUser);
            db.setUser(badUser);
            assertEquals("test failed",goodUser.getUserName(),db.getUserByFullName("good").getUserName());
            assertEquals(null,db.getUserByFullName("bad"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void addUser() {
        try {
            User goodUser = new Administrator("testUser", "1111", "good", "e");

            assertTrue("test failed",db.addUser(goodUser));
            assertFalse("test failed",db.addUser(null));
            assertFalse("test failed",db.addUser(goodUser));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void removeUser() {
        try {
            assertTrue("test failed",db.removeUser("The King"));
            assertFalse("test failed",db.removeUser("The King"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getLeague() {
        try {
            assertEquals("test failed", "Champions league", db.getLeague("Champions league").getName());
            assertEquals("test failed",null,db.getLeague("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void leagueExist() {
        try {
            assertTrue("test failed", db.leagueExist("Champions league"));
            assertFalse("test failed",db.leagueExist("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void setLeague() {
        try {
            League goodLeague = new League("A", 6);
            //League badLeague = new League("A", 6);

            db.setLeague(goodLeague);
            //db.setLeague(badLeague);
            assertEquals("test failed", goodLeague.getName(), db.getLeague("A").getName());
            //assertNotEquals("test failed", badLeague, db.getLeague("A"));
        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void addLeague() {
        try {
            League goodLeague = new League("A", 6);

            assertTrue("test failed", db.addLeague(goodLeague));
            assertFalse("test failed", db.addLeague(null));
            assertFalse("test failed", db.addLeague(goodLeague));

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
            assertTrue("test failed", db.removeLeague("Champions league"));
            assertFalse("test failed", db.removeLeague("Champions league"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getTeam() {
        try {
            assertEquals("test failed", "barca", db.getTeam("barca").getName());
            assertEquals("test failed",null,db.getTeam("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void teamExist() {
        try {
            assertTrue("test failed",db.teamExist("barca"));
            assertFalse("test failed",db.teamExist("a"));
        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void setTeam() {
        try {
            Team goodTeam = new Team("A");
            //Team badTeam = new Team("A");

            db.setTeam(goodTeam);
            //db.setTeam(badTeam);
            assertEquals("test failed", goodTeam.getName(), db.getTeam("A").getName());
            //assertNotEquals("test failed", badTeam.getName(), db.getTeam("A").getName());
        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void addTeam() {
        try {
            Team goodTeam = new Team("A");

            assertTrue("test failed", db.addTeam(goodTeam));
            assertFalse("test failed", db.addTeam(null));
            assertFalse("test failed", db.addTeam(goodTeam));

        } catch (Exception e) {
            System.out.println("test failed");
        }
    }

    @Test
    public void removeTeam() {
        try {
            assertTrue("test failed",db.removeTeam("barca"));
            assertFalse("test failed",db.removeTeam("barca"));

        }catch (Exception e){
            System.out.println("test failed");
        }
    }

    @Test
    public void getUserType() {
        try {
            User result=db.getUserType("Administrator");
            assertNotEquals("test failed",null,result);

        }
        catch (Exception e){
            System.out.println("test failed");
        }
    }


}