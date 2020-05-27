package DomainLayer.SystemLogic;

import ServiceLayer.IO.LoggingController;
import DomainLayer.UserGenerator.IUserGenerator;
import DomainLayer.UserGenerator.ManagementUserGenerator;
import DomainLayer.Users.Administrator;
import DomainLayer.Users.User;
import org.slf4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainSystem {
    private static MainSystem single_instance = null;
    private AccountSystemProxy accountSystemProxy;
    private TaxSystemProxy taxSystemProxy;
    private User currentUser = null;
    public static Logger LOG = LoggingController.getLogger();


    private DBLocal dbLocal = DBLocal.getInstance();
    private TimerPasswordBuilder timerPasswordBuilder;


    /**
     * An empty constructor
     */
    private MainSystem() {
        timerPasswordBuilder = new TimerPasswordBuilder();
        Timer t = new Timer();
        t.scheduleAtFixedRate(timerPasswordBuilder, 0, TimeUnit.DAYS.toMillis(1));
        //t.scheduleAtFixedRate(timerPasswordBuilder, 0, 2000);
    }

    /**
     * A Singleton method
     */
    public static MainSystem getInstance()
    {
        if (single_instance == null) {
            synchronized (MainSystem.class) {
                if (single_instance == null) {
                    single_instance = new MainSystem();
                }
            }
        }
        return single_instance;
    }

    /**
     * Use Case 1.1: This method initializes the System:
     * Connect to the logger, connect external systems, appoint administrator, start the password builder
     */
    public void initializeSystem(){
        connectToLog();
        connectExternalSystems();
        User user = dbLocal.getUserType("Administrator");
        if (user==null){
            appointUserToSAdministrator();
        }

    }

    /**
     * This method initialize the logger
     */
    private void connectToLog(){
        LOG.info("LOG FILE IS CONNECTED!");

    }

    /**
     * This method initialize the external system and connect this class to them.
     */
    private void connectExternalSystems(){
        accountSystemProxy = new AccountSystemProxy();
        if(accountSystemProxy.connectToSystem()){
            LOG.info("Account System is Connected");
        }
        else{
            LOG.info("Account System Connection failed");
        }
        taxSystemProxy = new TaxSystemProxy();
        if(taxSystemProxy.connectToSystem()){
            LOG.info("Tax System is Connected");
        }
        else{
            LOG.info("Tax System Connection failed");
        }
    }

    /**
     * This method gets user from the data base and appoints him to administrator.
     */
    private void appointUserToSAdministrator() {
        ManagementUserGenerator managementUserGenerator = new ManagementUserGenerator();
        String special_password = timerPasswordBuilder.getPassword();
        Administrator administrator = (Administrator) managementUserGenerator.generate("admin","admin01",special_password
                ,"Administrator", "admin admin", "admin@gmail.com",
                null,"","","");
        dbLocal.addUser(administrator);
        LOG.info("Administrator was appointed successfully");
    }

    public String getUserType(String userName){
        User user = dbLocal.getUser(userName);
        return user.getClass().getSimpleName();

    }


    /**
     * This method is for user's signing up the system
     * @param userName
     * @param password
     * @param mangerPassword
     * @param role
     * @param fullName
     * @param userEmail
     * @param birthDate
     * @param qualification
     * @param courtRole
     * @param teamRole
     * @param iUserGenerator
     * @return boolean answer - did the signing up work or not
     */
    public String singUp(String userName, String password, String mangerPassword, String role, String fullName, String userEmail,
                         LocalDate birthDate, String qualification, String courtRole, String teamRole,
                         IUserGenerator iUserGenerator){
        if (dbLocal.userExist(userName)){
            return "exist";
        }

        User newUser =  iUserGenerator.generate(userName, encrypte(password), mangerPassword, role, fullName, userEmail,
                birthDate, qualification, courtRole, teamRole);
        if(newUser==null){
            return "association representative";
        }

        dbLocal.addUser(newUser);
        LOG.info("A new user " + userName + " was signed up successfully");
        if(this.currentUser==null){
            this.currentUser = newUser;
            LOG.info(userName + " was logged in successfully");
            return "signed up and logged in";
        }

        return "signed up only";
    }

    public String encrypte(String password) {
        byte[] hash=null;
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            for(byte b:salt){
                b=8;
            }
            //random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        }catch (Exception e){

        }
        return new String(hash);

    }

    /**
     * This method is for user's logging in the system.
     * Checks his user name and his password in the data base
     * @param userName
     * @param password
     * @return String - did the logging in work or not and why
     */
    public String logIn(String userName, String password){
        if(this.currentUser!=null){
            return "occupied";
        }
        if(!dbLocal.userExist(userName)){
            return "name";
        }
        else if(dbLocal.getUser(userName)==null){
            return "null";
        }
        else if(!dbLocal.getUser(userName).getPassword().equals(encrypte(password))){
            return "password";
        }
        this.currentUser = dbLocal.getUser(userName);
        if (currentUser.isNonReadNotifications()){
            showNotification();
        }
        LOG.info(userName + " was logged in successfully");
        return "logged in";
    }

    /**
     * This method alert the user that he has non read notifications;
     */
    private void showNotification() {

    }

    /**
     * This method is for user's logging out the system.
     * @return boolean answer - did the logging out work or not
     */
    public boolean logOut(){
        if(currentUser!=null) {
            String userName = currentUser.getUserName();
            this.currentUser = null;
            LOG.info(userName + " was logged out successfully");
        }
        return true;
    }

    /**
     *
     * @return String special password
     */
    public String getSpecialPassword(){
        return timerPasswordBuilder.getPassword();
    }

    /** GETTERS & SETTERS **/

    public AccountSystemProxy getAccountSystemProxy() {
        return accountSystemProxy;
    }

    public void setAccountSystemProxy(AccountSystemProxy accountSystemProxy) {
        this.accountSystemProxy = accountSystemProxy;
    }

    public TaxSystemProxy getTaxSystemProxy() {
        return taxSystemProxy;
    }

    public void setTaxSystemProxy(TaxSystemProxy taxSystemProxy) {
        this.taxSystemProxy = taxSystemProxy;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


   /* public ArrayList<String> watchGamesList(){
        ArrayList<Game> games = ((Referee) currentUser).watchGamesList();
        return db.getStringGames(games);
    }*/
}

class TimerPasswordBuilder extends TimerTask {
    private String password;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * This method creates a random password every day
     */
    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        int x =8;
        while (x-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        password =  builder.toString();
        MainSystem.LOG.info("Special password changed at ");
    }




    public String getPassword(){
        return password;
    };
}
