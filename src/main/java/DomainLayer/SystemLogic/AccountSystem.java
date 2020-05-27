package DomainLayer.SystemLogic;

import java.util.ArrayList;

public class AccountSystem {

    double FinancialBalance;
    ArrayList<Payment> payments;

    public AccountSystem() {
        FinancialBalance = 0;
        payments = new ArrayList<>();
    }

    public boolean connectToSystem() {
        System.out.println("Account System is Connected");//todo: until GUI
        return true;
    }

    public void addMoneyToBalance(Payment payment){
        this.FinancialBalance+=payment.getAmount();
        this.payments.add(payment);
    }




}
