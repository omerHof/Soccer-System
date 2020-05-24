package SystemLogic;

public class TaxSystemProxy implements IExternalSystem, ITaxSystem {
    TaxSystem taxSystem = new TaxSystem();

    @Override
    public boolean connectToSystem() {
        return taxSystem.connectToSystem();
    }

    @Override
    public void SetInformation() {

    }

    @Override
    public void getInformation() {

    }

    @Override
    public double getTaxRate(double revenueAmount) {
        return 0;
    }
}
