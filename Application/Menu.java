package Application;

import Model.Player;
import Service.PlayerService;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * class Menu - класс ввода команд
 */
public class Menu {
    private PlayerService playerService = new PlayerService();
    private Commands commands = new Commands();
    private static Scanner scanner = new Scanner(System.in);

    Player player = null;
    public void onStart(){
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
            System.out.print("Введите команду:");
            if (scanner.hasNextInt()) {
                int command = scanner.nextInt();

                switch (command) {
                    case 1:
                        commands.registerPlayer();
                        break;
                    case 2:
                        player = commands.login();
                        if (player != null) {
                            onLogIn();
                        }
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Неверная команда");
                        break;
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }
    }
    public void onLogIn(){
        boolean isLoggedIn = true;
        while (isLoggedIn) {
            System.out.println("1. Баланс");
            System.out.println("2. Снятие");
            System.out.println("3. Пополнение");
            System.out.println("4. История операций");
            System.out.println("5. История действий");
            System.out.println("6. Выход");
            System.out.print("Введите команду:");
            if (scanner.hasNextInt()) {
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        commands.getPlayerBalance(player);
                        break;
                    case 2:
                        commands.debitPlayer(player);
                        break;
                    case 3:
                        commands.creditPlayer(player);
                        break;
                    case 4:
                        commands.getPlayerTransactionHistory(player);
                        break;
                    case 5:
                        commands.getPlayerLogHistory(player);
                        break;
                    case 6:
                        commands.logOut(player);
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Неверная команда");
                        break;
                }

            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }
    }
}
