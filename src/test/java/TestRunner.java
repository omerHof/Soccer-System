import DomainLayer.Games.CloseGameTest;
import DomainLayer.Games.DayToGameTest;
import DomainLayer.Games.EndGameTest;
import DomainLayer.Games.StartGameTest;
import DomainLayer.LeagueSeasonsManagment.*;
import DomainLayer.SystemLogic.DBLocalTest;
import DomainLayer.SystemLogic.MainSystemTest;
import DomainLayer.SystemLogic.NotificationTest;
import DomainLayer.SystemLogic.SearchTest;
import DomainLayer.Teams.StadiumTest;
import DomainLayer.Teams.StatisticsTest;
import DomainLayer.Teams.TeamPageTest;
import DomainLayer.Teams.TeamTest;
import UILayer.MainTest;
import DomainLayer.UserGenerator.ManagmentUserGeneratorTest;
import DomainLayer.UserGenerator.PremiumUserGenertatorTest;
import DomainLayer.UserGenerator.SimpleUserGeneratorTest;
import DomainLayer.Users.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {

        /** ----------------- INITIALIZE ----------------- **/

        /** DomainLayer.Games **/
        Result closeGame_result = JUnitCore.runClasses(CloseGameTest.class);
        Result dayToGame_result = JUnitCore.runClasses(DayToGameTest.class);
        Result endGame_result = JUnitCore.runClasses(EndGameTest.class);
        Result startGame_result = JUnitCore.runClasses(StartGameTest.class);

        /** LeagueSeasonsManagement **/
        Result goalScorePolicy_result = JUnitCore.runClasses(GoalScorePolicyTest.class);
        Result oneRoundGamePolicy_result = JUnitCore.runClasses(OneRoundGamePolicyTest.class);
        Result randomTwoRoundsGamePolicy_result = JUnitCore.runClasses(RandomTwoRoundsGamePolicyTest.class);
        Result regularScorePolicy_result = JUnitCore.runClasses(RegularScorePolicyTest.class);
        Result seasonScoreBoard_result = JUnitCore.runClasses(SeasonScoreBoardTest.class);
        Result season_result = JUnitCore.runClasses(SeasonTest.class);
        Result twoRoundsGamePolicy_result = JUnitCore.runClasses(TwoRoundsGamePolicyTest.class);

        /** MainApp**/
        Result app_result = JUnitCore.runClasses(MainTest.class);

        /** System Logic **/
        Result DB_result = JUnitCore.runClasses(DBLocalTest.class);
        Result mainSystem_result = JUnitCore.runClasses(MainSystemTest.class);
        Result notification_result = JUnitCore.runClasses(NotificationTest.class);
        Result search_result = JUnitCore.runClasses(SearchTest.class);

        /** DomainLayer.Teams **/
        Result stadium_result = JUnitCore.runClasses(StadiumTest.class);
        Result statistics_result = JUnitCore.runClasses(StatisticsTest.class);
        Result teamPage_result = JUnitCore.runClasses(TeamPageTest.class);
        Result team_result = JUnitCore.runClasses(TeamTest.class);

        /** DomainLayer.UserGenerator **/
        Result managmentUserGenerator_result = JUnitCore.runClasses(ManagmentUserGeneratorTest.class);
        Result premiumUserGenertator_result = JUnitCore.runClasses(PremiumUserGenertatorTest.class);
        Result simpleUserGenerator_result = JUnitCore.runClasses(SimpleUserGeneratorTest.class);

        /** DomainLayer.Users **/
        Result administrator_result = JUnitCore.runClasses(AdministratorTest.class);
        Result AssociationRepresentative_result = JUnitCore.runClasses(AssociationRepresentativeTest.class);
        Result coachPersonalPage_result = JUnitCore.runClasses(CoachPersonalPageTest.class);
        Result coach_result = JUnitCore.runClasses(CoachTest.class);
        Result fan_result = JUnitCore.runClasses(FanTest.class);
        Result manager_result = JUnitCore.runClasses(ManagerTest.class);
        Result personalPage_result = JUnitCore.runClasses(PersonalPageTest.class);
        Result player_result = JUnitCore.runClasses(PlayerTest.class);
        Result referee_result = JUnitCore.runClasses(RefereeTest.class);
        Result teamOwner_result = JUnitCore.runClasses(TeamOwnerTest.class);
        Result user_result = JUnitCore.runClasses(UserTest.class);

        /** ----------------- TESTS ----------------- **/

        /** DomainLayer.Games **/
        test(closeGame_result);
        test(dayToGame_result);
        test(endGame_result);
        test(startGame_result);

        /** LeagueSeasonsManagement **/
        test(goalScorePolicy_result);
        test(oneRoundGamePolicy_result);
        test(randomTwoRoundsGamePolicy_result);
        test(regularScorePolicy_result);
        test(seasonScoreBoard_result);
        test(season_result);
        test(twoRoundsGamePolicy_result);

        /** MainApp **/
        test(app_result);

        /** DomainLayer.SystemLogic **/
        test(DB_result);
        test(mainSystem_result);
        test(notification_result);
        test(search_result);

        /** DomainLayer.Teams **/
        test(stadium_result);
        test(statistics_result);
        test(teamPage_result);
        test(team_result);

        /** DomainLayer.UserGenerator **/
        test(managmentUserGenerator_result);
        test(premiumUserGenertator_result);
        test(simpleUserGenerator_result);

        /** DomainLayer.Users **/
        test(administrator_result);
        test(AssociationRepresentative_result);
        test(coachPersonalPage_result);
        test(coach_result);
        test(fan_result);
        test(personalPage_result);
        test(player_result);
        test(manager_result);
        test(referee_result);
        test(teamOwner_result);
        test(user_result);
    }

    private static void test (Result result){
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}