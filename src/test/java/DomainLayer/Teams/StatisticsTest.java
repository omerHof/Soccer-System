package DomainLayer.Teams;

import DomainLayer.LeagueSeasonsManagment.GoalScorePolicy;
import DomainLayer.LeagueSeasonsManagment.IScorePolicy;
import DomainLayer.LeagueSeasonsManagment.RegularScorePolicy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StatisticsTest {
    Statistics statisticsA;
    Statistics statisticsB;
    Statistics statisticsC;
    Statistics statisticsD;
    IScorePolicy policyAB;
    IScorePolicy policyCD;

    @Before
    public void setUp() throws Exception {
        policyAB = new RegularScorePolicy();
        policyCD = new GoalScorePolicy();
        statisticsA = new Statistics(policyAB);
        statisticsB = new Statistics(policyAB);
        statisticsC = new Statistics(policyCD);
        statisticsD = new Statistics(policyCD);
        statisticsA.setScore(3);
        statisticsA.setWins();
        statisticsA.setTie();
        statisticsA.setLoses();
        statisticsA.setLoses();
        statisticsA.setGoals(6);
        statisticsA.setGc(8);
        statisticsB.setScore(3);
        statisticsB.setGoals(2);
        statisticsB.setGc(2);
        statisticsB.setGoals(3);
        statisticsC.setScore(3);
        statisticsD.setScore(3);
        statisticsC.setGoals(6);
        statisticsC.setGc(8);
        statisticsD.setGoals(2);
        statisticsD.setGoals(2);




    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setNewSeasonStatistics() {
        try{
            statisticsA.setNewSeasonStatistics(policyAB);
            assertTrue("not equal",statisticsA.getGoals()==0);
            assertTrue("not equal",statisticsA.getScore()==0);
            assertTrue("not equal",statisticsA.getLoses()==0);
            assertTrue("not equal",statisticsA.getTie()==0);
            assertTrue("not equal",statisticsA.getWins()==0);
            assertTrue("not equal",statisticsA.getGc()==0);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getScore() {
        try{
            assertTrue("not equal",statisticsA.getScore()==7);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setScore() {
        try{
            statisticsB.setScore(4);
            assertTrue("not equal",statisticsB.getScore()==7);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getWins() {
        try{
            assertTrue("not equal",statisticsA.getWins()==1);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setWins() {
        try{
            statisticsA.setWins();
            assertTrue("not equal",statisticsA.getWins()==2);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getLoses() {
        try{
            assertTrue("not equal",statisticsA.getLoses()==2);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setLoses() {
        try{
            statisticsA.setLoses();
            assertTrue("not equal",statisticsA.getLoses()==3);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getGoals() {
        try{
            assertTrue("not equal",statisticsA.getGoals()==6);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setGoals() {
        try{
            statisticsA.setGoals(3);
            assertTrue("not equal",statisticsA.getGoals()==9);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getTie() {
        try{
            assertTrue("not equal",statisticsA.getTie()==1);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setTie() {
        try{
            statisticsA.setTie();
            assertTrue("not equal",statisticsA.getTie()==2);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getGc() {
        try{
            assertTrue("not equal",statisticsA.getGc()==8);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setGc() {
        try{
            statisticsA.setGc(4);
            assertTrue("not equal",statisticsA.getGc()==12);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getDif() {
        try{
            assertTrue("not equal",statisticsA.getDif()==-2);

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void compareTo() {
        try{
            statisticsB.setScore(4);
            assertTrue("not equal",statisticsA.compareTo(statisticsB)==1);
            assertTrue("not equal",statisticsC.compareTo(statisticsD)==-1);
        }catch (Exception e) {
            System.out.println("error");
        }
    }
}