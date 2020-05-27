package DBLayer;

import DomainLayer.LeagueSeasonsManagment.*;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Statistics;
import DomainLayer.Teams.Team;
import DomainLayer.Users.Administrator;
import DomainLayer.Users.AssociationRepresentative;
import DomainLayer.Users.MainReferee;
import DomainLayer.Users.Referee;

import java.time.LocalDate;
import java.util.ArrayList;

import DomainLayer.Users.*;

public class DataBase {

    DBLocal dbLocal;

    /**
     * Management
     **/
    private League league;

    /**
     * Policies
     **/
    private IGameInlayPolicy gameInlayPolicy;
    private IScorePolicy scorePolicy;

    /**
     * DomainLayer.Teams
     **/
    private ArrayList<Team> teams;
    private Team team1 = new Team("Barcelona");
    private Team team2 = new Team("Real Madrid");
    private Team team3 = new Team("Manchester United");
    private Team team4 = new Team("Liverpool");


    /**
     * Stadiums
     **/
    private Stadium stadium1 = new Stadium(100000.0,110000,80);
    private Stadium stadium2 = new Stadium(70000.0,60000,90);
    private Stadium stadium3 = new Stadium(50000.0,40000,300);
    private Stadium stadium4 = new Stadium(50000.0,40000,300);


    /**

     * Statistics
     **/
    private Statistics statistics1;
    private Statistics statistics2;
    private Statistics statistics3;
    private Statistics statistics4;

    /**
     * DomainLayer.Users
     **/
    private ArrayList<Referee> referees;
    private Referee refereee1;
    private Referee refereee2;
    private Referee refereee3;
    private Referee refereee4;
    private Referee refereee5;
    private Referee refereee6;

    private MainReferee mainReferee1;
    private MainReferee mainReferee2;


    private TeamOwner teamOwner1;
    private TeamOwner teamOwner2;
    private TeamOwner teamOwner3;
    private TeamOwner teamOwner4;


    private Manager manager1;
    private Manager manager2;
    private Manager manager3;
    private Manager manager4;

    /**
     * Association Representatives
     **/
    private ArrayList<AssociationRepresentative> representatives;
    private AssociationRepresentative representativee1;
    private AssociationRepresentative representativee2;

    /**
     * Administrators
     **/
    private Administrator administrator;

    /**
     * Fans
     **/
    private Fan fan1;
    private Fan fan2;
    private Fan fan3;
    private Fan fan4;
    private Fan fan5;

    /**
     * Players
     **/
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player player5;
    Player player6;
    Player player7;
    Player player8;
    Player player9;
    Player player10;
    Player player11;

    Player player12;
    Player player13;
    Player player14;
    Player player15;
    Player player16;
    Player player17;
    Player player18;
    Player player19;
    Player player20;
    Player player21;
    Player player22;

    Player player23;
    Player player24;
    Player player25;
    Player player26;
    Player player27;
    Player player28;
    Player player29;
    Player player30;
    Player player31;
    Player player32;
    Player player33;

    Player player34;
    Player player35;
    Player player36;
    Player player37;
    Player player38;
    Player player39;
    Player player40;
    Player player41;
    Player player42;
    Player player43;
    Player player44;

    Player player45;
    Player player46;
    Player player47;
    Player player48;
    Player player49;
    Player player50;

    /**
     * Coaches
     **/
    Coach coach1;
    Coach coach2;
    Coach coach3;
    Coach coach4;

    /**
     * constructor
     */
    public DataBase() {

        dbLocal = DBLocal.getInstance();



        /**Score Policy**/
        scorePolicy = new RegularScorePolicy();

        /**DomainLayer.Teams**/
        team1.createPage("1931","Spain");
        team2.createPage("1932","Spain");
        team3.createPage("1913","UK");
        team4.createPage("1927","UK");

        /**DomainLayer.Teams stadiums**/
        stadium1.setName("Camp Nou");
        stadium2.setName("Santiago Bernabeu");
        stadium3.setName("Old Trafford");
        stadium4.setName("Anfield");

        team1.setStadium(stadium1);
        team2.setStadium(stadium2);
        team3.setStadium(stadium3);
        team4.setStadium(stadium4);


        /**Statistics**/
        statistics1 = new Statistics(scorePolicy);
        team1.setStatistics(statistics1);
        statistics2 = new Statistics(scorePolicy);
        team2.setStatistics(statistics2);
        statistics3 = new Statistics(scorePolicy);
        team3.setStatistics(statistics3);
        statistics4 = new Statistics(scorePolicy);
        team4.setStatistics(statistics4);

        teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);

        /**Policies-game**/
        gameInlayPolicy = new TwoRoundsGamePolicy(teams, 2021);

        /**DomainLayer.Users**/
        referees = new ArrayList<>();

        refereee1 = new Referee("Praprotnik", MainSystem.getInstance().encrypte("Praprotnik1"), "Jura Praprotnik", "Praprotnik@gmail.com", "Linesman referee");
        referees.add(refereee1);
        refereee2 = new Referee("Vukan", MainSystem.getInstance().encrypte("Vukan1"), "Robert Vukan", "vukan@gmail.com", "Linesman referee");
        referees.add(refereee2);
        refereee3 = new Referee("Borsch", MainSystem.getInstance().encrypte("Borsch1"), "Mark Borsch", "borsch@gmail.com", "var referee");
        referees.add(refereee3);
        refereee4 = new Referee("Zwayer", MainSystem.getInstance().encrypte("Zwayer1"), "Felix Zwayer", "zwayer@gmail.com", "var referee");
        referees.add(refereee4);
        refereee5 = new Referee("Makkelie", MainSystem.getInstance().encrypte("Makkelie1"), "Danny Makkelie", "makkelie@gmail.com", "Linesman referee");
        referees.add(refereee5);
        refereee6 = new Referee("Dias", MainSystem.getInstance().encrypte("Dias11"), "Artur Dias", "dIas@gmail.com", "var referee");
        referees.add(refereee6);

        mainReferee1 = new MainReferee("Buquet", MainSystem.getInstance().encrypte("Buquet1"), "Ruddy Buquet", "buquet@gmail.com", "a");
        mainReferee2 = new MainReferee("Grinfeld", MainSystem.getInstance().encrypte("Grinfeld1"), "Oren Grinfeld", "grinfeld@gmail.com", "a");

        referees.add(mainReferee1);
        referees.add(mainReferee2);

        representatives = new ArrayList<>();
        representativee1 = new AssociationRepresentative("Levi", MainSystem.getInstance().encrypte("Levi11"), "Gavri Levi", "levy@gmail.com");
        representativee2 = new AssociationRepresentative("Luzon", MainSystem.getInstance().encrypte("Luzon1"), "Avi Luzon", "luzon@gmail.com");

        representatives.add(representativee1);
        representatives.add(representativee2);


        administrator = new Administrator("Dana123", MainSystem.getInstance().encrypte("Dana123"), "Dana Ridel", "Ridel@gmail.com");

        /** Owners **/
        teamOwner1 = new TeamOwner("ThePeople", MainSystem.getInstance().encrypte("ThePeople1"), "The People", "people@gmail.com");
        teamOwner2 = new TeamOwner("Perez", MainSystem.getInstance().encrypte("Perez1"), "Florentino perez", "perez@gmail.com");
        teamOwner3 = new TeamOwner("Magnier", MainSystem.getInstance().encrypte("Magnier1"), "John Magnier", "Magnier@gmail.com");
        teamOwner4 = new TeamOwner("Henry2", MainSystem.getInstance().encrypte("Henry2"), "John William Henry", "henry@gmail.com");




/** DomainLayer.Users that yet to be owners/managers **/

        /** Managers **/
        manager1 = new Manager("Floreta", MainSystem.getInstance().encrypte("Floreta1"), "Josep Maria Bartomeu Floreta", "Floreta@gmail.com");
        manager2 = new Manager("Zidane", MainSystem.getInstance().encrypte("Zidane1"), "Zinedine Zidane", "Zidane@gmail.com");
        manager3 = new Manager("Solskjer", MainSystem.getInstance().encrypte("Solskjer1"), "Ole Gunnar Solskjer", "Solskjer@gmail.com");
        manager4 = new Manager("Klopp1", MainSystem.getInstance().encrypte("Klopp1"), "Jurgen Klopp", "Klopp@gmail.com");

        /**Management**/
        league = new League("Champions league", 2);

        /**Coach and Player - for teams**/

        /**
         * BARCA
         */
        player1 = new Player("Messi10", MainSystem.getInstance().encrypte("Messi10"), "Lionel Messi", "messi@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player1.createPersonalPage( 174, 87,10 , team1.getName());
        player2 = new Player("TerStegen1", MainSystem.getInstance().encrypte("TerStegen1"), "Marc-Andre Ter Stegen", "tersteg@gmail.com", LocalDate.of(1972,12, 3), "GoalKeeper");
        player2.createPersonalPage( 182, 89,1 , team1.getName());
        player3 = new Player("Gerard3", MainSystem.getInstance().encrypte("Gerard3"), "Gerard Pique", "pique@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player3.createPersonalPage( 178, 70,3 , team1.getName());
        player4 = new Player("Jordi18", MainSystem.getInstance().encrypte("Jordi18"), "Jordi Alba", "alba@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player4.createPersonalPage( 175, 72,18 , team1.getName());
        player5 = new Player("Umtiti23", MainSystem.getInstance().encrypte("Umtiti23"), "Samuel Umtiti", "umtiti@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player5.createPersonalPage( 180, 72,23 , team1.getName());
        player6 = new Player("Araujo33", MainSystem.getInstance().encrypte("Araujo33"), "Ronald Araujo", "arauho@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player6.createPersonalPage(185, 90,33 , team1.getName());
        player7 = new Player("rakitic4", MainSystem.getInstance().encrypte("rakitic4"), "Ivan Rakitic", "raki@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player7.createPersonalPage(170, 85,4 , team1.getName());
        player8 = new Player("Busquets5", MainSystem.getInstance().encrypte("Busquets5"), "Sergio Busquets", "busquets@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player8.createPersonalPage(185, 78,5 , team1.getName());
        player9 = new Player("Roberto20", MainSystem.getInstance().encrypte("Roberto20"), "Sergi Roberto", "roberto@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player9.createPersonalPage(172, 77,20 , team1.getName());
        player10 = new Player("Suarez9", MainSystem.getInstance().encrypte("Suarez9"), "Luis Suarez", "suarez@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player10.createPersonalPage(170, 78,9 , team1.getName());
        player11 = new Player("Dembele11", MainSystem.getInstance().encrypte("Dembele11"), "Ousmane Dembele", "dembele@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player11.createPersonalPage(170, 78,11 , team1.getName());

        /**
         * REAL
         */
        player12 = new Player("Aréola1", MainSystem.getInstance().encrypte("Aréola1"), "Alphonse Aréola", "Aréola@gmail.com", LocalDate.of(1972,12, 3), "GoalKeeper");
        player12.createPersonalPage( 182, 89,1 , team2.getName() );
        player13 = new Player("Carvajal2", MainSystem.getInstance().encrypte("Carvajal2"), "Dani Carvajal", "Carvajal@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player13.createPersonalPage( 178, 70,2 , team2.getName() );
        player14 = new Player("Militao3", MainSystem.getInstance().encrypte("Militao3"), "Eder Militao", "Militao@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player14.createPersonalPage( 175, 72,3 , team2.getName() );
        player15 = new Player("Ramos4", MainSystem.getInstance().encrypte("Ramos4"), "Sergio Ramos", "Ramos@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player15.createPersonalPage( 180, 72,4 , team2.getName() );
        player16 = new Player("Asensio5", MainSystem.getInstance().encrypte("Asensio5"), "Marco Asensio", "Asensio@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player16.createPersonalPage(185, 90,5 , team2.getName());
        player17 = new Player("Kroos6", MainSystem.getInstance().encrypte("Kroos6"), "Toni Kroos", "Kroos@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player17.createPersonalPage(170, 85,6 , team2.getName());
        player18 = new Player("Modric7", MainSystem.getInstance().encrypte("Modric7"), "Luka Modric", "Modric@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player18.createPersonalPage(185, 78,7 , team2.getName());
        player19 = new Player("Rodriguez8", MainSystem.getInstance().encrypte("Rodriguez8"), "James Rodriguez", "Rodriguez@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player19.createPersonalPage(172, 77,8 , team2.getName());
        player20 = new Player("Bale9", MainSystem.getInstance().encrypte("Bale91"), "Gareth Bale", "Bale@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player20.createPersonalPage(170, 78,9 , team2.getName());
        player21 = new Player("Benzema10", MainSystem.getInstance().encrypte("Benzema10"), "Karim Benzema", "Benzema@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player21.createPersonalPage(170, 78,10 , team2.getName());
        player22 = new Player("Hazard11", MainSystem.getInstance().encrypte("Hazard11"), "Eden Hazard", "Hazard@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player22.createPersonalPage(170, 78,11 , team2.getName());

        /**
         * MAN U
         */
        player23 = new Player("Gea1", MainSystem.getInstance().encrypte("Gea111"), "De Gea", "Hazard@gmail.com", LocalDate.of(1972,12, 3), "GoalKeeper");
        player23.createPersonalPage( 182, 89,1 , team3.getName());
        player24 = new Player("Bailly2", MainSystem.getInstance().encrypte("Bailly2"), "Eric Bailly", "Bailly@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player24.createPersonalPage( 178, 70,2 , team3.getName());
        player25 = new Player("Dalot3", MainSystem.getInstance().encrypte("Dalot3"), "Diogo Dalot", "Dalot@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player25.createPersonalPage( 175, 72,3 , team3.getName());
        player26 = new Player("Laird4", MainSystem.getInstance().encrypte("Laird4"), "Ethan Laird", "Laird@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player26.createPersonalPage( 180, 72,4 , team3.getName());
        player27 = new Player("Maguire5", MainSystem.getInstance().encrypte("Maguire5"), "Harry Maguire", "Maguire@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player27.createPersonalPage(185, 90,5 , team3.getName());
        player28 = new Player("Pereira6", MainSystem.getInstance().encrypte("Pereira6"), "Andreas Pereira", "Pereira@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player28.createPersonalPage(170, 85,6 , team3.getName());
        player29 = new Player("Puigmal7", MainSystem.getInstance().encrypte("Puigmal7"), "Arnau Puigmal", "Puigmal@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player29.createPersonalPage(185, 78,7 , team3.getName());
        player30 = new Player("Fernandes8", MainSystem.getInstance().encrypte("Fernandes8"), "Bruno Fernandes", "Fernandes@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player30.createPersonalPage(172, 77,8 , team3.getName());
        player31 = new Player("Garner9", MainSystem.getInstance().encrypte("Garner9"), "James Garner", "Garner@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player31.createPersonalPage(170, 78,9 , team3.getName());
        player32 = new Player("Chong10", MainSystem.getInstance().encrypte("Chong10"), "Tahith Chong", "Chong@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player32.createPersonalPage(170, 78,10 , team3.getName());
        player33 = new Player("Greenwood11", MainSystem.getInstance().encrypte("Greenwood11"), "Mason Greenwood", "Greenwood@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player33.createPersonalPage(170, 78,11 , team3.getName());

        /**
         * LIVERPOOL
         */
        player34 = new Player("Becker1", MainSystem.getInstance().encrypte("Becker1"), "Alison Becker", "Becker@gmail.com", LocalDate.of(1972,12, 3), "GoalKeeper");
        player34.createPersonalPage( 182, 89,1 , team4.getName() );
        player35 = new Player("Lovern6", MainSystem.getInstance().encrypte("Lovern6"), "Dejan Loveran", "Loveran@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player35.createPersonalPage( 178, 70,6 , team4.getName() );
        player36 = new Player("Gomez12", MainSystem.getInstance().encrypte("Gomez12"), "Joe Gomez", "Gomez@gmail.com", LocalDate.of(1972,12, 3), "Defender");
        player36.createPersonalPage( 175, 72,12 , team4.getName());
        player37 = new Player("Milner7", MainSystem.getInstance().encrypte("Milner7"), "James Milner", "Milner@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player37.createPersonalPage( 180, 72,7 , team4.getName());
        player38 = new Player("Keita8", MainSystem.getInstance().encrypte("Keita8"), "Naby Keita", "Keita@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player38.createPersonalPage(185, 90,8 , team4.getName());
        player39 = new Player("Firmino9", MainSystem.getInstance().encrypte("Firmino9"), "Roberto Firmino", "Firmino@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player39.createPersonalPage(170, 85,9 , team4.getName());
        player40 = new Player("mane10", MainSystem.getInstance().encrypte("mane10"), "Sadio Mane", "Mane@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player40.createPersonalPage(185, 78,10 , team4.getName());
        player41 = new Player("Salah11", MainSystem.getInstance().encrypte("Salah11"), "Mohamed Salah", "Salah@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player41.createPersonalPage(172, 77,11 , team4.getName());
        player42 = new Player("Minamino18", MainSystem.getInstance().encrypte("Minamino18"), "Takumi Minamino", "Minamino@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player42.createPersonalPage(170, 78,18 , team4.getName());
        player43 = new Player("Origi13", MainSystem.getInstance().encrypte("Origi13"), "Divock Origi", "Origi@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player43.createPersonalPage(170, 78,13 , team4.getName());
        player44 = new Player("Elliott67", MainSystem.getInstance().encrypte("Elliott67"), "Harvey Elliott", "Elliott@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player44.createPersonalPage(170, 78,67 , team4.getName());

        /**
         * OTHERS
         */
        player45 = new Player("Zahavi", MainSystem.getInstance().encrypte("Zahavi1"), "Eran Zahavi", "Zahavi@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player46 = new Player("Keisee", MainSystem.getInstance().encrypte("Keisee1"), "Aduram Keisee", "Keisee@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player47 = new Player("Maradona", MainSystem.getInstance().encrypte("Maradona1"), "Diego Maradona", "Maradona@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player48 = new Player("Batistuta", MainSystem.getInstance().encrypte("Batistuta1"), "Gavriel Batistuta", "Batistuta@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");
        player49 = new Player("Hazan", MainSystem.getInstance().encrypte("Hazan1"), "Alon Hazan", "Hazan@gmail.com", LocalDate.of(1972,12, 3), "Striker");
        player50 = new Player("Beckham", MainSystem.getInstance().encrypte("Beckham1"), "David Beckham", "Beckham@gmail.com", LocalDate.of(1972,12, 3), "Midfielder");

        /**
         * COACHES
         */
        coach1 = new Coach("Seitien99", MainSystem.getInstance().encrypte("Seitien99"), "Quique Setien", "setien@gmail.com", "Head Coach");
        coach1.createCoachPersonalPage( LocalDate.of(1972,12, 3), team1.getName());
        coach2 = new Coach("Solari1", MainSystem.getInstance().encrypte("Solari1"), "Santiago Solari", "Solari@gmail.com", "Head Coach");
        coach2.createCoachPersonalPage(LocalDate.of(1959,7, 3), team2.getName());
        coach3 = new Coach("Ferguson1", MainSystem.getInstance().encrypte("Ferguson1"), "Alex Ferguson", "Ferguson@gmail.com", "Head Coach");
        coach3.createCoachPersonalPage(LocalDate.of(1965,7, 3), team3.getName());
        coach4 = new Coach("Krawietz1", MainSystem.getInstance().encrypte("Krawietz1"), "Peter Krawietz", "Krawietz@gmail.com", "Head Coach");
        coach4.createCoachPersonalPage(LocalDate.of(1946,7, 3), team4.getName());

        /**
         * FANS
         */
        fan1 = new Fan("Hofman", MainSystem.getInstance().encrypte("Hofman1"),"Omer Hofman","hofman@gmail.com");
        fan2 = new Fan("Katz",MainSystem.getInstance().encrypte("Katz11"),"Roi Katz","Katz@gmail.com");
        fan3 = new Fan("Schvartz",MainSystem.getInstance().encrypte("Schvartz1"),"tali Schvartz","talischvartz@gmail.com");
        fan4 = new Fan("Keset",MainSystem.getInstance().encrypte("Keset1"),"ido kest","keset@gmail.com");
        fan5 = new Fan("Szoke",MainSystem.getInstance().encrypte("Szoke1"),"Yiftah Szoke","szoke@gmail.com");

        /**DBLocal**/

        dbLocal.setLeague(league);

        dbLocal.setUser(mainReferee1);
        dbLocal.setUser(mainReferee2);

        dbLocal.setUser(refereee1);
        dbLocal.setUser(refereee2);
        dbLocal.setUser(refereee3);
        dbLocal.setUser(refereee4);
        dbLocal.setUser(refereee5);
        dbLocal.setUser(refereee6);

        dbLocal.setUser(representativee1);
        dbLocal.setUser(representativee2);

        dbLocal.setUser(administrator);

        dbLocal.setUser(coach1);
        dbLocal.setUser(coach2);
        dbLocal.setUser(coach3);
        dbLocal.setUser(coach4);

        dbLocal.setUser(teamOwner1);
        dbLocal.setUser(teamOwner2);
        dbLocal.setUser(teamOwner3);
        dbLocal.setUser(teamOwner4);

        dbLocal.setUser(manager1);
        dbLocal.setUser(manager2);
        dbLocal.setUser(manager3);
        dbLocal.setUser(manager4);

        dbLocal.setUser(fan1);
        dbLocal.setUser(fan2);
        dbLocal.setUser(fan3);
        dbLocal.setUser(fan4);
        dbLocal.setUser(fan5);

        dbLocal.addUser(player1);
        dbLocal.addUser(player2);
        dbLocal.addUser(player3);
        dbLocal.addUser(player4);
        dbLocal.addUser(player5);
        dbLocal.addUser(player6);
        dbLocal.addUser(player7);
        dbLocal.addUser(player8);
        dbLocal.addUser(player9);
        dbLocal.addUser(player10);
        dbLocal.addUser(player11);
        dbLocal.addUser(player12);
        dbLocal.addUser(player13);
        dbLocal.addUser(player14);
        dbLocal.addUser(player15);
        dbLocal.addUser(player16);
        dbLocal.addUser(player17);
        dbLocal.addUser(player18);
        dbLocal.addUser(player19);
        dbLocal.addUser(player20);
        dbLocal.addUser(player21);
        dbLocal.addUser(player22);
        dbLocal.addUser(player23);
        dbLocal.addUser(player24);
        dbLocal.addUser(player25);
        dbLocal.addUser(player26);
        dbLocal.addUser(player27);
        dbLocal.addUser(player28);
        dbLocal.addUser(player29);
        dbLocal.addUser(player30);
        dbLocal.addUser(player31);
        dbLocal.addUser(player32);
        dbLocal.addUser(player33);
        dbLocal.addUser(player34);
        dbLocal.addUser(player35);
        dbLocal.addUser(player36);
        dbLocal.addUser(player37);
        dbLocal.addUser(player38);
        dbLocal.addUser(player39);
        dbLocal.addUser(player40);
        dbLocal.addUser(player41);
        dbLocal.addUser(player42);
        dbLocal.addUser(player43);
        dbLocal.addUser(player44);
        dbLocal.addUser(player45);
        dbLocal.addUser(player46);
        dbLocal.addUser(player47);
        dbLocal.addUser(player48);
        dbLocal.addUser(player49);
        dbLocal.addUser(player50);

        dbLocal.setTeam(team1);
        dbLocal.setTeam(team2);
        dbLocal.setTeam(team3);
        dbLocal.setTeam(team4);

        /**
         * add coaches to teams
         */
        team1.addCoach(coach1);
        team2.addCoach(coach2);
        team3.addCoach(coach3);
        team4.addCoach(coach4);

        /**
         * add managers to teams
         */
        team1.addManager(manager1);
        team2.addManager(manager2);
        team3.addManager(manager3);
        team4.addManager(manager4);


        /**
         * add players to teams
         */
        team1.addPlayer(player1);
        team1.addPlayer(player2);
        team1.addPlayer(player3);
        team1.addPlayer(player4);
        team1.addPlayer(player5);
        team1.addPlayer(player6);
        team1.addPlayer(player7);
        team1.addPlayer(player8);
        team1.addPlayer(player9);
        team1.addPlayer(player10);
        team1.addPlayer(player11);

        team2.addPlayer(player12);
        team2.addPlayer(player13);
        team2.addPlayer(player14);
        team2.addPlayer(player15);
        team2.addPlayer(player16);
        team2.addPlayer(player17);
        team2.addPlayer(player18);
        team2.addPlayer(player19);
        team2.addPlayer(player20);
        team2.addPlayer(player21);
        team2.addPlayer(player22);

        team3.addPlayer(player23);
        team3.addPlayer(player24);
        team3.addPlayer(player25);
        team3.addPlayer(player26);
        team3.addPlayer(player27);
        team3.addPlayer(player28);
        team3.addPlayer(player29);
        team3.addPlayer(player30);
        team3.addPlayer(player31);
        team3.addPlayer(player32);
        team3.addPlayer(player33);

        team4.addPlayer(player34);
        team4.addPlayer(player35);
        team4.addPlayer(player36);
        team4.addPlayer(player37);
        team4.addPlayer(player38);
        team4.addPlayer(player39);
        team4.addPlayer(player40);
        team4.addPlayer(player41);
        team4.addPlayer(player42);
        team4.addPlayer(player43);
        team4.addPlayer(player44);

        /**
         * add team owners to teams
         */
        team1.addTeamOwner(teamOwner1);
        team2.addTeamOwner(teamOwner2);
        team3.addTeamOwner(teamOwner3);
        team4.addTeamOwner(teamOwner4);
        //teamOwner1.appoint(teamOwner2,"teamowner",10);

        /**
         * add team pages
         */
        team1.createPage("team in spain","spain");
        team2.createPage("team in spain","spain");
        team3.createPage("team in england","england");
        team4.createPage("team in england","england");

        /**
         * add follow fans
         */
        //fan1.followTeam(team1.getName());
        fan2.followTeam(team2.getName());
        fan3.followTeam(team3.getName());
        fan4.followThisPage(coach1.getUserFullName());
        fan5.followThisPage(player1.getUserFullName());

        dbLocal.setUser(fan1);
        dbLocal.setUser(fan2);
        dbLocal.setUser(fan3);
        dbLocal.setUser(fan4);
        dbLocal.setUser(fan5);

        /**
         * create season
         */

        /**DomainLayer.Teams in String**/
        ArrayList<String> stringTeams = new ArrayList<>();
        for(Team team:teams){
            stringTeams.add(team.getName());
        }

        /**Referee in String**/
        ArrayList<String> stringReferees = new ArrayList<>();
        for(Referee referee:referees){
            stringReferees.add(referee.getUserFullName());
        }

        /**AssociationRepresentative in String**/
        ArrayList<String> stringRepresentatives = new ArrayList<>();
        for(AssociationRepresentative representative:representatives){
            stringRepresentatives.add(representative.getUserFullName());
        }
        //representativee1.addSeasonToLeague(league.getName(), 2021, scorePolicy.getName(), gameInlayPolicy.getName(), stringTeams, stringReferees, stringRepresentatives);
        dbLocal.addLeague(league);
    }
}