package Model;

import java.util.Date;
import java.util.UUID;
/**
 * class Transaction - объект транзакции
 */
public class Transaction {
    private String id;
    private double amount;
    private TransactionType type;
    private Date actionDate;

    public Transaction(double amount, TransactionType type) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.type = type;
        this.actionDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getActionDate() {
        return actionDate;
    }
}
