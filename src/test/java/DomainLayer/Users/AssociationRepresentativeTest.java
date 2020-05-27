package DomainLayer.Users;

import DomainLayer.Games.Event;
import DomainLayer.Games.Game;
import DomainLayer.LeagueSeasonsManagment.*;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Teams.Statistics;
import DomainLayer.Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AssociationRepresentativeTest {

    private DBLocal dbLocal;
    private AssociationRepresentative assoTest;
    private MainSystem mainSystem;

   // DBLayer test = new DBLayer();

    private List<Team> allTeams = new LinkedList<>();

    Referee r1 = new Referee("r1", "rr", "bla bla", "ziv@ziv", "none");
    AssociationRepresentative a1 = new AssociationRepresentative("a1", "aa", "yiftah", "szoke@szoke");

/**AssociationRepresentative in String**/
    ArrayList<String> stringRepresentatives = new ArrayList<>();

/**Referee in String**/

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

    String mainRefereee1 = "mainReferee1";
    String mainRefereee2 = "mainReferee2";
    String mainRefereee3 = "mainReferee3";
    String mainRefereee4 = "mainReferee4";
    String mainRefereee5 = "mainReferee5";

    Player vermut;
    Player zahavi;
    Player aa;
    Player bb;
    Player cc;
    Player dd;
    Player ee;
    Player ff;
    Player gg;
    Player hh;

    Coach klinger;
    Coach aaa;
    Coach bbb;
    Coach ccc;
    Coach ddd;
    Coach eee;
    Coach fff;
    Coach ggg;
    Coach hhh;
    Coach iii;


/**DomainLayer.Teams in String**/

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


/**Policies-score**/

    IScorePolicy scorePolicy = new RegularScorePolicy();
    private IGameInlayPolicy gameInlayPolicy;


/**DomainLayer.Teams**/

    ArrayList<Team> teams = new ArrayList<>();
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
    private Referee refereee1;
    private Referee refereee2;
    private Referee refereee3;
    private Referee refereee4;
    private Referee refereee5;
    private Referee refereee6;
    private Referee refereee7;
    private Referee refereee8;
    private Referee refereee9;
    private Referee refereee10;
    private Referee refereee11;
    private Referee refereee12;
    private Referee refereee13;
    private Referee refereee14;
    private Referee refereee15;
    private MainReferee mainReferee1;
    private MainReferee mainReferee2;
    private MainReferee mainReferee3;
    private MainReferee mainReferee4;
    private MainReferee mainReferee5;

    private AssociationRepresentative representativee1;
    private AssociationRepresentative representativee2;
    private AssociationRepresentative representativee3;
    private AssociationRepresentative representativee4;
    private AssociationRepresentative representativee5;


/**
     * Statistics
     **/

    private Statistics statisticsA;
    private Statistics statisticsB;
    private Statistics statisticsC;
    private Statistics statisticsD;
    private Statistics statisticsE;
    private Statistics statisticsF;
    private Statistics statisticsG;
    private Statistics statisticsH;
    private Statistics statisticsI;
    private Statistics statisticsJ;

    private Administrator administrator;



    @Before
    public void setUp() throws Exception {

        dbLocal = DBLocal.getInstance();
        mainSystem = MainSystem.getInstance();
        assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");

        dbLocal = DBLocal.getInstance();

        /**Policies-score**/
        scorePolicy = new RegularScorePolicy();

        /**DomainLayer.Teams**/
        teams = new ArrayList<>();
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

        /**Statistics**/
        statisticsA = new Statistics(scorePolicy);
        a.setStatistics(statisticsA);
        statisticsB = new Statistics(scorePolicy);
        b.setStatistics(statisticsB);
        statisticsC = new Statistics(scorePolicy);
        c.setStatistics(statisticsC);
        statisticsD = new Statistics(scorePolicy);
        d.setStatistics(statisticsD);
        statisticsE = new Statistics(scorePolicy);
        e.setStatistics(statisticsE);
        statisticsF = new Statistics(scorePolicy);
        f.setStatistics(statisticsF);
        statisticsG = new Statistics(scorePolicy);
        g.setStatistics(statisticsG);
        statisticsH = new Statistics(scorePolicy);
        h.setStatistics(statisticsH);
        statisticsI = new Statistics(scorePolicy);
        i.setStatistics(statisticsI);
        statisticsJ = new Statistics(scorePolicy);
        j.setStatistics(statisticsJ);

        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);
        teams.add(e);
        teams.add(f);
        teams.add(g);
        teams.add(h);
        teams.add(i);
        teams.add(j);


        /**Policies-game**/
        gameInlayPolicy = new OneRoundGamePolicy(teams, 2021);


        /**DomainLayer.Users**/
        referees = new ArrayList<>();
        refereee1 = new Referee("referee1", "a", "referee1", "a", "a");
        referees.add(refereee1);
        refereee2 = new Referee("referee2", "a", "referee2", "a", "a");
        referees.add(refereee2);
        refereee3 = new Referee("referee3", "a", "referee3", "a", "a");
        referees.add(refereee3);
        refereee4 = new Referee("referee4", "a", "referee4", "a", "a");
        referees.add(refereee4);
        refereee5 = new Referee("referee5", "a", "referee5", "a", "a");
        referees.add(refereee5);
        refereee6 = new Referee("referee6", "a", "referee6", "a", "a");
        referees.add(refereee6);
        refereee7 = new Referee("referee7", "a", "referee7", "a", "a");
        referees.add(refereee7);
        refereee8 = new Referee("referee8", "a", "referee8", "a", "a");
        referees.add(refereee8);
        refereee9 = new Referee("referee9", "a", "referee9", "a", "a");
        referees.add(refereee9);
        refereee10 = new Referee("referee10", "a", "referee10", "a", "a");
        referees.add(refereee10);
        refereee11 = new Referee("referee11", "a", "referee11", "a", "a");
        referees.add(refereee11);
        refereee12 = new Referee("referee12", "a", "referee12", "a", "a");
        referees.add(refereee12);
        refereee13 = new Referee("referee13", "a", "referee13", "a", "a");
        referees.add(refereee13);
        refereee14 = new Referee("referee14", "a", "referee14", "a", "a");
        referees.add(refereee14);
        refereee15 = new Referee("referee15", "a", "referee15", "a", "a");
        referees.add(refereee15);
        mainReferee1 = new MainReferee("mainReferee1", "a", "mainReferee1", "a", "a");
        mainReferee2 = new MainReferee("mainReferee2", "a", "mainReferee2", "a", "a");
        mainReferee3 = new MainReferee("mainReferee3", "a", "mainReferee3", "a", "a");
        mainReferee4 = new MainReferee("mainReferee4", "a", "mainReferee4", "a", "a");
        mainReferee5 = new MainReferee("mainReferee5", "a", "mainReferee5", "a", "a");

        referees.add(mainReferee1);
        referees.add(mainReferee2);
        referees.add(mainReferee3);
        referees.add(mainReferee4);
        referees.add(mainReferee5);

        representatives = new ArrayList<>();
        representativee1 = new AssociationRepresentative("representative1", "a", "representative1", "a");
        representativee2 = new AssociationRepresentative("representative2", "a", "representative2", "a");
        representativee3 = new AssociationRepresentative("representative3", "a", "representative3", "a");
        representativee4 = new AssociationRepresentative("representative4", "a", "representative4", "a");
        representativee5 = new AssociationRepresentative("representative5", "a", "representative5", "a");

        representatives.add(representativee1);
        representatives.add(representativee2);
        representatives.add(representativee3);
        representatives.add(representativee4);
        representatives.add(representativee5);

        administrator = new Administrator("The King", "1234", "Oren Hason", "OrenHason@gmail.com");

        /**DomainLayer.Teams in String**/

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

        /**Referee in String**/

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

        String mainRefereee1 = "mainReferee1";
        String mainRefereee2 = "mainReferee2";
        String mainRefereee3 = "mainReferee3";
        String mainRefereee4 = "mainReferee4";
        String mainRefereee5 = "mainReferee5";

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

        stringReferees.add(mainRefereee1);
        stringReferees.add(mainRefereee2);
        stringReferees.add(mainRefereee3);
        stringReferees.add(mainRefereee4);
        stringReferees.add(mainRefereee5);


        /**AssociationRepresentative in String**/

        String representative1 = "representative1";
        String representative2 = "representative2";
        String representative3 = "representative3";
        String representative4 = "representative4";
        String representative5 = "representative5";
        String goodSubtitute = "goodSubtitute";

        stringRepresentatives.add(representative1);
        stringRepresentatives.add(representative2);
        stringRepresentatives.add(representative3);
        stringRepresentatives.add(representative4);
        stringRepresentatives.add(representative5);

        /**Coach and Player - for teams**/

        LocalDate date1 = LocalDate.of(1985, 1, 12);
        LocalDate date2 = LocalDate.of(1987, 6, 12);

        vermut = new Player("ver", "123", "gili vermut", "g", date1, "midelfer");
        zahavi = new Player("ez7", "123", "rean zahavi", "eeee", date2, "striker");
        aa = new Player("ver1", "123", "gili vermut", "g", date1, "midelfer");
        bb = new Player("ez71", "123", "rean zahavi", "eeee", date2, "striker");
        cc = new Player("ver2", "123", "gili vermut", "g", date1, "midelfer");
        dd = new Player("ez72", "123", "rean zahavi", "eeee", date2, "striker");
        ee = new Player("ver3", "123", "gili vermut", "g", date1, "midelfer");
        ff = new Player("ez37", "123", "rean zahavi", "eeee", date2, "striker");
        gg = new Player("ver4", "123", "gili vermut", "g", date1, "midelfer");
        hh = new Player("ez75", "123", "rean zahavi", "eeee", date2, "striker");

        klinger = new Coach("kling", "1212", "nir klinger", "dsdasd", "head coach");
        aaa = new Coach("klin", "1212", "nir klinger", "dsdasd", "head coach");
        bbb = new Coach("kli", "1212", "nir klinger", "dsdasd", "head coach");
        ccc = new Coach("kl", "1212", "nir klinger", "dsdasd", "head coach");
        ddd = new Coach("k", "1212", "nir klinger", "dsdasd", "head coach");
        eee = new Coach("klingg", "1212", "nir klinger", "dsdasd", "head coach");
        fff = new Coach("klinggg", "1212", "nir klinger", "dsdasd", "head coach");
        ggg = new Coach("klinggggg", "1212", "nir klinger", "dsdasd", "head coach");
        hhh = new Coach("klnnning", "1212", "nir klinger", "dsdasd", "head coach");
        iii = new Coach("klinssssg", "1212", "nir klinger", "dsdasd", "head coach");

        /**DBLocal**/

        dbLocal.setLeague(league);

        dbLocal.setUser(mainReferee1);
        dbLocal.setUser(mainReferee2);
        dbLocal.setUser(mainReferee3);
        dbLocal.setUser(mainReferee4);
        dbLocal.setUser(mainReferee5);

        dbLocal.setUser(refereee1);
        dbLocal.setUser(refereee2);
        dbLocal.setUser(refereee3);
        dbLocal.setUser(refereee4);
        dbLocal.setUser(refereee5);
        dbLocal.setUser(refereee6);
        dbLocal.setUser(refereee7);
        dbLocal.setUser(refereee8);
        dbLocal.setUser(refereee9);
        dbLocal.setUser(refereee10);
        dbLocal.setUser(refereee11);
        dbLocal.setUser(refereee12);
        dbLocal.setUser(refereee13);
        dbLocal.setUser(refereee14);
        dbLocal.setUser(refereee15);

        dbLocal.setUser(representativee1);
        dbLocal.setUser(representativee2);
        dbLocal.setUser(representativee3);
        dbLocal.setUser(representativee4);
        dbLocal.setUser(representativee5);

        dbLocal.setUser(administrator);
        dbLocal.setUser(klinger);
        dbLocal.setUser(zahavi);

        dbLocal.setUser(administrator);

        //adds all coaches nd players.
        dbLocal.addUser(vermut);
        dbLocal.addUser(zahavi);
        dbLocal.addUser(klinger);
        dbLocal.addUser(aa);
        dbLocal.addUser(aaa);
        dbLocal.addUser(bb);
        dbLocal.addUser(bbb);
        dbLocal.addUser(cc);
        dbLocal.addUser(ccc);
        dbLocal.addUser(dd);
        dbLocal.addUser(ddd);
        dbLocal.addUser(ee);
        dbLocal.addUser(eee);
        dbLocal.addUser(ff);
        dbLocal.addUser(fff);
        dbLocal.addUser(gg);
        dbLocal.addUser(ggg);
        dbLocal.addUser(hh);
        dbLocal.addUser(iii);
        dbLocal.setTeam(a);
        dbLocal.setTeam(b);
        dbLocal.setTeam(c);
        dbLocal.setTeam(d);
        dbLocal.setTeam(e);
        dbLocal.setTeam(f);
        dbLocal.setTeam(g);
        dbLocal.setTeam(h);
        dbLocal.setTeam(i);
        dbLocal.setTeam(j);


        a.addCoach(klinger);
        b.addCoach(aaa);
        c.addCoach(bbb);
        d.addCoach(ccc);
        e.addCoach(ddd);
        f.addCoach(eee);
        g.addCoach(fff);
        h.addCoach(ggg);
        i.addCoach(hhh);
        j.addCoach(iii);

        a.addPlayer(zahavi);
        b.addPlayer(vermut);
        c.addPlayer(aa);
        d.addPlayer(bb);
        e.addPlayer(cc);
        f.addPlayer(dd);
        g.addPlayer(ee);
        h.addPlayer(ff);
        i.addPlayer(gg);
        j.addPlayer(hh);

        ///create pages teams
        c.createPage("team in england","england");
        d.createPage("team in england","england");

        dbLocal.addLeague(league);

        AssociationRepresentative assoTest = new AssociationRepresentative("tester", "tt", "lala", "lili");
        assoTest.addLeague("Alufot", 2);

        assoTest.addSeasonToLeague("Alufot", 2020, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);
        //assoTest.addSeasonToLeague("Alufot", 2021, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addLeague() {

        assertTrue("UC 9.1 OK! - added a new league.", assoTest.addLeague("Alufottt", 10));
        assertFalse("UC 9.1 OK! - doesn't add an existing league.", assoTest.addLeague("Alufot", 5));
    }

    @Test
    public void addSeasonToLeague() {

        //assoTest.addSeasonToLeague("Alufot", 2021, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);

        List<Season> allSeasons = dbLocal.getLeague("Alufot").getAllSeasons();
        Season newS = allSeasons.get(0); //gets the season object that was added.

        assertTrue(newS.getYear() == 2020);
        //assertTrue(testReferee.getQualification().equals("bla bla"));

        assertTrue(newS.getiScorePolicy().getName().equals("RegularScorePolicy"));   //  todo: check thissss /v/v/d/f//f//f/
        assertFalse(newS.getiGameInlayPolicy().getName().equals("OneRoundsGamePolicy")); // suppose to be notSimple   todo: check thissss

       /* List<Referee> allRef = newS.getAllReferees();
        assertTrue(allRef.get(0).userFullName.equals("ramzi ramzen"));

        List<AssociationRepresentative> allAss = newS.getAllRepresentatives();
        assertFalse(allAss.get(0).userFullName.equals("ramzi ramzen"));
        assertEquals(allAss.get(0).userFullName, ("yiftah"));

        List<Team> allTe = newS.getAllTeams();
        assertEquals(allTe.get(0).getName(), "Maccabi Tel Aviv");*/
    }

    @Test
    public void addReferee() {

        Fan fanTest = new Fan("f", "ff", "omer hof", "omer@hof");
        AssociationRepresentative assocTest = new AssociationRepresentative("a1", "aa", "tali", "tali@tali");
        dbLocal.addUser(fanTest);
        dbLocal.addUser(assocTest);

        //assertNull(dbLocal.getUserType("Referee")); //not exists yet.
        assertFalse(dbLocal.getUserByFullName("omer hof") instanceof Referee);
        assertTrue(dbLocal.getUserByFullName("omer hof") instanceof Fan); //starts as a fan

        assocTest.addReferee("omer hof");
        assertEquals(dbLocal.getUserByFullName("omer hof").password, "ff");
        assertTrue(dbLocal.getUserByFullName("omer hof") instanceof Referee); //now he is a referee

        assertFalse(assoTest.addReferee("notExistsUser")); //cant assign a fan that doesn't exist in the DBLocal.
    }

    @Test
    public void removeReferee() {

        Referee r1 = new Referee("r1", "rr", "ramzi ramzen", "ziv@ziv", "bla bla");
        AssociationRepresentative assocTest = new AssociationRepresentative("a1", "aa", "tali", "tali@tali");
        dbLocal.addUser(r1);
        dbLocal.addUser(assocTest);

        assertNull(dbLocal.getUserType("Fan")); //not exists yet.
        assertTrue(dbLocal.getUserByFullName("ramzi ramzen") instanceof Referee);

        assertTrue(assocTest.removeReferee("ramzi ramzen"));
        assertTrue(dbLocal.getUserByFullName("ramzi ramzen") instanceof Fan);

        assertFalse(assoTest.removeReferee("notExistsUser"));
    }

    @Test ///VVV
    public void addGameEvent() {

        assoTest.addSeasonToLeague("Alufot", 2021, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);
        LocalDateTime date1 = LocalDateTime.of(2020,4,19, 15, 30);

        Game game = new Game(a, b, date1);
        game.setStatus(Game.gameStatus.active);
        representativee1.setMyGame(game);

        representativee1.addGameEvent(Event.eventType.goal, "37", "yehi", "home");

        assertTrue(representativee1.addGameEvent(Event.eventType.injury, "51", "hod", "away"));

        assertTrue(representativee1.addGameEvent(Event.eventType.yellowTicket, "53", "hod", "away"));
        assertTrue(representativee1.addGameEvent(Event.eventType.offside, "54", "hod", "home"));
        assertTrue(representativee1.addGameEvent(Event.eventType.foul, "58", "hod", "away"));
        assertTrue(representativee1.addGameEvent(Event.eventType.substitution, "59", "hod", "away"));

        assertTrue(representativee1.addGameEvent(Event.eventType.goal, "66", "yehi", "home"));
        assertTrue(representativee1.addGameEvent(Event.eventType.goal, "87", "yehi", "home")); /////////שלושער ליחי/////
        assertTrue(representativee1.addGameEvent(Event.eventType.redTicket, "91", "shukrun", "away")); // בחור אלים

        assertFalse(representativee5.addGameEvent(Event.eventType.goal, "87", "messi", "away")); //doesn't have an active game.
    }

    @Test
    public void passMyGames() {

        AssociationRepresentative passFrom = (AssociationRepresentative) dbLocal.getUserType("AssociationRepresentative");
        String leagueToPassFrom = dbLocal.whatLeagueImAt(passFrom, 2020);
        System.out.println(leagueToPassFrom);
        //ArrayList<AssociationRepresentative> representatives = season.getAllRepresentatives();

        AssociationRepresentative goodSubtitute = new AssociationRepresentative("goodSubtitute", "taata", "goodSubtitute", "tali@gmail");

       /* Team a = new Team("barca");
        Team b = new Team("real");
        LocalDateTime date2 = LocalDateTime.of(2020, 4, 19, 17, 30);

        Game game = new Game(a, b, date2);
        goodSubtitute.setMyGame(game);*/

        dbLocal.setUser(goodSubtitute);

        representatives.add(goodSubtitute);
        //season.setAllRepresentatives(representatives); //now list has one more rep. to pass to !

        AssociationRepresentative representativee10 = new AssociationRepresentative("representativee10", "a", "representativee10", "a");
        AssociationRepresentative representativee11 = new AssociationRepresentative("representativee11", "a", "representativee11", "a");
        AssociationRepresentative representativee12 = new AssociationRepresentative("representativee12", "a", "representativee12", "a");
        AssociationRepresentative representativee13 = new AssociationRepresentative("representativee13", "a", "representativee13", "a");

        ArrayList<AssociationRepresentative> repsNEW = new ArrayList<>();
        repsNEW.add(representativee10);
        repsNEW.add(representativee11);
        repsNEW.add(representativee12);
        repsNEW.add(representativee13);

        ArrayList<String> stringRepresentativesNEW = new ArrayList<>();

        stringRepresentativesNEW.add("representativee10"); //by username.
        stringRepresentativesNEW.add("representativee11"); //by username.
        stringRepresentativesNEW.add("representativee12"); //by username.
        stringRepresentativesNEW.add("representativee13"); //by username.

        stringRepresentativesNEW.add("goodSubtitute"); //the new one, by username.

        dbLocal.setUser(representativee10);
        dbLocal.setUser(representativee11);
        dbLocal.setUser(representativee12);
        dbLocal.setUser(representativee13);

        dbLocal.removeUser("representative1");
        dbLocal.removeUser("representative2");
        dbLocal.removeUser("representative3");
        dbLocal.removeUser("representative4");
        dbLocal.removeUser("representative5");

        League league1 = new League("Champions", 10);
        dbLocal.setLeague(league1);

        assoTest.addSeasonToLeague("Champions", 2020, scorePolicy.getName(), gameInlayPolicy.getName(), stringTeams, stringReferees, stringRepresentativesNEW); //adds

        //Season newSeason = new Season(2021, teams, referees, representatives, scorePolicy.getName(), gameInlayPolicy.getName()); //creates a new

        assertEquals(dbLocal.getUserTypeList("AssociationRepresentative").size(), 5);

        assertTrue(passFrom.passMyGames());


        //assoTest.addSeasonToLeague("Champions league", 2020, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);
        //Season test = dbLocal.getLeague("Alufot").getSeasonByYear(2020);

        //test.getYear();
        //assoTest.addSeasonToLeague("Alufot", 2020, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);

        /*League league2 = new League("Champions league", 10);
        assoTest.addLeague("Champions league", 10);
        stringRepresentatives.add(representativeTest);
        dbLocal.addLeague(league2);*/
    }
}
