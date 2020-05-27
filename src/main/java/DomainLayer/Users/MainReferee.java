package DomainLayer.Users;

import DomainLayer.Games.Game;

import java.util.ArrayList;

public class MainReferee extends Referee {

    public MainReferee(String userName, String password, String fullName, String userEmail, String qualification) {
        super(userName, password, fullName, userEmail, qualification);
    }

    ////////////////////////////// USE CASE 10.4 //////////////////////////////
    public void editGameEvents (String report){

        Game gameToEdit = findFinishGame();

        if(report != null && gameToEdit!= null)
            gameToEdit.setFinalReport(report);
    }


    public String displayGameEvents (){

        Game gameToEdit = findFinishGame();

        if(gameToEdit != null)
            return gameToEdit.getFinalReport();

        return "";
    }


    /**
     * this function display all mainReferee's games with details.
     * @return - list of Strings - each one represents a different game.
     */
    public ArrayList<Game> watchGamesList (){

        ArrayList<Game> allGames = new ArrayList<>();

        if (super.myGames != null) {
            for (Game g : super.myGames) {
                if( g != null && g.getStatus().equals(Game.gameStatus.preGame))
                    allGames.add(g);
            }
            return allGames;
        }
        return null;
    }


/*    private void deleteEvent(Event.eventType type, int time, String playerName) {

        Game gameToDeleteFrom = findFinishGame();
        ArrayList<Event> gameEventBook = gameToDeleteFrom.getEventBook();

        for (Event event : gameEventBook){
            if(event.getType().equals(type) && event.getEventTime()==time && event.getPlayerName().equals(playerName)) //the exact event we want to delete.
                gameEventBook.remove(event); //removes the event from original
        }

        gameToDeleteFrom.setEventBook(gameEventBook); // really necessary ???????
    }*/

 /*   private void editGEvent(Event.eventType type, int time, String playerName) {

        Game gameToEdit = findFinishGame();

        if (gameToEdit != null) { //there is a close game he can still edit (within 5 hours).
            Event addEvent = new Event(type, time, playerName);

            gameToEdit.addEvent(addEvent);
            MainSystem.LOG.info("A new event: " + type + " was added to game: " + gameToAddTo.getHomeTeam().getName() + "-" + gameToAddTo.getAwayTeam().getName() + ", " + gameToAddTo.getGameDate());
        }
    }*/

   /* private void addEvent(Event.eventType type, int time, String playerName) {

        Game gameToAddTo = findFinishGame();

        if (gameToAddTo != null) { //there is a close game he can still edit (within 5 hours).
            Event addEvent = new Event(type, time, playerName);

            MainSystem.LOG.info("A new event: " + type + " was added to game: " + gameToAddTo.getHomeTeam().getName() + "-" + gameToAddTo.getAwayTeam().getName() + ", " + gameToAddTo.getGameDate());
            gameToAddTo.addEvent(addEvent);
        }
    }*/

    //returns a game that already ended, but still within 5 hours, so can be edited.
    private Game findFinishGame() {
        for (Game game : super.myGames)
            if(game.getStatus().equals(Game.gameStatus.finish))
                return game;

        return null; //no finish game at the moment.
    }
}