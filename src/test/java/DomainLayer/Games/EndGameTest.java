package DomainLayer.Games;

import DBLayer.DataBase;
import DomainLayer.SystemLogic.DBLocal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class EndGameTest {
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
    public void run() {
        try {
            System.out.println(LocalDateTime.now());
            System.out.println(game.getTimeOfGame());
            System.out.println(game.getStatus());

            Thread.sleep(5000);
            System.out.println(game.getStatus());
            Thread.sleep(3000);
            System.out.println(game.getStatus());
            assertEquals("status is not active", Game.gameStatus.finish, game.getStatus());
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}