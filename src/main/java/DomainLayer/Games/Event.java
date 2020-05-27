package DomainLayer.Games;

public class Event {

    /**
     * ***** all types of event it can have are:*******
     * goal, offside, foul, redTicket, yellowTicket, injury, substitution
     */
    public enum eventType {
        goal, offside, foul, redTicket, yellowTicket, injury, substitution;
    }

    private Integer eventTime;
    private eventType type;
    private String playerName;


    /**
     * constructor - all the event's details:
     * @param type - enum for this class
     * @param eventTime - minutes into the game
     * @param playerName - name of the player involved in the eevnt
     */
    public Event(eventType type, int eventTime, String playerName) {
        if (eventTime >= 0 && eventTime <= 120 && playerName != "") {
            this.eventTime = eventTime;
            this.type = type;
            this.playerName = playerName;
        }
    }

    /**********getters and setters**********/

    public int getEventTime() {
        return eventTime;
    }

    public eventType getType() {
        return type;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public void setType(eventType type) {
        this.type = type;
    }

    public void setPlayerName(String  playerName) {
        this.playerName = playerName;
    }

    public String evenToString(){
        String event=eventTime+" "+type.name()+" "+playerName;
        return event;
    }
}
