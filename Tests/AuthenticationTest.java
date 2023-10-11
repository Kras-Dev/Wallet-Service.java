package Tests;

import Model.Player;
import Service.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
    private PlayerService playerService;
    @Before
    public void setUp() {
        playerService = new PlayerService();

        Player player1 = new Player("username1", "password1");
        playerService.addPlayer(player1);
    }

    @Test
    public void testSuccessfulPlayerAuthentication(){
        String username = "username1";
        String password = "password1";

        Player authenticatedPlayer = playerService.authenticatePlayer(username,password);
        Assert.assertNotNull(authenticatedPlayer);
        Assert.assertEquals(username, authenticatedPlayer.getUsername());
    }
    @Test
    public void testFailedAuthentication() {

        String username = "username1";
        String incorrectPassword = "incorrect_password";
        Player authenticatedPlayer = playerService.authenticatePlayer(username, incorrectPassword);

        Assert.assertNull(authenticatedPlayer);
    }
}
