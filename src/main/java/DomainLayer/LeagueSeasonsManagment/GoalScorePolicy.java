package DomainLayer.LeagueSeasonsManagment;

/**
 * this class represent the score policy:
 * 2 point for win, 1 point for draw and 0 point for lose
 * this policy prefer Gives priority to a team with more goals than a team with better goal different
 */
public class GoalScorePolicy implements IScorePolicy {

    private int win;
    private int draw;
    private int lost;
    private String name;
    private boolean goalDiff;

    /**
     * constructor
     */
    public GoalScorePolicy() {
        name = "GoalScorePolicy";
        goalDiff = false;
        win = 2;
        draw = 1;
        lost = 0;
    }

    /**
     * score policy
     */
    @Override
    public void scorePolicyAlgoImplementation() {

    }

    /**
     * priority
     *
     * @return false- prefer more goals
     */
    public boolean isGoalDiff() {
        return goalDiff;
    }

    /****gettets and setters****/
    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public String getName() {
        return name;
    }
}
