package Service;


import Model.AuditLog;
import Model.Player;
import Model.Transaction;
import Model.TransactionType;

import java.util.*;
/**
 * class PlayerService - класс обработки запросов и логики
 */
public class PlayerService {
    private Map<String, Player> players;

    public PlayerService() {
        this.players = new HashMap<>();
    }

    public void addPlayer(Player player) {
        players.put(player.getUsername(), player);
    }

    public Player getPlayer(String username) {
        return players.get(username);
    }
    public List<Player> getPlayers() {
        List<Player> playerList = new ArrayList<>(players.values());
        return playerList;
    }
    public void registerPlayer(String username, String password) throws Exception {
        if (!isPlayerUnique(username)){
            throw new Exception("Игрок уже существует");
        }
        if (username.isEmpty() || password.isEmpty()) {
            throw new Exception("Поле не может быть пустым");
        }
        if (!players.containsKey(username)) {
            players.put(username, new Player(username, password));
        }
    }
    public Player authenticatePlayer(String username, String password) {
        Player player = players.get(username);
        if (player != null && player.getPassword().equals(password)) {
            player.addLog(new AuditLog(player.getUserId(), AuditLog.ActionType.LOGIN));
            return player;
        }
        return null;
    }
    public double getPlayerBalance(Player player){
        return player.getBalance();
    }

    public void debitPlayer(Player player,double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Сумма снятия должна быть больше 0");
        }
        if (player.getBalance() - amount < 0) {
              throw new Exception("Недостаточный баланс");
        }
        Transaction transaction = new Transaction(amount,TransactionType.DEBIT);
        if (!isTransactionIdUnique(transaction.getId())) {
            throw new Exception("ID Транзакции не уникально");
        }
        player.addTransaction(transaction);
        player.updateBalance(-amount);
        player.addLog(new AuditLog(player.getUserId(), AuditLog.ActionType.WITHDRAWAL));
    }

    public void creditPlayer(Player player,double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Сумма пополнения должна быть больше 0");
        }
        Transaction transaction = new Transaction(amount,TransactionType.CREDIT);
        if (!isTransactionIdUnique(transaction.getId())) {
            throw new Exception("ID Транзакции не уникально");
        }
        player.addTransaction(transaction);
        player.updateBalance(amount);
        player.addLog(new AuditLog(player.getUserId(), AuditLog.ActionType.DEPOSIT));

    }

    public List<Transaction> getPlayerTransactionHistory(Player player) {
        return new ArrayList<>(player.getTransactionHistory());
    }

    private boolean isTransactionIdUnique(String transactionId) {
        for (Player player : players.values()) {
            for (Transaction transaction : player.getTransactionHistory()) {
                if (transaction.getId().equals(transactionId)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPlayerUnique(String username) {
        for (Player player : players.values()) {
            if (player.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
     public List<AuditLog> getPlayerLogHistory(Player player) {
        return new ArrayList<>(player.getLogHistory());
    }
    public void logOut(Player player){
        player.addLog(new AuditLog(player.getUserId(), AuditLog.ActionType.LOGOUT));
    }
}

