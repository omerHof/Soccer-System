package DomainLayer.Teams;

import DomainLayer.LeagueSeasonsManagment.IScorePolicy;

/**
 * this class represent the statistics of a team
 */
public class Statistics implements Comparable {

    private int score;
    private int wins;
    private int loses;
    private int tie;
    private int gs;
    private int gc;
    private IScorePolicy policy;

    public Statistics(IScorePolicy policy) {
        setNewSeasonStatistics(policy);
    }

    /**
     * initial the statistic for a team
     *
     * @param policy
     */
    public void setNewSeasonStatistics(IScorePolicy policy) {
        this.score = 0;
        this.wins = 0;
        this.loses = 0;
        this.tie = 0;
        this.gs = 0;
        this.gc = 0;
        this.policy = policy;
    }

    /*****getters ans setters*****/

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        this.wins++;
        this.setScore(policy.getWin());
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses() {
        this.loses++;
        this.setScore(policy.getLost());
    }

    public int getGoals() {
        return gs;
    }

    public void setGoals(int goals) {
        this.gs += goals;
    }

    public int getTie() {
        return tie;
    }

    public void setTie() {
        this.tie++;
        this.setScore(policy.getDraw());
    }
    public void setWinsFromDB(int wins){
        this.wins=wins;
    }
    public void setTiesFromDB(int ties){
        this.tie=ties;
    }
    public void setLosesFromDB(int loses){
        this.loses=loses;
    }
    public void setGoalsFromDB(int goals){
        this.gs=goals;
    }
    public void setGcFromDB(int gc){
        this.gc=gc;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc += gc;
    }

    public int getDif() {
        return gs - gc;
    }

    /**
     * compare between two statistics
     * @param obj other team stat
     * @return which stat is better
     */
    @Override
    public int compareTo(Object obj) {
        Statistics other = (Statistics) obj;
        if (this.getScore() > other.getScore()) {
            return -1;
        }
        if (this.getScore() < other.getScore()) {
            return 1;
        }
        if (this.getScore() == other.getScore() && policy.isGoalDiff()) {
            if (this.getDif() > other.getDif()) {
                return -1;
            }
            if (this.getDif() < other.getDif()) {
                return 1;
            }
            if (this.getGoals() > other.getGoals()) {
                return -1;
            }
            if (this.getGoals() < other.getGoals()) {
                return 1;
            }
        }
        if (this.getScore() == other.getScore() && !policy.isGoalDiff()) {
            if (this.getGoals() > other.getGoals()) {
                return -1;
            }
            if (this.getGoals() < other.getGoals()) {
                return 1;
            }
            if (this.getDif() > other.getDif()) {
                return -1;
            }
            if (this.getDif() < other.getDif()) {
                return 1;
            }
        }
        return 0;
    }

    public IScorePolicy getPolicy() {
        return policy;
    }
}