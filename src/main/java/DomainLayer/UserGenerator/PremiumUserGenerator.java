package DomainLayer.UserGenerator;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Users.*;

import java.time.LocalDate;

public class PremiumUserGenerator implements IUserGenerator {

    @Override
    /**
     * constructor - with all the users' relevant details:
     *
     * @param userName
     * @param password
     * @param managementPassword
     * @param role - manager/coach/player/teamOwner/referee
     * @param fullName
     * @param userEmail
     * @param birthDate
     * @param qualification
     * @param courtRole
     * @param teamRole
     **/
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate  birthDate, String qualification, String courtRole, String teamRole) {

        boolean approved = askForApproval(fullName, role); //from an association representative.

        if(approved) {
            User newUser = whichUserAmI(userName, password, managementPassword, role, fullName, userEmail, birthDate, qualification, courtRole, teamRole); //checks what kind of premium user needs to be generated.
            return newUser;
        }
        else
            return null;

    }

    /**
     * this method checks what kind of Premium User it is, and creates the correct one.
     *
     * @param userName
     * @param password
     * @param managementPassword
     * @param role - manager/coach/player/teamOwner/referee
     * @param fullName
     * @param userEmail
     * @param birthDate
     * @param qualification
     * @param courtRole
     * @param teamRole
     * @return User object
     */
    public User whichUserAmI(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole) {

        if (role != null) {
            switch (role.toLowerCase()) {

                case ("player"): {//Register a player
                    User newPlayer = new Player(userName, password, fullName, userEmail, birthDate, courtRole);
                    MainSystem.LOG.info("A new player: " + userName + " was created successfully !");
                    return newPlayer;
                }

                case ("coach"): {//Register a coach
                    User newCoach = new Coach(userName, password, fullName, userEmail, teamRole);
                    MainSystem.LOG.info("A new coach: " + userName + " was created successfully !");
                    return newCoach;
                }

                case ("manager"): { //Register a manager
                    User newManager = new Manager(userName, password, fullName,userEmail);
                    MainSystem.LOG.info("A new team manager: " + userName + " was created successfully !");
                    return newManager;
                }

                case ("teamowner"): { //Register a teamOwner
                    User newTeamOwner = new TeamOwner(userName, password, fullName,userEmail);
                    MainSystem.LOG.info("A new team owner: " + userName + " was created successfully !");
                    return newTeamOwner;
                }

                case ("referee"): { //Register a referee
                    User newReferee = new Referee(userName, password, fullName,userEmail,qualification);
                    if(!managementPassword.equals("onlyChangeStatus")) //association representative changed a fan's status. no need to write this in LOG.
                        MainSystem.LOG.info("A new referee: " + userName + " was created successfully !");
                    return newReferee;
                }

                default: {  //not legal.
                    System.out.println("unsupported role input"); //// error alert ?/...
                    return null;
                }
            }
        }
        else
            return null;

    }
    /**
     * this function randomly chose an AssociationRepresentative and ask for an approval from him.
     * @return - true if approved, false if not.
     */
    public boolean askForApproval (String fullName, String role) {

        DBLocal dbLocal1 = DBLocal.getInstance();
        AssociationRepresentative ar = (AssociationRepresentative) dbLocal1.getUserType("AssociationRepresentative"); // a random one.
        boolean isApproved = ar.approveRegistration(fullName, role);

        return isApproved;
    }

}
