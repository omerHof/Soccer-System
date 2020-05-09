package SystemLogic;

public class AccountSystemProxy implements IExternalSystem {
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
}
