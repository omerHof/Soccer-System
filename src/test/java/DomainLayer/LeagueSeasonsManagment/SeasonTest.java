package DomainLayer.LeagueSeasonsManagment;

import DBLayer.DataBase;
import DomainLayer.SystemLogic.DBLocal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeasonTest {

    DBLocal dbLocal = DBLocal.getInstance();
    DataBase test = new DataBase();
    League league;
    Season season;


   /* *//**AssociationRepresentative in String**//*

    ArrayList<String> stringRepresentatives = new ArrayList<>();
    String representative1 = "representative1";
    String representative2 = "representative2";
    String representative3 = "representative3";
    String representative4 = "representative4";
    String representative5 = "representative5";

    *//**Referee in String**//*

    ArrayList<String> stringReferees = new ArrayList<>();
    String referee1 = "referee1";
    String referee2 = "referee2";
    String referee3 = "referee3";
    String referee4 = "referee4";
    String referee5 = "referee5";
    String referee6 = "referee6";
    String referee7 = "referee7";
    String referee8 = "referee8";
    String referee9 = "referee9";
    String referee10 = "referee10";
    String referee11 = "referee11";
    String referee12 = "referee12";
    String referee13 = "referee13";
    String referee14 = "referee14";
    String referee15 = "referee15";
    */
/*

    */
/**DomainLayer.Teams in String**//*


    ArrayList<String> stringTeams = new ArrayList<>();
    String barca = "barca";
    String real = "real";
    String man_u = "man u";
    String man_city = "man city";
    String liverpool = "liverpool";
    String chelsea = "chelsea";
    String roma = "roma";
    String juve = "juve";
    String milan = "milan";
    String inter = "inter";

    League league;
    Season season;

    */
/**Policies-score**//*

    IScorePolicy scorePolicy = new RegularScorePolicy();
    private IGameInlayPolicy gameInlayPolicy;


    */
/**DomainLayer.Teams**//*

    ArrayList<Team> teamsAssignTest = new ArrayList<>();
    Team a = new Team("barca");
    Team b = new Team("real");
    Team c = new Team("man u");
    Team d = new Team("man city");
    Team e = new Team("liverpool");
    Team f = new Team("chelsea");
    Team g = new Team("roma");
    Team h = new Team("juve");
    Team i = new Team("milan");
    Team j = new Team("inter");

    private ArrayList<Referee> referees;
    private ArrayList<AssociationRepresentative> representatives;
    private Referee referee1;
    private Referee referee2;
    private Referee referee3;
    private Referee referee4;
    private Referee referee5;
    private Referee referee6;
    private Referee referee7;
    private Referee referee8;
    private Referee referee9;
    private Referee referee10;
    private Referee referee11;
    private Referee referee12;
    private Referee referee13;
    private Referee referee14;
    private Referee referee15;
    private MainReferee mainReferee1;
    private MainReferee mainReferee2;
    private MainReferee mainReferee3;
    private MainReferee mainReferee4;
    private MainReferee mainReferee5;

    private AssociationRepresentative representative;

    private AssociationRepresentative representative1;
    private AssociationRepresentative representative2;
    private AssociationRepresentative representative3;
    private AssociationRepresentative representative4;
    private AssociationRepresentative representative5;

    private Administrator administrator;
*/

    @Before
    public void setUp() throws Exception {

        league =  dbLocal.getLeague("Champions league");
        season = league.getAllSeasons().get(0);

        //game = dbLocal.getLeague("Champions league").getSeasonByYear(2020).getAllGames().get(1).get(0);

        /*stringRepresentatives.add(representative1);
        stringRepresentatives.add(representative2);
        stringRepresentatives.add(representative3);
        stringRepresentatives.add(representative4);
        stringRepresentatives.add(representative5);

        stringReferees.add(referee1);
        stringReferees.add(referee2);
        stringReferees.add(referee3);
        stringReferees.add(referee4);
        stringReferees.add(referee5);
        stringReferees.add(referee6);
        stringReferees.add(referee7);
        stringReferees.add(referee8);
        stringReferees.add(referee9);
        stringReferees.add(referee10);
        stringReferees.add(referee11);
        stringReferees.add(referee12);
        stringReferees.add(referee13);
        stringReferees.add(referee14);
        stringReferees.add(referee15);
*/
/*

        stringTeams.add(barca);
        stringTeams.add(real);
        stringTeams.add(man_u);
        stringTeams.add(man_city);
        stringTeams.add(liverpool);
        stringTeams.add(chelsea);
        stringTeams.add(roma);
        stringTeams.add(juve);
        stringTeams.add(milan);
        stringTeams.add(inter);

        league =  dbLocal.getLeague("Champions league");
        //season = league.getAllSeasons().get(0);

        teamsAssignTest.add(a);
        teamsAssignTest.add(b);
        teamsAssignTest.add(c);
        teamsAssignTest.add(d);
        teamsAssignTest.add(e);
        teamsAssignTest.add(f);
        teamsAssignTest.add(g);
        teamsAssignTest.add(h);
        teamsAssignTest.add(i);
        teamsAssignTest.add(j);

        */
/**DomainLayer.Users**//*

        referees = new ArrayList<>();
        referee1 = new Referee("referee1", "a", "a", "a", "a");
        referees.add(referee1);
        referee2 = new Referee("referee2", "a", "a", "a", "a");
        referees.add(referee2);
        referee3 = new Referee("referee3", "a", "a", "a", "a");
        referees.add(referee3);
        referee4 = new Referee("referee4", "a", "a", "a", "a");
        referees.add(referee4);
        referee5 = new Referee("referee5", "a", "a", "a", "a");
        referees.add(referee5);
        referee6 = new Referee("referee6", "a", "a", "a", "a");
        referees.add(referee6);
        referee7 = new Referee("referee7", "a", "a", "a", "a");
        referees.add(referee7);
        referee8 = new Referee("referee8", "a", "a", "a", "a");
        referees.add(referee8);
        referee9 = new Referee("referee9", "a", "a", "a", "a");
        referees.add(referee9);
        referee10 = new Referee("referee10", "a", "a", "a", "a");
        referees.add(referee10);
        referee11 = new Referee("referee11", "a", "a", "a", "a");
        referees.add(referee11);
        referee12 = new Referee("referee12", "a", "a", "a", "a");
        referees.add(referee12);
        referee13 = new Referee("referee13", "a", "a", "a", "a");
        referees.add(referee13);
        referee14 = new Referee("referee14", "a", "a", "a", "a");
        referees.add(referee14);
        referee15 = new Referee("referee15", "a", "a", "a", "a");
        referees.add(referee15);
        mainReferee1 = new MainReferee("mainReferee1", "a", "a", "a", "a");
        mainReferee2 = new MainReferee("mainReferee2", "a", "a", "a", "a");
        mainReferee3 = new MainReferee("mainReferee3", "a", "a", "a", "a");
        mainReferee4 = new MainReferee("mainReferee4", "a", "a", "a", "a");
        mainReferee5 = new MainReferee("mainReferee5", "a", "a", "a", "a");

        referees.add(mainReferee1);
        referees.add(mainReferee2);
        referees.add(mainReferee3);
        referees.add(mainReferee4);
        referees.add(mainReferee5);


        representatives = new ArrayList<>();
        representative1 = new AssociationRepresentative("representative1", "a", "a", "a");
        representative2 = new AssociationRepresentative("representative2", "a", "a", "a");
        representative3 = new AssociationRepresentative("representative3", "a", "a", "a");
        representative4 = new AssociationRepresentative("representative4", "a", "a", "a");
        representative5 = new AssociationRepresentative("representative5", "a", "a", "a");

        representatives.add(representative1);
        representatives.add(representative2);
        representatives.add(representative3);
        representatives.add(representative4);
        representatives.add(representative5);

        administrator = new Administrator("The King", "1234", "Oren Hason", "OrenHason@gmail.com");

        gameInlayPolicy = new OneRoundGamePolicy(teamsAssignTest, 2020);
*/

/*
        dbTest = DBLocal.getInstance();
        mainSystem = MainSystem.getInstance();
        assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");


        user= new Administrator("The King","1234","Oren Hason","OrenHason@gmail.com");
        league= new League("premier league",10);
        seasons = new ArrayList<>();
        teams = new ArrayList<>();
        referees= new ArrayList<>();
        representatives = new ArrayList<>();
        gameInlayPolicy = new TwoRoundsGamePolicy(teams,2020);
        scorePolicy = new RegularScorePolicy();

        policy= new RegularScorePolicy();

        referee1 = new Referee("a","a","a","a","a");
        referees.add(referee1);
        referee2 = new Referee("a","a","a","a","a");
        referees.add(referee2);
        referee3 = new Referee("a","a","a","a","a");
        referees.add(referee3);
        mainReferee = new MainReferee("a","a","a","a","a");
        DBLocal.getInstance().setUser(mainReferee);
        representative = new AssociationRepresentative("a","a","a","a");
        representatives.add(representative);

        statisticsA= new Statistics(policy);
        a.setStatistics(statisticsA);
        teams.add(a);
        statisticsB= new Statistics(policy);
        b.setStatistics(statisticsB);
        teams.add(b);

        season = new Season(2020,teams,referees,representatives,scorePolicy.getName(),gameInlayPolicy.getName());
        seasons.add(season);
        league.setAllSeasons(seasons);

        dbLocal.setUser(user);
        dbLocal.setLeague(league);
        dbLocal.setTeam(a);
        dbLocal.setTeam(b);

        a.setBudget(100);*/
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getYear() {

       assertEquals(season.getYear(), 2020);
    }

    @Test
    public void getiGameInlayPolicy() {

        IGameInlayPolicy onePolicy = season.getiGameInlayPolicy();
        assertTrue(onePolicy instanceof OneRoundGamePolicy);

    }
    @Test
    public void getiScorePolicy() {
    }

    @Test
    public void assignUsersToGames() {

        //Season testSeason = new Season(2021, teamsAssignTest, referees, representatives, scorePolicy.getName(), gameInlayPolicy.getName());
        //assertNotNull(testSeason); //בעיה עם הטבלה!
    }


    @Test
    public void setAllTeams() {
    }

    @Test
    public void setAllRepresentatives() {
    }

    @Test
    public void getAllGames() {
    }

    @Test
    public void setAllGames() {
    }

    @Test
    public void setiGameInlayPolicy() {
    }

    @Test
    public void setAllReferees() {
    }

    @Test
    public void getAllTeams() {
    }

    @Test
    public void getAllReferees() {
    }

    @Test
    public void getAllRepresentatives() {
    }

    @Test
    public void setIScorePolicy() {
    }
}