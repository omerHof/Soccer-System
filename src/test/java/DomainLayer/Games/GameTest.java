package DomainLayer.Games;

import DBLayer.DataBase;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameTest {

    DBLocal dbLocal = DBLocal.getInstance();
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {

        game = dbLocal.getLeague("Champions league").getSeasonByYear(2020).getAllGames().get(1).get(0);
        //dbLocal.getTeam("barca").setBudget(100);
        //double money = dbLocal.getLeague("Champions league").getSeasonByYear(2020).getSeasonScoreBoard().getTeamByName("barca").getBudget();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setAlarms() {
    }

    @Test
    public void getTimeOfGame() {
        try {
            LocalDateTime time= LocalDateTime.of(2020, Month.JANUARY,1,19,0,0);
            assertEquals("not same time", time, game.getTimeOfGame());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setTimeOfGame() {
        try {
            LocalDateTime dateTime = LocalDateTime.of(2020, Month.FEBRUARY, 8, 18, 0, 0);
            game.setTimeOfGame(dateTime);
            assertEquals("not same time", dateTime, game.getTimeOfGame());
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getHomeTeam() {
        try {
            assertNotEquals("getHomeTeam return null", null, game.getHomeTeam());
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getAwayTeam() {
        try {
            assertNotEquals("getHomeTeam return null", null, game.getAwayTeam());
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getGameDate() {

    }

    @Test
    public void getStatus() {
        try {
            game.setStatus(Game.gameStatus.active);
            assertEquals("status is not pre", Game.gameStatus.active, game.getStatus());
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getScore() {
        try {
            game.setScore("3-2");
            assertEquals("not 3-2", "3-2", game.getScore());

        } catch (Exception e) {
            System.out.println("error");
        }

    }

    @Test
    public void getGameReferees() {
        try {
            //assertTrue("not 4 refereees",game.getGameReferees().size()==4);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getRepresentative() {
    }

    @Test
    public void getEventBook() {
        try {
            Event event = new Event(Event.eventType.goal, 80, "messi");
            ArrayList<Event> eventBook = new ArrayList<>();
            eventBook.add(event);
            game.setEventBook(eventBook);
            assertEquals("didnt set", game.getEventBook().get(0).getPlayerName(), "messi");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setEventBook() {
        try {
            Event event = new Event(Event.eventType.goal, 80, "messi");
            ArrayList<Event> eventBook = new ArrayList<>();
            eventBook.add(event);
            game.setEventBook(eventBook);
            assertEquals("didnt set", game.getEventBook().get(0).getPlayerName(), "messi");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getFinalReport() {
    }

    @Test
    public void setFinalReport() {
        try {
            game.setFinalReport("final report");
            assertEquals("didnt set", "final report", game.getFinalReport());

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setHomeTeam() {
        try {
            Team team = new Team("haifa");
            game.setHomeTeam(team);
            assertEquals("didnt set", "haifa", game.getHomeTeam().getName());

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setAwayTeam() {
        try {
            Team team = new Team("haifa");
            game.setAwayTeam(team);
            assertEquals("didnt set", "haifa", game.getAwayTeam().getName());

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setGameDate() {
    }

    @Test
    public void setScore() {
        try {
            game.setScore("3-2");
            assertEquals("not 3-2", "3-2", game.getScore());

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setGameReferees() {
    }

    @Test
    public void setStatus() {
        try {
            game.setStatus(Game.gameStatus.active);
            assertEquals("didnt set", Game.gameStatus.active, game.getStatus());

        } catch (Exception e) {
            System.out.println("error");
        }

    }

    @Test
    public void addEvent() {
    }
}