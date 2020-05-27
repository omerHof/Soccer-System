package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TeamOwnerTest {
    static TeamOwner teamOwner1;
    static TeamOwner teamOwner2;
    static TeamOwner teamOwner3;
    static Team team;
    static Team team2;
    static Team team3;
    static DBLocal dbLocal = DBLocal.getInstance();


    @Before
    public  void setUp() throws Exception {
        teamOwner1 = new TeamOwner("teamOwner1", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner2 = new TeamOwner("teamOwner2", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner3 = new TeamOwner("teamOwner3", "1234", "teamOwnerName", "teamOwner.com");
//        teamOwner4 = new TeamOwner("teamOwner4", "1234", "teamOwnerName", "teamOwner.com");
        AssociationRepresentative associationRepresentative = new AssociationRepresentative("a", "1234", "a b", "a.com");
        dbLocal.addUser(associationRepresentative);
        dbLocal.setUser(teamOwner1);
        dbLocal.setUser(teamOwner2);
        dbLocal.setUser(teamOwner3);
        teamOwner1.askPermissionToOpenTeam();
        teamOwner2.askPermissionToOpenTeam();
        teamOwner3.askPermissionToOpenTeam();
        teamOwner1.openTeam("Arnon Sturm Graz", 200000);
        team = dbLocal.getTeam("Arnon Sturm Graz");
        teamOwner2.openTeam("Celtic veBenel", 300000);
        team2 = dbLocal.getTeam("Celtic veBenel");
        teamOwner3.openTeam("Pablo Rozenburg", 400000);
        team3 = dbLocal.getTeam("Pablo Rozenburg");
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void openTeam() {
        assertNotNull(team);
        assertTrue(team.getTeamOwners().containsKey("teamOwner1"));
        assertEquals(team, teamOwner1.getTeam());

        teamOwner1.closeTeam();
        assertEquals(team.getStatus(), Team.teamStatus.close);
        Stadium stadium = new Stadium(100000,50000,100000);
        String try_to_add_assent = teamOwner1.addAssent(stadium, 10);
        assertEquals(try_to_add_assent, "team is closed");

        teamOwner1.openTeam("Arnon Sturm Graz", 16);
        assertEquals(team.getStatus(), Team.teamStatus.active);
        try_to_add_assent = teamOwner1.addAssent(stadium, 10);
        assertEquals(try_to_add_assent, "added successfully");
    }

    @Test
    public void addAssent() {
        Coach coach = new Coach("coach","1234", "coachName", "coach.com", "coach_that_coaches");
        String try_to_add_assent = teamOwner1.addAssent(coach, 600000);
        assertEquals(try_to_add_assent, "added successfully");
        try_to_add_assent = teamOwner1.addAssent(coach, 600000);
        assertEquals(try_to_add_assent, "already added");
        Coach coach2 = new Coach("coach2","1234", "coachName", "coach.com", "coach_that_coaches");
        try_to_add_assent = teamOwner1.addAssent(coach2, 600000);
        assertEquals(try_to_add_assent, "money isn't growing on the trees!");
    }

    @Test
    public void removeAssent() {
        Stadium stadium2 = new Stadium(100000,50000,100000);
        stadium2.setName("Ha'Urva");
        String try_to_remove = teamOwner2.removeAssent(stadium2);
        assertEquals(try_to_remove, "not in the team");
        teamOwner2.addAssent(stadium2, 120);
        try_to_remove = teamOwner2.removeAssent(stadium2);
        assertEquals(try_to_remove, "removed successfully");
    }

    @Test
    public void changeAssentWorth() {
        Coach coach = new Coach("coach","1234", "coachName", "coach.com", "coach_that_coaches");
        teamOwner2.addAssent(coach, 600000);
        assertTrue(coach.getWorth()== 600000);
        teamOwner2.changeAssentWorth(coach, 450000);
        assertTrue(coach.getWorth()== 450000);
    }

    @Test
    public void appoint() {
        Coach coach = new Coach("owner","1234", "coachName", "coach.com", "coach_that_coaches");
        Coach coach2 = new Coach("manager","1234", "coachName", "coach.com", "coach_that_coaches");
        teamOwner2.addAssent(coach, 100000);
        teamOwner2.addAssent(coach2, 100000);
        teamOwner2.appoint(coach, "teamowner", 15);
        teamOwner2.appoint(coach2, "manager", 14);
        assertTrue(teamOwner2.getTeam_owners_appointments().containsKey("owner"));
        assertTrue(teamOwner2.getManagers_appointments().containsKey("manager"));
        User user1 = dbLocal.getUser("owner");
        User user2 = dbLocal.getUser("manager");
        assertTrue(user1 instanceof TeamOwner);
        assertTrue(user2 instanceof Manager);
        assertEquals(((TeamOwner)user1).getTeam(), team2);
        assertEquals(((Manager)user2).getTeam(), team2);
        assertTrue(user1.isNonReadNotifications());
        assertTrue(user2.isNonReadNotifications());
    }

    @Test
    public void removeAppointmentTeamOwner() {
        Coach coach = new Coach("zimri","1234", "coachName", "coach.com", "coach_that_coaches");
        Coach coach2 = new Coach("manager","1234", "coachName", "coach.com", "coach_that_coaches");
        Coach coach3 = new Coach("another_owner","1234", "coachName", "coach.com", "coach_that_coaches");
        dbLocal.addUser(coach);
        dbLocal.addUser(coach2);
        dbLocal.addUser(coach3);
        teamOwner3.appoint(coach, "teamowner", 15);
        TeamOwner zimri = (TeamOwner) dbLocal.getUser("zimri");
        zimri.appoint(coach2, "manager",16);
        zimri.appoint(coach3, "teamowner",17);
        teamOwner3.removeAppointmentTeamOwner(zimri);
        User fan1 = dbLocal.getUser("zimri");
        User fan2 = dbLocal.getUser("manager");
        User fan3 = dbLocal.getUser("another_owner");
        assertTrue(fan1 instanceof Fan);
        assertTrue(fan2 instanceof Fan);
        assertTrue(fan3 instanceof Fan);
    }

    @Test
    public void reportFinance() {
        double budget = teamOwner1.getTeam().getBudget();
        teamOwner1.reportFinance(15000, "income", false, null);
        budget += 15000;
        assertTrue(budget == teamOwner1.getTeam().getBudget());
        Coach coach = new Coach("zimri","1234", "coachName", "coach.com", "coach_that_coaches");
        teamOwner1.reportFinance(25000, "outcome", true, coach);
        assertTrue(coach.isNonReadNotifications());
        String surprise = coach.getReceivedNotifications().get(0).read();
        assertEquals(surprise, "You have been paid 25000.0 gogo'im");
        assertFalse(coach.isNonReadNotifications());


    }




}