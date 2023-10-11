package Application;

import Model.AuditLog;
import Model.Player;
import Model.Transaction;
import Service.PlayerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 * class Commands - класс обработки команд
 */
public class Commands {
    private PlayerService playerService = new PlayerService();

    private static Scanner scanner = new Scanner(System.in);
    public  void registerPlayer()  {
        System.out.println("Введите имя: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        try {
            playerService.registerPlayer(username, password);
            System.out.println("Игрок зарегистрирован успешно");
        } catch (Exception e){
            System.out.println("Регистрация отклонена: " + e.getMessage());
        }
    }

    public Player login() {
        System.out.println("Введите имя: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        Player player = playerService.authenticatePlayer(username, password);
        if (player != null) {
            System.out.println("Вход выполнен успешно");
        } else {
            System.out.println("Неверное имя или пароль");
        }
        return player;
    }
    public void getPlayerBalance(Player player) {
        double balance = playerService.getPlayerBalance(player);
        System.out.println("Баланс: " + balance);
    }

    public void debitPlayer(Player player) {
        System.out.println("Введите сумму: ");
        double amount = Double.parseDouble(scanner.nextLine());
        try {
            playerService.debitPlayer(player,amount );
            System.out.println("Операция подтверждена");
        } catch (Exception e) {
            System.out.println("Операция отклонена: " + e.getMessage());
        }
    }
    public void creditPlayer(Player player) {
        System.out.println("Введите сумму: ");
        double amount = Double.parseDouble(scanner.nextLine());
        try {
            playerService.creditPlayer(player,amount );
            System.out.println("Операция подтверждена");
        } catch (Exception e) {
            System.out.println("Операция отклонена: " + e.getMessage());
        }
    }
    public void getPlayerTransactionHistory(Player player) {
        List<Transaction> transactionHistory = playerService.getPlayerTransactionHistory(player);
        if (transactionHistory.isEmpty()) {
            System.out.println("История операйций не найдена");
        } else {
            System.out.println("История операций: ");
            for (Transaction log : transactionHistory) {
                System.out.println(log.getActionDate() + " " + log.getId() + " " + log.getAmount() + " "+ log.getType());
            }
        }
    }
    public void getPlayerLogHistory(Player player) {
        List<AuditLog> logHistory = playerService.getPlayerLogHistory(player);
        if (logHistory.isEmpty()) {
            System.out.println("История действий не найдена");
        } else {
            System.out.println("История действий: ");
            for (AuditLog log : logHistory) {
                System.out.println(log.getActionDate() + " " + log.getActionType());
            }
        }
    }
    public void logOut(Player player){
        playerService.logOut(player);
    }

}
