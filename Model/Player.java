package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * class Player - объект игрока
 */
public class Player {
    private String username;
    private String userId;
    private String password;
    private double balance;
    private List<Transaction> transactionHistory;
    private List<AuditLog> logHistory;

    public Player(String username, String password) {
        this.username = username;
        this.userId = UUID.randomUUID().toString();
        this.password = password;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
        this.logHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public List<AuditLog> getLogHistory() {
        return logHistory;
    }

    public void setLogHistory(List<AuditLog> logHistory) {
        this.logHistory = logHistory;
    }
    public void addLog(AuditLog auditLog){
        logHistory.add(auditLog);
    }
    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public void updateBalance(double amount) {
        balance += amount;
    }
}