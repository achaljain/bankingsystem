package bank;

public class Transaction{
    private int id;
    private String date;
    private String type;
    private String dst;
    private double amount;
    private double balance;

    public Transaction() {
        this.id = 0;
        this.date = "NA";
        this.type = "NA";
        this.dst = "NA";
        this.amount = 0;
        this.balance = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
