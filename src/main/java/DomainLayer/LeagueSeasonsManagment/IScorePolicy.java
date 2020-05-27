package DomainLayer.LeagueSeasonsManagment;

/**
 * interface for score policies
 */
public interface IScorePolicy {
    void scorePolicyAlgoImplementation();

    String getName();

    int getWin();

    int getDraw();

    int getLost();

    boolean isGoalDiff();

}
