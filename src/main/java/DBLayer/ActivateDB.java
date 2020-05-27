package DBLayer;

import DomainLayer.Games.Game;
import DomainLayer.LeagueSeasonsManagment.*;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.Notification;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Statistics;
import DomainLayer.Teams.Team;
import DomainLayer.Users.*;
import com.mongodb.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


/**
 * @author Shamik Mitra
 *
 */
public class ActivateDB {
    private static ActivateDB db;
    MongoContext context;
    private ActivateDB(){
        context = MongoContext.get().connectDb("soccerAssociation");
    }
    public static ActivateDB getInstance() {
        if (db == null) {
            synchronized (DBLocal.class) {
                if (db == null) {
                    db = new ActivateDB();
                }
            }
        }
        return db;
    }
    /**
     *
     */
    /*public static void main(String[] args) throws InterruptedException {

        DBLayer dataBase= new DBLayer();
        DBLocal.getInstance().writeToMongo();
        //readInfo();



    }*/


    public void writeInfo(ArrayList<User> users, ArrayList<Team> teams, ArrayList<League> leagues){
        //MongoContext context = MongoContext.get().connectDb("soccerAssociation");
        DB db=context.getDb();

        db.createCollection("Users",null);
        db.createCollection("Teams",null);
        db.createCollection("Leagues",null);
        db.createCollection("Seasons",null);
        db.createCollection("Games",null);

        db.createCollection("Test",null);


        writeUsers(users,db);
        writeTeams(teams,db);
        writeLeagues(leagues,db);
        writeGames(leagues,db);
        writeSeasons(leagues,db);
        //MongoDatabase database = context.getDatabase();
        //writeTest(teams,database);

    }

    private void writeGames(ArrayList<League> leagues, DB db) {
        DBCollection collection = db.getCollection("Games");
        collection.drop();

        for (League league : leagues) {
            for (Season season : league.getAllSeasons()) {
                int round=1;

                for (ArrayList<Game> games : season.getAllGames().values()) {

                    for(Game game:games){
                        BasicDBObject document = new BasicDBObject();
                        //--------GAME ID--------//
                        document.put("id", game.getId()+"");
                        //--------LEAGUE NAME--------//
                        document.put("leagueName", league.getName());

                        //--------SEASON YEAR--------//
                        document.put("seasonYear", season.getYear()+"");

                        //--------ROUND--------//
                        document.put("round", round+"");

                        //--------GAME STATUS--------//
                        document.put("status", game.getStatus().toString());

                        //--------TEAMS--------//
                        document.put("homeTeam", game.getHomeTeam().getName());
                        document.put("awayTeam", game.getAwayTeam().getName());

                        //--------SCORE--------//
                        document.put("score", game.getScore());

                        //--------FINAL REPORT--------//
                        document.put("finalReport", game.getFinalReport());

                        //--------TIME--------//
                        document.put("gameTime", game.getTimeOfGame().toString());

                        collection.insert(document);

                    }
                    round++;
                }
            }
        }
    }

    private void writeSeasons(ArrayList<League> leagues, DB db) {
        DBCollection collection = db.getCollection("Seasons");
        collection.drop();

        for (League league : leagues) {
            for(Season season:league.getAllSeasons()){
                BasicDBObject document = new BasicDBObject();
                //--------LEAGUE NAME--------//
                document.put("leagueName", league.getName());

                //--------YEAR--------//
                document.put("seasonYear", season.getYear()+"");

                //--------TEAMS--------//
                ArrayList<String> allTeams = new ArrayList<>();
                for(Team team:season.getAllTeams()) {
                    allTeams.add(team.getName());
                }
                document.put("teams", allTeams);

                //--------REFEREES--------//
                ArrayList<String> allReferees = new ArrayList<>();
                for(Referee referee:season.getAllReferees()) {
                    allReferees.add(referee.getUserFullName());
                }
                document.put("referees", allReferees);

                //--------ASSOCIATION REPRESENTATIVE--------//
                ArrayList<String> allAsso = new ArrayList<>();
                for(AssociationRepresentative representative:season.getAllRepresentatives()) {
                    allAsso.add(representative.getUserFullName());
                }
                document.put("representatives", allAsso);

                //--------POLICIES--------//
                document.put("scorePolicy", season.getiScorePolicy().getName());
                document.put("gamePolicy", season.getiGameInlayPolicy().getName());

                collection.insert(document);
            }
        }
    }


    private void writeLeagues(ArrayList<League> leagues, DB db) {
        DBCollection collection = db.getCollection("Leagues");
        collection.drop();

        for (League league : leagues) {
            BasicDBObject document = new BasicDBObject();
            //--------LEAGUE NAME--------//
            document.put("leagueName", league.getName());

            //--------NUMBER OF TEAMS--------//
            document.put("numOfTeams", league.getNumOfTeams()+"");

            //--------SEASONS--------//
            ArrayList<String> seasons = new ArrayList<>();
            for(Season season:league.getAllSeasons()){
                seasons.add(season.getYear()+"");
            }
            document.put("seasons", seasons);

            collection.insert(document);

        }
    }

    private void writeTeams(ArrayList<Team> teams, DB db) {
        DBCollection collection = db.getCollection("Teams");
        collection.drop();

        for(Team team:teams){
            BasicDBObject document = new BasicDBObject();

            //--------TEAM NAME--------//
            document.put("teamName", team.getName());

            //--------STATUS--------//
            document.put("status", team.getStatus().toString());

            //--------BUDGET--------//
            document.put("budget", team.getBudget()+"");

            //--------STADIUM--------//
            ArrayList<String> stadiumDetails = new ArrayList<>();
            stadiumDetails.add(team.getStadium().getName());
            stadiumDetails.add(team.getStadium().getCapacity()+"");
            stadiumDetails.add(team.getStadium().getPrice()+"");
            stadiumDetails.add(team.getStadium().getWorth()+"");
            document.put("stadium", stadiumDetails);

            //--------PLAYERS--------//
            ArrayList<String> players = new ArrayList<>();
            players=new ArrayList<>(team.getPlayers().keySet());
            document.put("players", players);

            //--------COACHES--------//
            ArrayList<String> coaches = new ArrayList<>();
            coaches=new ArrayList<>(team.getCoaches().keySet());
            document.put("coaches", coaches);

            //--------TEAM OWNERS--------//
            ArrayList<String> teamOwners = new ArrayList<>();
            teamOwners=new ArrayList<>(team.getTeamOwners().keySet());
            document.put("teamOwners", teamOwners);

            //--------MANAGERS--------//
            ArrayList<String> managers = new ArrayList<>();
            managers=new ArrayList<>(team.getManagers().keySet());
            document.put("managers", managers);

            //--------STATISTICS--------//
            ArrayList<String> statistic = new ArrayList<>();
            statistic.add(team.getStatistics().getPolicy().getName()+"");
            statistic.add(team.getStatistics().getScore()+"");
            statistic.add(team.getStatistics().getWins()+"");
            statistic.add(team.getStatistics().getLoses()+"");
            statistic.add(team.getStatistics().getTie()+"");
            statistic.add(team.getStatistics().getGoals()+"");
            statistic.add(team.getStatistics().getGc()+"");
            document.put("statistic", statistic);

            //--------PAGE--------//
            ArrayList<String> teamPage = new ArrayList<>();
            teamPage.add(team.getPage().getHistory());
            teamPage.add(team.getPage().getNation());
            document.put("teamPage", teamPage);

            collection.insert(document);

        }

    }

    private void writeUsers(ArrayList<User> users,DB db) {
        DBCollection collection = db.getCollection("Users");
        collection.drop();

        for(User user: users){
            BasicDBObject document = new BasicDBObject();
            document.put("userName", user.getUserName());
            document.put("password", user.getPassword());
            document.put("role",DBLocal.getInstance().getUserType(user));
            document.put("userFullName", user.getUserFullName());
            document.put("userEmail", user.getUserEmail());
            String role = user.getClass().getSimpleName();

            switch (role) {
                case "Fan":
                    Fan fan = (Fan)user;
                    ArrayList<String> followedPages = new ArrayList<>();
                    ArrayList<String> followedTeams = new ArrayList<>();
                    for(PersonalPage page:fan.getFollowedPages()) {
                        followedPages.add(page.getName());
                    }
                    followedTeams = new ArrayList<>(fan.getFollowedTeams().keySet());
                    document.put("fanFollowedPages", followedPages);
                    document.put("fanFollowedTeams", followedTeams);


                    break;
                case "Player":
                    Player player = (Player)user;
                    LocalDate birthDate= LocalDate.now().minus(player.getAge(), ChronoUnit.YEARS);
                    String date=birthDate.toString();
                    document.put("birth date", date);
                    document.put("playerPosition", player.getCourtRole());
                    document.put("salary", player.getSalary());
                    document.put("worth", player.getWorth());
                    if(player.getCurrentTeam()!=null) {
                        document.put("currentTeam", player.getCurrentTeam().getName());
                    }
                    else{
                        document.put("currentTeam", null);
                    }

                    if(player.getPage()!=null) {
                        ArrayList<String> playerPage = new ArrayList<>();
                        playerPage.add(player.getPage().getShirtNumber() + "");
                        playerPage.add(player.getPage().getWeight() + "");
                        playerPage.add(player.getPage().getHeight() + "");
                        document.put("personalPage", playerPage);
                    }
                    break;
                case "Coach":
                    Coach coach= (Coach)user;
                    document.put("coachPosition", coach.getTeamRole());
                    document.put("salary", coach.getSalary());
                    document.put("worth", coach.getWorth());

                    if(coach.getCurrentTeam()!=null) {
                        document.put("currentTeam", coach.getCurrentTeam().getName());
                    }
                    else{
                        document.put("currentTeam", null);
                    }

                    if(coach.getPage()!=null) {
                        ArrayList<String> coachPage = new ArrayList<>();
                        coachPage.add(coach.getPage().getAge() + "");
                        if(coach.getPage().getCurrentTeam()!=null) {
                            coachPage.add(coach.getPage().getCurrentTeam().getName() + "");
                        }
                        document.put("personalPage", coachPage);
                    }
                    break;
                case "AssociationRepresentative":
                    ArrayList<Integer> repGamesId = new ArrayList<>();
                    AssociationRepresentative representative = (AssociationRepresentative)user;
                    for(Game game:representative.getMyGames()){
                        repGamesId.add(game.getId());
                    }
                    document.put("myGames", repGamesId);


                    break;
                case "TeamOwner":
                    TeamOwner teamOwner= (TeamOwner)user;
                    document.put("worth", teamOwner.getWorth());
                    document.put("TeamOwnerAfford", teamOwner.isAfford());
                    ArrayList<String> team_owners_appointments = new ArrayList<>();
                    ArrayList<String> managers_appointments = new ArrayList<>();
                    team_owners_appointments=new ArrayList<>(teamOwner.getTeam_owners_appointments().keySet());
                    managers_appointments=new ArrayList<>(teamOwner.getManagers_appointments().keySet());
                    document.put("TeamOwnerAppointment", team_owners_appointments);
                    document.put("TeamOwnerManagerAppointment", managers_appointments);
                    if(teamOwner.getTeam()!=null) {
                        document.put("currentTeam", teamOwner.getTeam().getName());
                    }
                    else{
                        document.put("currentTeam", null);
                    }

                    break;
                case "Manager":
                    Manager manager= (Manager) user;
                    document.put("worth", manager.getWorth());
                    if(manager.getTeam()!=null) {
                        document.put("currentTeam", manager.getTeam().getName());
                    }
                    else{
                        document.put("currentTeam", null);
                    }
                    break;
                case "Administrator":

                    break;
                case "Referee":
                    Referee referee= (Referee)user;
                    document.put("refereeQualification", referee.getQualification());
                    ArrayList<Integer> refereeGamesId = new ArrayList<>();
                    for(Game game:referee.getMyGames()){
                        refereeGamesId.add(game.getId());
                    }
                    document.put("myGames", refereeGamesId);
                    break;
                case "MainReferee":
                    MainReferee mainReferee= (MainReferee)user;
                    document.put("refereeQualification", mainReferee.getQualification());
                    ArrayList<Integer> mainRefereeGamesId = new ArrayList<>();
                    for(Game game:mainReferee.getMyGames()){
                        mainRefereeGamesId.add(game.getId());
                    }
                    document.put("myGames", mainRefereeGamesId);
                    break;

                default:
                    user=null;
            }

            List<String> recived = new ArrayList<String>();
            for(Notification notification:user.getReceivedNotifications()){
                recived.add(notification.getContext());
            }
            List<String> read = new ArrayList<String>();
            for(Notification notification:user.getReadNotifications()){
                read.add(notification.getContext());
            }
            document.append("receivedNotifications", recived);
            document.append("readNotifications", read);

            collection.insert(document);


        }
    }

    public void readInfo() {

        //MongoContext context = MongoContext.get().connectDb("soccerAssociation");
        readUsers(context);

        readTeams(context);
        readLeagues(context);
        readSeasons(context);
        exstensions(context);
        DBLocal dbLocal = DBLocal.getInstance();


    }

    private void exstensions(MongoContext context) {
        DBLocal dbLocal = DBLocal.getInstance();

        //-----AssociationRepresentative----//
        ArrayList<User> representatives = DBLocal.getInstance().getUserTypeList("AssociationRepresentative");
        for (User user : representatives) {
            AssociationRepresentative representative = (AssociationRepresentative) user;
            ArrayList<Integer> gamesId = representative.getGamesId();
            for (int gameId : gamesId) {
                representative.followThisGame((DBLocal.getInstance().getGameById(gameId)));
            }
        }
        //-----referee----//
        ArrayList<User> referees = DBLocal.getInstance().getUserTypeList("Referee");
        for (User user : referees) {
            Referee referee = (Referee) user;
            ArrayList<Integer> gamesId = referee.getGamesId();
            for (int gameId : gamesId) {
                referee.followThisGame((DBLocal.getInstance().getGameById(gameId)));
            }
        }
        //--fan--//
        ArrayList<User> fans = DBLocal.getInstance().getUserTypeList("Fan");
        for (User user : fans) {
            Fan fan = (Fan) user;
            for(String team:fan.getFollowedTeams().keySet()){
                fan.followTeam(team);
            }
        }


        System.out.println();
    }

    private HashMap<Integer,ArrayList<Game>>  readGames(MongoContext context, String league, int year) {
        DBCursor result = context.findByKey2("Games");
        List<DBObject> dbObjects = result.toArray();
        HashMap<Integer,ArrayList<Game>> allGames = new HashMap<>();
        ArrayList<Game> gamesInRound = new ArrayList<>();
        int round=1;
        for(DBObject dbObject:dbObjects) {
            String leagueName = (String)dbObject.get("leagueName");
            int seasomYear = Integer.parseInt((String)dbObject.get("seasonYear"));

            if(leagueName.equals(league)&&seasomYear==year) {
                String roundString = (String) dbObject.get("round");
                int currentRound = Integer.parseInt(roundString);
                if(currentRound!=round){
                    ArrayList<Game> gamesInRoundTemp = new ArrayList<>();
                    for(Game game:gamesInRound){
                        gamesInRoundTemp.add(game);
                    }
                    allGames.put(round,gamesInRoundTemp);
                    gamesInRound.clear();
                    round=currentRound;
                }
                String homeTeam = (String)dbObject.get("homeTeam");
                String awayTeam = (String)dbObject.get("awayTeam");
                String gameTime = (String)dbObject.get("gameTime");
                LocalDateTime timeOfGame=null;
                if(gameTime!=null){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    formatter = formatter.withLocale( Locale.US );
                    timeOfGame = LocalDateTime.parse(gameTime, formatter);
                }

                Game game = new Game(DBLocal.getInstance().getTeam(homeTeam),DBLocal.getInstance().getTeam(awayTeam),timeOfGame);

                //todo add game to teams
                //todo think how to add game to users

                String score = (String)dbObject.get("score");
                String finalReport = (String)dbObject.get("finalReport");
                String status = (String)dbObject.get("status");
                int id = Integer.parseInt((String)dbObject.get("id"));

                Game.gameStatus gameStatus =Game.gameStatus.valueOf(status);

                game.setScore(score);
                game.setFinalReport(finalReport);
                game.setStatus(gameStatus);
                game.setId(id);

                DBLocal.getInstance().getTeam(homeTeam).addGame(game);
                DBLocal.getInstance().getTeam(awayTeam).addGame(game);

                gamesInRound.add(game);

            }
        }
        allGames.put(round,gamesInRound);
        return allGames;
    }

    private void readSeasons(MongoContext context) {
        DBCursor result = context.findByKey2("Seasons");
        List<DBObject> dbObjects = result.toArray();
        for(DBObject dbObject:dbObjects) {
            String leagueName = (String)dbObject.get("leagueName");
            int seasomYear = Integer.parseInt((String)dbObject.get("seasonYear"));
            ArrayList<String> teamsString = (ArrayList<String>)dbObject.get("teams");
            ArrayList<String> refereesString = (ArrayList<String>)dbObject.get("referees");
            ArrayList<String> representativesString = (ArrayList<String>)dbObject.get("representatives");
            String scorePolicy = (String)dbObject.get("scorePolicy");
            String gamePolicy = (String)dbObject.get("gamePolicy");

            ArrayList <Team> teams = new ArrayList<>();
            for(String team:teamsString){
                teams.add(DBLocal.getInstance().getTeam(team));
            }
            ArrayList <Referee> referees = new ArrayList<>();
            for(String referee:refereesString){
                referees.add((Referee)DBLocal.getInstance().getUser(referee));
            }
            ArrayList <AssociationRepresentative> representatives = new ArrayList<>();
            for(String asso:representativesString){
                representatives.add((AssociationRepresentative)DBLocal.getInstance().getUser(asso));
            }

            Season season = new Season(seasomYear,teams,referees,representatives,scorePolicy,gamePolicy);
            season.setAllGames(readGames(context,leagueName,seasomYear));
            DBLocal.getInstance().getLeague(leagueName).addSeason(season);

        }

    }

    private void readLeagues(MongoContext context) {
        DBCursor result = context.findByKey2("Leagues");
        List<DBObject> dbObjects = result.toArray();
        for(DBObject dbObject:dbObjects) {
            String leagueName = (String)dbObject.get("leagueName");
            String numOfTeams = (String)dbObject.get("numOfTeams");
            ArrayList<String> seasons = (ArrayList<String>)dbObject.get("seasons");

            League league = new League(leagueName,Integer.parseInt(numOfTeams));

            DBLocal.getInstance().setLeague(league);
        }

    }

    private void readTeams(MongoContext context) {
        DBCursor result = context.findByKey2("Teams");
        List<DBObject> dbObjects = result.toArray();
        for(DBObject dbObject:dbObjects) {

            String teamName = (String)dbObject.get("teamName");
            String status = (String)dbObject.get("status");
            String budget = (String)dbObject.get("budget");
            ArrayList<String> statisticsDetails = (ArrayList<String>)dbObject.get("statistic");
            ArrayList<String> stadiumDetails = (ArrayList<String>)dbObject.get("stadium");
            ArrayList<String> teamPage = (ArrayList<String>)dbObject.get("teamPage");
            ArrayList<String> players = (ArrayList<String>)dbObject.get("players");
            ArrayList<String> coaches = (ArrayList<String>)dbObject.get("coaches");
            ArrayList<String> teamOwners = (ArrayList<String>)dbObject.get("teamOwners");
            ArrayList<String> managers = (ArrayList<String>)dbObject.get("managers");

            Team team = new Team(teamName);

            Team.teamStatus teamStatus =Team.teamStatus.valueOf(status);
            team.setStatus(teamStatus);
            team.setBudget(Double.parseDouble(budget));

            Stadium stadium = new Stadium(Double.parseDouble(stadiumDetails.get(3)),Integer.parseInt(stadiumDetails.get(1)),Double.parseDouble(stadiumDetails.get(2)));
            stadium.setName(stadiumDetails.get(0));
            team.setStadium(stadium);

            IScorePolicy iScorePolicy;
            switch(statisticsDetails.get(0)) {
                case "RegularScorePolicy":
                    iScorePolicy = new RegularScorePolicy();
                    break;
                case "GoalScorePolicy":
                    iScorePolicy = new GoalScorePolicy();
                    break;
                default:
                    iScorePolicy = new RegularScorePolicy();
            }
            Statistics statistics = new Statistics(iScorePolicy);
            statistics.setScore(Integer.parseInt(statisticsDetails.get(1)));
            statistics.setWinsFromDB(Integer.parseInt(statisticsDetails.get(2)));
            statistics.setLosesFromDB(Integer.parseInt(statisticsDetails.get(3)));
            statistics.setTiesFromDB(Integer.parseInt(statisticsDetails.get(4)));
            statistics.setGcFromDB(Integer.parseInt(statisticsDetails.get(5)));
            statistics.setGcFromDB(Integer.parseInt(statisticsDetails.get(6)));

            team.setStatistics(statistics);

            if(players!=null) {
                for (String player : players) {
                    team.addPlayer(player);
                }
            }
            if(coaches!=null) {
                for (String coach : coaches) {
                    team.addCoach(coach);
                }
            }
            if(teamOwners!=null) {
                for (String teamOwner : teamOwners) {
                    team.addTeamOwner(teamOwner);
                }
            }
            if(managers!=null) {
                for (String manager : managers) {
                    team.addManager(manager);
                }
            }

            team.createPage(teamPage.get(0),teamPage.get(1));

            DBLocal.getInstance().setTeam(team);

        }

    }

    private void readUsers(MongoContext context) {
        DBCursor result = context.findByKey2("Users");
        List<DBObject> dbObjects = result.toArray();

        for(DBObject dbObject:dbObjects) {

            //--------GENERAL--------//
            String userName = (String)dbObject.get("userName");
            String password = (String)dbObject.get("password");
            String role = (String)dbObject.get("role");
            String userFullName = (String)dbObject.get("userFullName");
            String userEmail = (String)dbObject.get("userEmail");

            //--------PLAYER--------//
            String playerPosition = (String)dbObject.get("playerPosition");
            String currentTeam = (String)dbObject.get("currentTeam");
            String dateTime = (String) dbObject.get("birth date");
            LocalDate playerBirthDate=null;
            if(dateTime!=null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale( Locale.US );
                playerBirthDate = LocalDate.parse(dateTime, formatter);
            }

            //--------COACH--------//
            String coachPosition = (String)dbObject.get("coachPosition");

            int salary=0;
            if(dbObject.get("salary")!=null) {
                salary = (int) dbObject.get("salary");
            }
            double worth=0;
            if(dbObject.get("worth")!=null) {
                worth = (double) dbObject.get("worth");
            }
            ArrayList<String> personalPage = (ArrayList<String>)dbObject.get("personalPage");

            //--------FAN--------//
            ArrayList<String> fanFollowedTeams = (ArrayList<String>)dbObject.get("fanFollowedTeams");

            //--------TEAM OWNER--------//

            boolean teamOwnerAfford=false;
            if(dbObject.get("TeamOwnerAfford")!=null) {
                teamOwnerAfford = (boolean) dbObject.get("TeamOwnerAfford");
            }
            //--------REFEREE--------//
            String refereeQualification = (String)dbObject.get("refereeQualification");
            ArrayList<String> received = (ArrayList<String>)dbObject.get("receivedNotifications");
            ArrayList<String> read = (ArrayList<String>)dbObject.get("readNotifications");

            //--------Asso--------//
            ArrayList<Integer> gamesId = (ArrayList<Integer>)dbObject.get("myGames");

            User user;
            switch (role) {
                case "Fan":
                    user = new Fan(userName,password,userFullName,userEmail);

                    for(String team:fanFollowedTeams){
                        ((Fan) user).getFollowedTeams().put(team,DBLocal.getInstance().getTeam(team));
                    }

                    break;
                case "Player":

                    user = new Player(userName,password,userFullName,userEmail,playerBirthDate,playerPosition);
                    ((Player) user).setSalary(salary);
                    ((Player) user).setWorth(worth);
                    ((Player) user).setCurrentTeam(currentTeam);
                    if(personalPage!=null) {
                        if (((Player) user).getCurrentTeam() != null) {
                            ((Player) user).createPersonalPage(Double.parseDouble(personalPage.get(2)), Integer.parseInt(personalPage.get(1)), Integer.parseInt(personalPage.get(0)), ((Player) user).getCurrentTeam().getName());
                        }
                        else {
                            ((Player) user).createPersonalPage(Double.parseDouble(personalPage.get(2)), Integer.parseInt(personalPage.get(1)), Integer.parseInt(personalPage.get(0)),null);
                        }
                    }
                    break;
                case "Coach":
                    user = new Coach(userName,password,userFullName,userEmail,coachPosition);
                    ((Coach) user).setSalary(salary);
                    ((Coach) user).setWorth(worth);

                    ((Coach) user).setCurrentTeam(currentTeam);
                    if(personalPage!=null) {
                        LocalDate localDate = LocalDate.now().minus(Integer.parseInt(personalPage.get(0)),ChronoUnit.YEARS);
                        if (((Coach) user).getCurrentTeam() != null) {
                            ((Coach) user).createCoachPersonalPage(localDate,((Coach) user).getCurrentTeam().getName());
                        }
                        else {
                            ((Coach) user).createCoachPersonalPage(localDate,null);
                        }
                    }
                    break;
                case "AssociationRepresentative":
                    user = new AssociationRepresentative(userName,password,userFullName,userEmail);
                    ((AssociationRepresentative) user).setGamesId(gamesId);

                    break;
                case "TeamOwner":
                    user = new TeamOwner(userName,password,userFullName,userEmail);
                    ((TeamOwner) user).setWorth(worth);
                    ((TeamOwner) user).setAfford(teamOwnerAfford);

                    ((TeamOwner) user).setCurrentTeam(currentTeam);

                    break;
                case "Manager":
                    user = new Manager(userName,password,userFullName,userEmail);
                    ((Manager) user).setWorth(worth);

                    ((Manager) user).setCurrentTeam(currentTeam);
                    break;
                case "Administrator":
                    user = new Administrator(userName,password,userFullName,userEmail);

                    break;
                case "Referee":
                    user = new Referee(userName,password,userFullName,userEmail,refereeQualification);
                    ((Referee) user).setGamesId(gamesId);


                    break;
                case "MainReferee":
                    user = new MainReferee(userName,password,userFullName,userEmail,refereeQualification);
                    ((MainReferee) user).setGamesId(gamesId);
                    break;

                default:
                    user=null;
            }

            //--------NOTIFICATION--------//
            ArrayList<Notification> receivedNotifications = new ArrayList<>();
            for(String reciveNot:received){
                Notification notification = new Notification(user,reciveNot,user);
                receivedNotifications.add(notification);
            }
            user.setReceivedNotifications(receivedNotifications);

            ArrayList<Notification> readNotifications = new ArrayList<>();
            for(String readNot:read){
                Notification notification = new Notification(user,readNot,user);
                readNotifications.add(notification);
            }
            user.setReadNotifications(readNotifications);

            DBLocal.getInstance().setUser(user);
        }
        for(DBObject dbObject:dbObjects){
            ArrayList<String> fanFollowedPages = (ArrayList<String>)dbObject.get("fanFollowedPages");
            ArrayList<String> TeamOwnerAppointment = (ArrayList<String>)dbObject.get("TeamOwnerAppointment");
            ArrayList<String> TeamOwnerManagerAppointment = (ArrayList<String>)dbObject.get("TeamOwnerManagerAppointment");

            String userName = (String)dbObject.get("userName");
            String role = (String)dbObject.get("role");
            if(role.equals("Fan")) {
                User user = DBLocal.getInstance().getUser(userName);
                for (String userPage : fanFollowedPages) {
                    Fan fan = (Fan)user;
                    fan.followThisPage(userPage);
                }
                DBLocal.getInstance().setUser(user);
            }
            if(role.equals("TeamOwner")){
                User user = DBLocal.getInstance().getUser(userName);

                for(String teamOwner:TeamOwnerAppointment){
                    ((TeamOwner) user).getTeam_owners_appointments().put(teamOwner,(TeamOwner)DBLocal.getInstance().getUser(teamOwner));
                }
                for(String manager:TeamOwnerManagerAppointment){
                    ((TeamOwner) user).getManagers_appointments().put(manager,(Manager) DBLocal.getInstance().getUser(manager));
                }
                DBLocal.getInstance().setUser(user);
            }
        }
    }
}