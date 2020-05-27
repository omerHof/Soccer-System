package DomainLayer.UserGenerator;

import DomainLayer.Users.User;

import java.time.LocalDate;


/**
 * this class represent a simple user generator - only a fan.
 */
public class SimpleUserGenerator implements IUserGenerator {


    @Override
    /**
     * constructor - with all the user's relevant details:
     *
     * @param userName
     * @param password
     * @param managementPassword - not in use for this user.
     * @param role - not in use for this user.
     * @param fullName
     * @param userEmail
     * @param birthDate - not in use for this user.
     * @param qualification - not in use for this user.
     * @param courtRole - not in use for this user.
     * @param teamRole - not in use for this user.
     **/
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole) {

        User newFan = new DomainLayer.Users.Fan(userName, password, fullName, userEmail); // all the rest are not relevant for this simple user.
        return newFan;
    }
}

