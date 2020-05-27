package DomainLayer.UserGenerator;

import DomainLayer.Users.User;

import java.time.LocalDate;

public interface IUserGenerator {

    /**
     * constructor - with all kind of user' possible attributes:
     *
     * @param userName
     * @param password
     * @param managementPassword
     * @param role
     * @param fullName
     * @param userEmail
     * @param birthDate
     * @param qualification
     * @param courtRole
     * @param teamRole
     **/
     public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole);
}
