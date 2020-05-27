package DomainLayer.SystemLogic;

public class Payment {
    private String date;
    private Double amount;
    private String teamName;

    public Payment(String teamName, String date, double amount){
        this.amount = amount;
        this.teamName = teamName;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
