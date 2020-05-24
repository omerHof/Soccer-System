package LeagueSeasonsManagment;

import DataBase.DataBase;
import Games.Game;
import SystemLogic.DBLocal;
import SystemLogic.MainSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SeasonScoreBoardTest {

    private DBLocal dbLocal;
   // private AssociationRepresentative assoTest;
    private MainSystem mainSystem;
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {
        dbLocal = DBLocal.getInstance();
        mainSystem = MainSystem.getInstance();
        //assoTest = new AssociationRepresentative("YS", "123", "Yiftah Szoke", "yszoke@gmail.com");
        game = dbLocal.getLeague("Alufot").getAllSeasons().get(0).getAllGames().get(1).get(0);
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