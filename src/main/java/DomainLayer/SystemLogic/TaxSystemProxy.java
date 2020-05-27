package DomainLayer.SystemLogic;

public class TaxSystemProxy implements IExternalSystem, ITaxSystem {
    TaxSystem taxSystem = new TaxSystem();

    @Override
    public boolean connectToSystem() {
        return taxSystem.connectToSystem();
    }
    @Override
    public double getTaxRate(double revenueAmount) {
       return taxSystem.getTaxRate(revenueAmount);
    }
}
