package DomainLayer.Users;

import DBLayer.DataBase;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {
    TeamOwner teamOwner1;
    Team team;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void complain() {
        teamOwner1 = new TeamOwner("teamOwner1", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner1.setPermission(true);
        teamOwner1.openTeam("Arnon Sturm Graz", 200000);
        team = DBLocal.getInstance().getTeam("Arnon Sturm Graz");
        Manager manager = new Manager("m", "1234", "m m", "m.com");
        teamOwner1.addAssent(manager, 10000);
        Fan fan = new Fan("f", "1234", "f f", "f.com");
        fan.complain("you suck", team);
        String surprise = manager.getReceivedNotifications().get(0).read();
        assertEquals(surprise, "you suck");
    }



    @Test
    public void search() {
        DataBase test = new DataBase();
        User user = new Fan("","","","");
        List<Object> ans =  user.search("milan", "notCategory");
        assertEquals(ans.size(), 1);
    }
}