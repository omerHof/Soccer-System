package DomainLayer.LeagueSeasonsManagment;

/**
 * this class represent the score policy:
 * 3 point for win, 1 point for draw and 0 point for lose
 * this policy prefer Gives priority to a team with better goal different than a team with more goals
 */
public class RegularScorePolicy implements IScorePolicy {

    private String name;
    private int win;
    private int draw;
    private int lost;
    private boolean goalDiff;

    public RegularScorePolicy() {
        name = "RegularScorePolicy";
        goalDiff = true;
        win = 3;
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
     * prefer goal different
     *
     * @return
     */
    public boolean isGoalDiff() {
        return goalDiff;
    }

    /***** getters ans setters*****/
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
