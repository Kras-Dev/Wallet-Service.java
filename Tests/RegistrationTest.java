package Tests;


import Model.Player;
import Service.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RegistrationTest {
    private PlayerService playerService;
    @Before
    public void setUp() {
        playerService = new PlayerService();
    }
    @Test
    public void testSuccessfulPlayerRegistration() {
        String username = "Bob";
        String password = "pass";
        try {
            playerService.registerPlayer(username, password);
        } catch (Exception e) {
            Assert.fail("Unexpected Exception: " + e.getMessage());
        }
        List<Player> players = playerService.getPlayers();
        boolean playerRegistered = false;
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                playerRegistered = true;
                break;
            }
        }
        Assert.assertTrue(playerRegistered); // Проверяем, что игрок успешно зарегистрирован
    }

    @Test
    public void testUnsuccessfulPlayerRegistration() {
        String username = "";
        String password = "";
        try {
            playerService.registerPlayer(username, password);
            Assert.fail("Expected an Exception to be thrown"); // Ожидаем исключение
        } catch (Exception e) {
            // Проверяем, что исключение "Поле не может быть пустым" было брошено
            Assert.assertEquals("Поле не может быть пустым", e.getMessage());
        }
        List<Player> players = playerService.getPlayers();
        boolean playerRegistered = false;

        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                playerRegistered = true;
                break;
            }
        }
        Assert.assertFalse(playerRegistered); // Проверяем, что игрок не зарегистрирован
    }

    @Test
    public void testPlayerAlreadyExists() {
        PlayerService playerService = new PlayerService();
        String username = "Bob";
        String password = "pass";
        try {
            playerService.registerPlayer(username, password);
        } catch (Exception e) {
            Assert.fail("Unexpected Exception: " + e.getMessage());
        }
        try {
            playerService.registerPlayer(username, password);
            Assert.fail("Expected an Exception to be thrown"); // Ожидаем исключение
        } catch (Exception e) {
            // Проверяем, что исключение "Игрок уже существует" было брошено
            Assert.assertEquals("Игрок уже существует", e.getMessage());
        }

        List<Player> players = playerService.getPlayers();
        int playerCount = 0;

        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                playerCount++;
            }
        }
        Assert.assertEquals(1, playerCount); // Проверяем, что количество игроков с дублирующим именем равно 1
    }

}
