package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Team;
import DomainLayer.Teams.TeamPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FanTest {


    Fan f1;
    Team t1;
    Player p1;
    Coach c1;
    DBLocal DBLocalTest;






    /*
    unlock this when we want to check notifications about games!!!!! and lock the others
    DBLocal dbLocal = DBLocal.getInstance();
    DBLayer test = new DBLayer();

     */

    @Before
    public void setUp() throws Exception {

        LocalDate localDate2 = LocalDate.of(1999,1,1);
        f1 = new Fan("ido747","12345","ido kestenbaum","ido747@gmail.com");
        t1 = new Team("hapoel tel aviv");
        p1 = new Player("messi","12345","leo messi","leo123@gmail.com",localDate2,"striker");
        c1 = new Coach("klinger","123","nir klinger","nir123@gmail.com","head coach");
        DBLocalTest = DBLocal.getInstance();
        DBLocalTest.addUser(f1);
        DBLocalTest.addUser(p1);
        DBLocalTest.addUser(c1);
        DBLocalTest.addTeam(t1);












    }

    @After
    public void tearDown() throws Exception {

       DBLocalTest.removeUser(f1.getUserName());
        DBLocalTest.removeUser(p1.getUserName());
        DBLocalTest.removeUser(c1.getUserName());
        DBLocalTest.removeTeam(t1.getName());
        f1 =null;
        t1=null;
        p1=null;
        c1=null;


    }




    @Test
    public void update() {

        Team barca = new Team("barcelona");
        DBLocalTest.addTeam(barca);
        Team real = new Team("real madrid");
        DBLocalTest.addTeam(real);

        barca.createPage("team in spain","spain");
        real.createPage("team in spain","spain");
       t1.createPage("team in israel","israel");
        TeamPage barcaPage=barca.getPage();
        TeamPage realPage=real.getPage();
        TeamPage hapoelPage=t1.getPage();


       TeamOwner hapoelOwner = new TeamOwner("aa","aa","nisanov","dsdsadas");
        TeamOwner barcaOwner = new TeamOwner("arrrra","aa","bartulemeo","dsdsadffas");
        TeamOwner realOwner = new TeamOwner("222222","aa","peres","dsdsadffas");
        DBLocalTest.addUser(hapoelOwner);
        DBLocalTest.addUser(barcaOwner);
        DBLocalTest.addUser(realOwner);
        t1.addTeamOwner(hapoelOwner.getUserFullName());
        barca.addTeamOwner(barcaOwner.getUserFullName());
        real.addTeamOwner(realOwner.getUserFullName());

        PlayerPersonalPage messiPage = p1.createPersonalPage(167,65,10,barca.getName());
        LocalDate date = LocalDate.of(1965,12,12);
        CoachPersonalPage klingerPage =c1.createCoachPersonalPage(date,null);




        ////followed player move team
        f1.followThisPage(p1.getUserFullName());

        t1.addPlayer(p1.getUserFullName());
        //p1.setCurrentTeam(t1.getName());
        assertEquals(f1.getReceivedNotifications().get(0).getContext(),"new update! leo messi has moved to hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().size(),1);
        barca.addPlayer(p1.getUserFullName());
        assertEquals(f1.getReceivedNotifications().get(1).getContext(),"new update! leo messi has moved to barcelona");



        ////followed coach move team

        f1.followThisPage(c1.getUserFullName());
        barca.addCoach(c1);
        assertEquals(f1.getReceivedNotifications().get(2).getContext(),"new update! nir klinger has moved to barcelona");
        assertEquals(barca.getPlayers().size(),1);
        assertEquals(barca.getCoaches().size(),1);
        assertEquals(t1.getCoaches().size(),0);
        assertEquals(t1.getPlayers().size(),0);

        f1.stopFollowAllPages();
        assertEquals(f1.getFollowedPages().size(),0);











        ///follow team
        f1.followTeam(t1.getName());
        t1.addPlayer(p1);
        assertEquals(f1.getReceivedNotifications().get(3).getContext(),"new update! the player leo messi has moved to hapoel tel aviv");
        t1.addCoach(c1.getUserFullName());
        assertEquals(f1.getReceivedNotifications().get(4).getContext(),"new update! the coach nir klinger has moved to hapoel tel aviv");
        Stadium stadium = new Stadium(12222,12222,12222);
        stadium.setName("bloomfield");
        t1.setStadium(stadium);
        assertEquals(f1.getReceivedNotifications().get(5).getContext(),"new update! the team hapoel tel aviv has move to the stadium "+stadium.getName());


        Manager manager = new Manager("ssss","dsds","managerr","fsdfsdf@gmail.com");
        DBLocalTest.addUser(manager);
        t1.addManager(manager);
        assertEquals(f1.getReceivedNotifications().get(6).getContext(),"new update! the manager managerr has moved to hapoel tel aviv");
        f1.followTeam(barca.getName());
        barca.addPlayer(p1);
        assertEquals(f1.getReceivedNotifications().get(7).getContext(),"new update! the player leo messi has left the team hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().get(8).getContext(),"new update! the player leo messi has moved to barcelona");



        barca.addCoach(c1);
        assertEquals(f1.getReceivedNotifications().get(9).getContext(),"new update! the coach nir klinger has left the team hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().get(10).getContext(),"new update! the coach nir klinger has moved to barcelona");

        Stadium stadium2 = new Stadium(12222,12222,12222);
        stadium2.setName("bloomfield2");
        t1.setStadium(stadium2);
        assertEquals(f1.getReceivedNotifications().get(11).getContext(),"new update! the team hapoel tel aviv has move to the stadium "+stadium2.getName());


        barca.addManager(manager);
        assertEquals(f1.getReceivedNotifications().get(12).getContext(),"new update! the manager managerr has left  hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().get(13).getContext(),"new update! the manager managerr has moved to barcelona");
        barca.removeManager(manager.getUserFullName());

        assertEquals(f1.getReceivedNotifications().get(14).getContext(),"new update! the manager managerr has left  barcelona");
        assertEquals(f1.getReceivedNotifications().get(14).getSender().getUserFullName(),"bartulemeo");







        /////notifications of games!!! unlock when you want to test this!
        /*
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = dbLocal.getUserByFullName("ido kest");
        Fan fan = (Fan)user;
        Team t1 = fan.getFollowedTeams().get("man u");
        assertTrue(fan.getReceivedNotifications().size()>0);

         */


    }


    @Test
    public void getFollowedPages() {

        t1.addPlayer(p1);
        p1.createPersonalPage(167,65,10,t1.getName());
        t1.addCoach(c1.getUserFullName());
        f1.followThisPage(p1.getUserFullName());
        assertEquals(1,f1.getFollowedPages().size());
        f1.followThisPage(c1.getUserFullName());
        assertEquals(1,f1.getFollowedPages().size());


    }

    @Test
    public void followThisPage() {
        f1.followThisPage(p1.getUserFullName());
        assertEquals(f1.getFollowedPages().size(),0);

        //after create pages:

        p1.createPersonalPage(167,65,10,t1.getName());
        f1.followThisPage(p1.getUserFullName());
        assertEquals(f1.getFollowedPages().size(),1);


    }

    @Test
    public void followThisPage1() {

        f1.followThisPage(p1.getPage());
        assertEquals(f1.getFollowedPages().size(),0);

        //after create pages:

        p1.createPersonalPage(167,65,10,t1.getName());
        f1.followThisPage(p1.getPage());
        assertEquals(f1.getFollowedPages().size(),1);

    }

    @Test
    public void stopFollowThisPage() {

        p1.createPersonalPage(167,65,10,t1.getName());
        f1.followThisPage(p1.getPage());
        f1.stopFollowThisPage(p1.getUserFullName());
        assertEquals(f1.getFollowedPages().size(),0);
        f1.stopFollowThisPage(c1.getUserFullName());
        assertEquals(f1.getFollowedPages().size(),0);


        f1.followThisPage(p1.getPage());
        LocalDate date = LocalDate.of(1965,12,12);
        CoachPersonalPage klingerPage =c1.createCoachPersonalPage(date,null);
        f1.stopFollowThisPage(c1.getUserFullName());
        assertEquals(f1.getFollowedPages().size(),1);

        f1.stopFollowThisPage("noname");
        assertEquals(f1.getFollowedPages().size(),1);


    }

    @Test
    public void stopFollowAllPages() {
        p1.createPersonalPage(167,65,10,t1.getName());
        f1.followThisPage(p1.getPage());
        LocalDate date = LocalDate.of(1965,12,12);
        CoachPersonalPage klingerPage =c1.createCoachPersonalPage(date,null);
        f1.followThisPage(c1.getUserFullName());
        assertEquals(f1.getFollowedPages().size(),2);
        f1.stopFollowAllPages();
        assertEquals(f1.getFollowedPages().size(),0);
        f1.stopFollowAllPages();
        assertEquals(f1.getFollowedPages().size(),0);


    }

    @Test
    public void followTeam() {
        f1.followTeam("pardesia");
        assertEquals(0,f1.getFollowedTeams().size());
        f1.followTeam(t1.getName());
        assertEquals(0,f1.getFollowedTeams().size());
        t1.createPage("play in red","israel");
        f1.followTeam(t1.getName());
        assertEquals(1,f1.getFollowedTeams().size());



    }

    @Test
    public void stopFollowTeam() {
        t1.createPage("play in red","israel");
        f1.followTeam(t1.getName());
        Team t2 = new Team("maccabi tel aviv");
        DBLocalTest.addTeam(t2);
        t2.createPage("play in yellow","israel");
        f1.followTeam(t2.getName());
        assertEquals(2,f1.getFollowedTeams().size());
        f1.stopFollowTeam("maccabi tel aviv");
        assertEquals(1,f1.getFollowedTeams().size());
        f1.stopFollowTeam("test, hope not working");
        assertEquals(1,f1.getFollowedTeams().size());
        f1.stopFollowTeam(null);
        assertEquals(1,f1.getFollowedTeams().size());




    }

    @Test
    public void stopFollowAllTeams() {
        t1.createPage("play in red","israel");
        f1.followTeam(t1.getName());
        Team t2 = new Team("maccabi tel aviv");
        DBLocalTest.addTeam(t2);
        t2.createPage("play in yellow","israel");
        f1.followTeam(t2.getName());
        assertEquals(2,f1.getFollowedTeams().size());
        f1.stopFollowAllTeams();
        assertEquals(0,f1.getFollowedTeams().size());
        f1.stopFollowAllTeams();
        assertEquals(0,f1.getFollowedTeams().size());

    }


    @Test
    public void getFollowedTeams() {
        assertEquals(0,f1.getFollowedTeams().size());
        t1.createPage("play in red","israel");
        f1.followTeam(t1.getName());
        Team t2 = new Team("maccabi tel aviv");
        DBLocalTest.addTeam(t2);
        t2.createPage("play in yellow","israel");
        f1.followTeam(t2.getName());
        assertEquals(2,f1.getFollowedTeams().size());

    }



}