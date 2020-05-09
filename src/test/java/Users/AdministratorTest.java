package Users;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.Notification;
import Teams.Team;
import Teams.TeamPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdministratorTest {
    Administrator ad;
    Team team1;
    Team team2;
    Player player;
    Coach coach;
    TeamOwner owner;
    Referee referee;
    AssociationRepresentative assocRep;
    Manager manager;
    Fan fan;
    DB db;


    @Before
    public void setUp() throws Exception {
        ad = new Administrator("addd","bestAD","aaa","dsdasdasd");
        team1 = new Team("hapoel pardesia");
        team2 = new Team("hapoel beer sheva");

        LocalDate date1 = LocalDate.of(1985,3,3);
        player = new Player("leo","leo","leo messi","dsdsa",date1,"srtiker");
        coach = new Coach("pep","12121","pep guardiola","dsdas","head coach");
        owner = new TeamOwner("bigboss","1212","bibi","dddd");
        referee = new Referee("ref","reff","eli hakmon","eee","A");
        assocRep = new AssociationRepresentative("aa","aa","oren hason","aaaaaaa");
        manager = new Manager("bestManager","nnnn","someone","idiididi");
        fan = new Fan("ido747","1222","ido kestenbaum","ido747@gmsil.com");
        db = DB.getInstance();
        db.addTeam(team1);
        db.addTeam(team2);

        db.addUser(ad);
        db.addUser(player);
        db.addUser(coach);
        db.addUser(owner);
        db.addUser(referee);
        db.addUser(assocRep);
        db.addUser(manager);
        db.addUser(fan);



    }

    @After
    public void tearDown() throws Exception {
        db.removeUser(ad.getUserFullName());
        db.removeUser(player.getUserFullName());
        db.removeUser(coach.getUserFullName());
        db.removeUser(owner.getUserFullName());
        db.removeUser(referee.getUserFullName());
        db.removeUser(assocRep.getUserFullName());
        db.removeUser(manager.getUserFullName());
        db.removeUser(fan.getUserFullName());
        db.removeTeam(team1.getName());
        db.removeTeam(team2.getName());


    }

    @Test
    public void closeTeamForPermanent() {
        LocalDateTime date = LocalDateTime.of(2020,4,22,17,00);
        Game g = new Game(team1,team2,date);
        team1.addGame(g);
        team2.addGame(g);

        ad.closeTeamForPermanent(team1.getName());
        assertEquals(team1.getStatus(), Team.teamStatus.active);

       // ad.closeTeamForPermanent(team3.getName());
       // assertEquals(team3.getStatus(), Team.teamStatus.PermanentlyClosed);

        Team team3 = new Team("maccabi netanya");
        db.addTeam(team3);
        team3.addManager(manager);
        team3.addTeamOwner(owner);
        ad.closeTeamForPermanent(team3.getName());

        /* the norifications
         */
        ArrayList<Notification> notifications = manager.getReceivedNotifications();
        assertEquals(notifications.get(0).getContext(), "the team maccabi netanya is permanently closed" );



    }

    @Test
    public void deleteUserFromSystem() {
        /*
        AssociationRepresentative
         */
        AssociationRepresentative assocrep2 = new AssociationRepresentative("pp","pp","ppp","dsdasdas");
        db.addUser(assocrep2);
        ad.deleteUserFromSystem(assocRep.getUserFullName());
        assertEquals(null,db.getUserByFullName("oren hason"));

          /*
        administrator
         */
          ad.deleteUserFromSystem(ad.getUserFullName());
          Administrator admin = new Administrator("d","d","dddd","ddd");
          db.addUser(admin);
          ad.deleteUserFromSystem(admin.getUserFullName());
          assertEquals(1,db.checkQuantityOfUsersByType("Administrator"));



          //fan

          LocalDate date2 = LocalDate.of(1970,5,11);
          CoachPersonalPage coachPage = coach.createCoachPersonalPage(date2,team1.getName());
          PlayerPersonalPage playerPage = player.createPersonalPage(167,65,10,team1.getName());
          fan.followThisPage("pep guardiola");
          fan.followThisPage("leo messi");
          assertEquals(fan.getFollowedPages().size(),2);
          team1.createPage("non","israel");
        TeamPage teamPage =team1.getPage();
                fan.followTeam("hapoel pardesia");
          assertEquals(fan.getFollowedTeams().size(),1);
        team2.createPage("non","israel");
        TeamPage teamPage2 =team2.getPage();

        fan.followTeam(team2.getName());
        assertEquals(fan.getFollowedTeams().size(),2);
       assertEquals(teamPage.countObservers(),1);
        ad.deleteUserFromSystem(fan.getUserFullName());
        assertEquals(fan.getFollowedTeams().size(),0);

        assertEquals(teamPage.countObservers(),0);







/*
          ad.deleteUserFromSystem(fan.getUserFullName());
        assertEquals(fan.getFollowedPages().size(),0);
        assertEquals(fan.getFollowedTeams().size(),0);


 */

/*
team owner


 */
         team1.addTeamOwner(owner);
         assertEquals(ad.deleteUserFromSystem(owner.getUserFullName()),"the team has less then 2 team owners");
         assertEquals(team1.getTeamOwners().size(),1);
         TeamOwner owner2 = new TeamOwner("avi","ddsdas","abramovich","dsdsa");
        db.addUser(owner2);
        team1.addTeamOwner(owner2);
        assertEquals(team1.getTeamOwners().size(),2);
        String message = ad.deleteUserFromSystem(owner.getUserFullName());

        assertEquals(message,"the user deleted succsesfully");
        assertEquals(team1.getTeamOwners().size(),1);

       // ad.deleteUserFromSystem("abramovich");





        ///ref
        LocalDateTime date = LocalDateTime.of(2020,4,22,17,00);

        Game g = new Game(team1,team2,date);
        team1.addGame(g);
        team2.addGame(g);
        referee.addGame(g);


         message = ad.deleteUserFromSystem(referee.getUserFullName());
        assertEquals(message,"no delete, the referee has an open games");
        Referee ref2 = new Referee("dd","dd","dddd","dsdad","d");
        db.addUser(ref2);
        String message2 = ad.deleteUserFromSystem(ref2.getUserFullName());
        assertEquals(message2,"the user deleted succsesfully");




        team1.addManager(manager);
        String message5 = ad.deleteUserFromSystem(manager.getUserFullName());
          assertEquals("the user deleted succsesfully",message5);
        ///player
        team1.addPlayer(player);
        assertEquals(team1.getPlayers().size(),1);
        String message3 = ad.deleteUserFromSystem(player.getUserFullName());
        ///no delete
        assertEquals(team1.getPlayers().size(),1);

        g.setStatus(Game.gameStatus.close);
        String message33 = ad.deleteUserFromSystem(player.getUserFullName());
        assertEquals(message33,"the user deleted succsesfully");
        assertEquals(team1.getPlayers().size(),0);

        team1.addCoach(coach);
        String message4 = ad.deleteUserFromSystem(coach.getUserFullName());
        assertEquals(message4,"the user deleted succsesfully");




}
    @Test
    public void watchLog() {
        String str = ad.watchLog();
        assertTrue(str.contains("ystemLogic.MainSystem"));
       // System.out.println(str);
    }
}