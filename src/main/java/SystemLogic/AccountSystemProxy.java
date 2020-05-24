package SystemLogic;

public class AccountSystemProxy implements IExternalSystem, IAccountSystem {
    AccountSystem accountSystem= new AccountSystem();

    @Override
    public boolean connectToSystem() {
        return accountSystem.connectToSystem();
    }

    @Override
    public void SetInformation() {

    }

    @Override
    public void getInformation() {

    }

    @Override
    public boolean addPayment(String teamName, String date, double amount) {
        return false;
    }
}
