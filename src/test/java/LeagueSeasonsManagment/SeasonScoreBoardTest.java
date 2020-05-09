package LeagueSeasonsManagment;

import DataForTest.DataBase;
import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SeasonScoreBoardTest {

    private DB db;
   // private AssociationRepresentative assoTest;
    private MainSystem mainSystem;
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {
        db = DB.getInstance();
        mainSystem = MainSystem.getInstance();
        //assoTest = new AssociationRepresentative("YS", "123", "Yiftah Szoke", "yszoke@gmail.com");
        game = db.getLeague("Alufot").getAllSeasons().get(0).getAllGames().get(1).get(0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sortByValue() {
        try{
            //season.getSeasonScoreBoard().sortByValue();
            Thread.sleep(30000);
            System.out.println();
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void showTable() {
        try{

        }catch (Exception e) {
            System.out.println("error");
        }
    }
}