package DataForTest;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import Users.Administrator;
import Users.AssociationRepresentative;
import Users.MainReferee;
import Users.Referee;

import java.time.LocalDate;
import java.util.ArrayList;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import Users.*;

import java.util.ArrayList;

public class DataBase {


    DB db;

    /**
     * Management
     **/
    private League league;
    private ArrayList<Season> seasons;
    private Season season;


    /**
     * Policies
     **/
    private IGameInlayPolicy gameInlayPolicy;
    private IScorePolicy scorePolicy;

    /**
     * Teams
     **/
    private ArrayList<Team> teams;
    private Team a = new Team("barca");
    private Team b = new Team("real");
    private Team c = new Team("man u");
    private Team d = new Team("man city");
    private Team e = new Team("liverpool");
    private Team f = new Team("chelsea");
    private Team g = new Team("roma");
    private Team h = new Team("juve");
    private Team i = new Team("milan");
    private Team j = new Team("inter");

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

    /**
     * Users
     **/
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
    private TeamOwner teamOwner1;
    private TeamOwner teamOwner2;
    private TeamOwner teamOwner3;
    private TeamOwner teamOwner4;
    private TeamOwner teamOwner5;
    private TeamOwner teamOwner6;
    private TeamOwner teamOwner7;
    private Manager manager1;
    private Manager manager2;
    private Manager manager3;
    private Manager manager4;
    private Player almostOwner1;
    private Player almostOwner2;
    private Player almostManager1;
    private Coach almostOwner3;
    private Coach almostOwner4;
    private Coach almostManager2;

    private AssociationRepresentative representativee1;
    private AssociationRepresentative representativee2;
    private AssociationRepresentative representativee3;
    private AssociationRepresentative representativee4;
    private AssociationRepresentative representativee5;

    private Administrator administrator;
    private Fan fan1;


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

    /**
     * constructor
     */
    public DataBase() {

        db = DB.getInstance();

        /**Policies-score**/
        scorePolicy = new RegularScorePolicy();

        /**Teams**/
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
        gameInlayPolicy = new TwoRoundsGamePolicy(teams, 2021);


        /**Users**/
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

        /** Owners **/
        teamOwner1 = new TeamOwner("teamOwner1", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner2 = new TeamOwner("teamOwner2", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner3 = new TeamOwner("teamOwner3", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner4 = new TeamOwner("teamOwner4", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner5 = new TeamOwner("teamOwner5", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner6 = new TeamOwner("teamOwner6", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner7 = new TeamOwner("teamOwner7", "1234", "teamOwnerName", "teamOwner.com");


/** Users that yet to be owners/managers **/

        LocalDate date11 = LocalDate.of(1994, 6, 28);

//        almostOwner1 = new Player("almostOwner1", "1234", "playerName", "player.com", date11, "player_that_plays");
//        almostOwner2 = new Player("almostOwner2", "1234", "playerName", "player.com", date11, "player_that_plays");
//        almostOwner3 = new Coach("almostOwner3", "1234", "coachName", "coach.com", "coach_that_coaches");
//        almostOwner4 = new Coach("almostOwner4", "1234", "coachName", "coach.com", "coach_that_coaches");
//        almostManager1 = new Player("almostManager1", "1234", "playerName", "player.com", date11, "player_that_plays");
//        almostManager2 = new Coach("almostManager2", "1234", "coachName", "coach.com", "coach_that_coaches");


        /** Managers **/
        manager1 = new Manager("manager1", "1234", "mmanagerName", "manager.com");
        manager2 = new Manager("manager2", "1234", "mmanagerName", "manager.com");
        manager3 = new Manager("manager3", "1234", "mmanagerName", "manager.com");
        manager4 = new Manager("manager4", "1234", "mmanagerName", "manager.com");

        /**Teams in String**/

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

        ArrayList<String> stringRepresentatives = new ArrayList<>();
        String representative1 = "representative1";
        String representative2 = "representative2";
        String representative3 = "representative3";
        String representative4 = "representative4";
        String representative5 = "representative5";
        stringRepresentatives.add(representative1);
        stringRepresentatives.add(representative2);
        stringRepresentatives.add(representative3);
        stringRepresentatives.add(representative4);
        stringRepresentatives.add(representative5);


        /**Management**/
        //league = new League("Champions league", 10);
        //season = new Season(2021, teams, referees, representatives, scorePolicy.getName(), gameInlayPolicy.getName());
        //seasons = new ArrayList<>();
        //seasons.add(season);
        //league.setAllSeasons(seasons);
        //representative = new AssociationRepresentative("representative", "a", "a", "a");
        //representative.addSeasonToLeague("Champions league",2020,scorePolicy.getName(), gameInlayPolicy.getName(),stringTeams,stringReferees,stringRepresentatives);


        /**Coach and Player - for teams**/

        LocalDate date1 = LocalDate.of(1985, 1, 12);
        LocalDate date2 = LocalDate.of(1987, 6, 12);

        vermut = new Player("ver", "123", "Gili Vermut", "g", date1, "midelfer");
        vermut.createPersonalPage( 174, 87,7 , "Hapoel Tel Aviv" );
        zahavi = new Player("ez7", "123", "Eran Zahavi", "eeee", date2, "striker");
        zahavi.createPersonalPage( 182, 89,10 , "China" );
        aa = new Player("ver1", "123", "Aduram Keisee", "g", date1, "midelfer");
        aa.createPersonalPage( 178, 70,6 , "Macabi Heifa" );
        bb = new Player("ez71", "123", "Leo Messi", "eeee", date2, "striker");
       bb.createPersonalPage( 175, 72,10 , "Barcelona" );
        cc = new Player("ver2", "123", "Gavriel Batistuta", "g", date1, "midelfer");
        cc.createPersonalPage( 180, 72,10 , "Barcelona" );
        dd = new Player("ez72", "123", "Alon Hazan", "eeee", date2, "striker");
        dd.createPersonalPage(185, 90,10 , "Katar");
        ee = new Player("ver3", "123", "David Beckham", "g", date1, "midelfer");
        ee.createPersonalPage(170, 85,7 , "Manchester United");
        ff = new Player("ez37", "123", "Yossi Benayun", "eeee", date2, "striker");
        ff.createPersonalPage(185, 78,15 , "Dimona");
        gg = new Player("ver4", "123", "Muhamad Salah", "g", date1, "midelfer");
        gg.createPersonalPage(172, 77,9 , "Liverpool");
        hh = new Player("ez75", "123", "Shlomi Arbeitman", "eeee", date2, "striker");
        hh.createPersonalPage(170, 78,11 , "Betar Yerushlaim");

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

        /**fan**/

        fan1 = new Fan("idok7777","12345","ido kest","ido747@gmail.com");





        /**DB**/

        db.setLeague(league);

        db.setUser(mainReferee1);
        db.setUser(mainReferee2);
        db.setUser(mainReferee3);
        db.setUser(mainReferee4);
        db.setUser(mainReferee5);

        db.setUser(refereee1);
        db.setUser(refereee2);
        db.setUser(refereee3);
        db.setUser(refereee4);
        db.setUser(refereee5);
        db.setUser(refereee6);
        db.setUser(refereee7);
        db.setUser(refereee8);
        db.setUser(refereee9);
        db.setUser(refereee10);
        db.setUser(refereee11);
        db.setUser(refereee12);
        db.setUser(refereee13);
        db.setUser(refereee14);
        db.setUser(refereee15);

        db.setUser(representativee1);
        db.setUser(representativee2);
        db.setUser(representativee3);
        db.setUser(representativee4);
        db.setUser(representativee5);

        db.setUser(administrator);
        db.setUser(klinger);
        db.setUser(zahavi);

        db.setUser(administrator);

        db.setUser(teamOwner1);
        db.setUser(teamOwner2);
        db.setUser(teamOwner3);
        db.setUser(teamOwner4);
        db.setUser(teamOwner5);
        db.setUser(teamOwner6);
        db.setUser(teamOwner7);
        db.setUser(almostOwner1);
        db.setUser(almostOwner2);
        db.setUser(almostOwner3);
        db.setUser(almostOwner4);
        db.setUser(almostManager1);
        db.setUser(almostManager2);

        db.setUser(manager1);
        db.setUser(manager2);
        db.setUser(manager3);
        db.setUser(manager4);

        db.setUser(fan1);

        //adds all coaches nd players.
        db.addUser(vermut);
        db.addUser(zahavi);
        db.addUser(klinger);
        db.addUser(aa);
        db.addUser(aaa);
        db.addUser(bb);
        db.addUser(bbb);
        db.addUser(cc);
        db.addUser(ccc);
        db.addUser(dd);
        db.addUser(ddd);
        db.addUser(ee);
        db.addUser(eee);
        db.addUser(ff);
        db.addUser(fff);
        db.addUser(gg);
        db.addUser(ggg);
        db.addUser(hh);
        db.addUser(iii);
        db.setTeam(a);
        db.setTeam(b);
        db.setTeam(c);
        db.setTeam(d);
        db.setTeam(e);
        db.setTeam(f);
        db.setTeam(g);
        db.setTeam(h);
        db.setTeam(i);
        db.setTeam(j);


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

        ///
        c.addTeamOwner(teamOwner1);
        d.addTeamOwner(teamOwner2);

        ///create pages teams

        c.createPage("team in england","england");
        d.createPage("team in england","england");
        ///fan follow team
        fan1.followTeam(c.getName());
        fan1.followTeam(d.getName());
        db.setUser(fan1);


        db.addLeague(league);

        AssociationRepresentative assoTest = new AssociationRepresentative("tester", "tt", "lala", "lili");
        assoTest.addLeague("Alufot", 2);

        //assoTest.addSeasonToLeague("Alufot", 2020, "RegularScorePolicy", "OneRoundGamePolicy", stringTeams, stringReferees, stringRepresentatives);
        assoTest.addSeasonToLeague("Alufot", 2021, "RegularScorePolicy", gameInlayPolicy.getName(), stringTeams, stringReferees, stringRepresentatives);



        Fan forTest = new Fan("f", "f", "blabla", "test@test");
        Player p = new Player("p","p","blabla","test@test",LocalDate.now(),"striker");
        Coach cr = new Coach("c","c","blabla","test@test", "main coach");
        Referee r = new Referee("r","r","blabla","test@test","var referee");
        MainReferee mr = new MainReferee("mr","mr","blabla","test@test","main referee");
        TeamOwner to = new TeamOwner("to","to","blabla","test@test");
        Manager m = new Manager("m","m","blabla","test@test");
        AssociationRepresentative ar = new AssociationRepresentative("a", "a", "blabla", "test@test");
        Administrator ad = new Administrator("a","a","blabla","test@test");
        db.setUser(p);
        db.setUser(cr);
        db.setUser(r);
        db.setUser(mr);
        db.setUser(to);
        db.setUser(m);
        db.setUser(ad);
        db.setUser(forTest);
        db.setUser(ar);



        // Season season = db.getLeague("Alufot").getAllSeasons().get(0);
        //season.getiGameInlayPolicy();
    }
}

