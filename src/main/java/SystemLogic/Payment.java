package SystemLogic;

public class Payment {
    private String date;
    private Double amount;
    private String teamName;

    public Payment(String teamName, String date, double amount){
        this.amount = amount;
        this.teamName = teamName;
        this.date = date;
    }
}
