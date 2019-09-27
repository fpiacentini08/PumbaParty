package test.java.pumba.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.pumba.game.Game;
import main.java.pumba.players.Player;
import main.java.pumba.rooms.Room;
import test.java.pumba.room.RoomFixture;

public class GameTest
{

	@Test
	public void testConstructor()
	{
		Room room = RoomFixture.withFourUsers();
		Game game = new Game(room.getUsers());
		assertNotNull(game);
		assertNotNull(game.getBoard());
		assertNotNull(game.getMinigames());
		assertNotNull(game.getPlayers());
		assertNotNull(Game.getTurns());
		assertEquals(room.getUsers().size(), game.getPlayers().size(), 0);
		assertTrue(game.getMinigames().size() > 0);

		for (Player player : game.getPlayers())
		{
			assertTrue(player.getCoins().equals(0));
			assertNotNull(player.getPosition());
			assertNotNull(player.getUsername());
		}
	}

	@Test
	public void testPlay()
	{
		Room room = RoomFixture.withFourUsers();
		Game game = new Game(room.getUsers());
		game.playGame();
	}
}
