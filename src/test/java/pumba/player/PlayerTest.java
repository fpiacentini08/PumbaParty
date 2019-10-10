package pumba.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pumba.board.cells.Position;
import pumba.effects.Effect;
import pumba.players.Player;

public class PlayerTest
{

	@Test
	public void playerConstructorTest()
	{
		Player player = PlayerFixture.withDefaults();
		assertNotNull(player);

		assertNotNull(player.getUsername());

		assertNotNull(player.getCoins());
		assertEquals(0, player.getCoins(), 0);

		assertNotNull(player.getPosition());
		assertNotNull(player.getPosition().getPosX());
		assertNotNull(player.getPosition().getPosY());
		assertTrue(player.getPosition().getPosX() >= 0);
		assertTrue(player.getPosition().getPosY() >= 0);
	}

	@Test
	public void playerSettersAndGettersTest()
	{
		Player player = PlayerFixture.withDefaults();

		player.setCoins(1500);
		player.setPosition(new Position(15, 12));

		assertNotNull(player);

		assertNotNull(player.getCoins());
		assertEquals(1500, player.getCoins(), 0);

		assertNotNull(player.getPosition());
		assertNotNull(player.getPosition().getPosX());
		assertNotNull(player.getPosition().getPosY());

		assertEquals(new Position(15, 12), player.getPosition());

	}

	@Test
	public void playerThrowDiceTest()
	{
		Player player = PlayerFixture.withDefaults();

		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		set.add(6);
		for (int i = 0; i < 20; i++)
		{
			assertTrue(set.contains(player.throwDice()));
		}
	}

	@Test
	public void playerApplyEffectWithCoinsTest()
	{
		Player player = PlayerFixture.withDefaults();
		Effect effectPositive = new Effect(15);
		Effect effectNegative = new Effect(-20);
		
		player.applyEffect(effectPositive);
		assertEquals(15, player.getCoins(), 0);

		player.applyEffect(effectNegative);
		assertEquals(0, player.getCoins(), 0);

		player.applyEffect(effectPositive);
		assertEquals(15, player.getCoins(), 0);
		
		player.applyEffect(effectPositive);
		assertEquals(30, player.getCoins(), 0);
		
		player.applyEffect(effectPositive);
		assertEquals(45, player.getCoins(), 0);

		player.applyEffect(effectNegative);
		assertEquals(25, player.getCoins(), 0);
		
		player.applyEffect(effectNegative);
		assertEquals(5, player.getCoins(), 0);
		
		player.applyEffect(effectNegative);
		assertEquals(0, player.getCoins(), 0);
		
		player.applyEffect(effectNegative);
		assertEquals(0, player.getCoins(), 0);
		
		player.applyEffect(effectNegative);
		assertEquals(0, player.getCoins(), 0);
		
		player.applyEffect(effectNegative);
		assertEquals(0, player.getCoins(), 0);
		
		player.applyEffect(effectNegative);
		assertEquals(0, player.getCoins(), 0);
	}
}
