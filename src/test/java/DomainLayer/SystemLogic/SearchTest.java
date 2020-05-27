package DomainLayer.SystemLogic;

import DBLayer.DataBase;
import DomainLayer.Users.AssociationRepresentative;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchTest {

    private DBLocal dbLocal;
    private AssociationRepresentative assoTest;
    private MainSystem mainSystem;

    DataBase test = new DataBase();
    Search search;
    List<Object> ans;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void search() {

        Search search3 = new Search("milan", Search.Category.teams);
        ans = search3.search();
        assertEquals(ans.size(), 1);

        Search search4 = new Search("milan", null);
        ans = search4.search();
        assertEquals(ans.size(), 1);
    }

    @Test
    public void searchByCategory() {

        Search search2 = new Search("ver2", Search.Category.players);
        ans = search2.searchByCategory();
        assertEquals(ans.size(), 1); //only one player using this name.

        Search search4 = new Search("ver2", Search.Category.leagues);
        ans = search4.searchByCategory();
        assertEquals(ans.size(), 0); //no league names ver2 in database.

        Search search5 = new Search("ver2", Search.Category.referees);
        ans = search5.searchByCategory();
        assertEquals(ans.size(), 0); //no league names ver2 in database.


        Search search6 = new Search("real", Search.Category.teams);
        ans = search6.searchByCategory();
        assertEquals(ans.size(), 1); //only one team names real.

        Search search7 = new Search("realll", Search.Category.teams);
        ans = search7.searchByCategory();
        assertEquals(ans.size(), 0); //no team names reallll.
    }

    @Test
    public void searchAll() {

        String toSearch = "Referee";
        search = new Search(toSearch, null);

        ans = search.searchAll();
        assertEquals(ans.size(), 20); //all 20 referees in database.




    }
}