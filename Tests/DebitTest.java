package Tests;

import Model.Player;
import Service.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DebitTest {
    private PlayerService playerService;
    private Player player;
    @Before
    public void setUp() {
        playerService = new PlayerService();
        player = new Player("username1", "password1");
        player.setBalance(100.0);
        playerService.addPlayer(player);
    }

    @Test
    public void testValidAmount() throws Exception {
        double amount = 51.0;
        playerService.debitPlayer(player,amount);
        double expectedBalance = 49.0;
        double actualBalance = player.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance,0.01);
    }

    @Test
    public void testZeroAmount() throws Exception{
        double amount = 0.0;
        try {
            playerService.debitPlayer(player, amount);
        } catch (Exception e) {
            Assert.assertEquals("Сумма снятия должна быть больше 0", e.getMessage());
        }
    }
    @Test
    public void testNegativeAmount() throws Exception{
        double amount = -10.0;
        try {
            playerService.debitPlayer(player, amount);
        } catch (Exception e) {
            Assert.assertEquals("Сумма снятия должна быть больше 0", e.getMessage());
        }
    }
    @Test
    public void testInsufficientBalance() throws Exception{
        double amount = 150.0;
        try {
            playerService.debitPlayer(player, amount);
        } catch (Exception e) {
            Assert.assertEquals("Недостаточный баланс", e.getMessage());
        }
    }
}
