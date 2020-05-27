package DomainLayer.UserGenerator;

import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Users.Administrator;
import DomainLayer.Users.AssociationRepresentative;
import DomainLayer.Users.User;

import java.time.LocalDate;

public class ManagementUserGenerator implements IUserGenerator {

    private static String represantativePassword;
    private static String systemManagerPassword;

    @Override
    /**
     * constructor - with all the users' relevant details:
     * (most of the inputs are not relevant for this kind of user generator)
     *
     * @param userName
     * @param password
     * @param managementPassword
     * @param role - AssociationRepresentative/Administrator only.
     * @param fullName
     * @param userEmail
     * @param birthDate
     * @param qualification
     * @param courtRole
     * @param teamRole
     **/
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole) {

        MainSystem mainSystem = MainSystem.getInstance();
        String pass = mainSystem.getSpecialPassword();
        boolean approved = managementPassword.equals(mainSystem.getSpecialPassword()); //

        if (approved) {
            if (role.equals("AssociationRepresentative"))
                return new AssociationRepresentative(userName, password, fullName, userEmail);
            else if (role.equals("Administrator"))
                return new Administrator(userName, password, fullName, userEmail);
            else
                return null;
        }
        else
            return null;
    }


    public boolean askForApproval(String fullName, String password) { //checks whether the special password is valid or not.
        return true;
    }
}