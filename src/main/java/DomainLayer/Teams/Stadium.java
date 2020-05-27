package DomainLayer.Teams;

public class Stadium implements Assent {
    private String name;
    private double worth;
    private int capacity;
    private double price;

    public Stadium() {
        this.name = "stadium";
        this.worth = 10000;
        this.capacity = 50000;
        this.price = 50;
    }

    public Stadium(double worth, int capacity, double price) {
        this.worth = worth;
        this.capacity = capacity;
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }
}
