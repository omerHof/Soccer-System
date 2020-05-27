package DomainLayer.SystemLogic;

public class TaxSystem {
    public boolean connectToSystem() {
        System.out.println("Tax System is Connected");//todo: until GUI
        return true;
    }

    public double getTaxRate(double revenueAmount) {
        double taxRate=0;
        if(revenueAmount<=6330)
            taxRate=0.1;
        else if(revenueAmount>6330&& revenueAmount<=9080)
            taxRate=0.14;
        else  if(revenueAmount>9080&& revenueAmount<=14580)
            taxRate=0.2;
        else if(revenueAmount>14580&& revenueAmount<=20260)
            taxRate=0.31;
        else if(revenueAmount>20260&& revenueAmount<=42610)
            taxRate=0.35;
        else if(revenueAmount>42160&& revenueAmount<=54300)
            taxRate=0.47;
        else if(revenueAmount>54300)
            taxRate=0.5;

        return taxRate;
    }
}
