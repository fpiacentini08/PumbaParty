package test.java.pumba.board.cells;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.pumba.board.cells.Position;

public class PositionTest
{

	@Test
	public void positionConstructorTest() {
		Position pos = PositionFixture.withDefaults();
		assertNotNull(pos);
		assertNotNull(pos.getPosX());
		assertNotNull(pos.getPosY());
		assertTrue(pos.getPosX() >=0);
		assertTrue(pos.getPosY() >=0);

		assertEquals(PositionFixture.posX, pos.getPosX());
		assertEquals(PositionFixture.posY, pos.getPosY());
	}
	
	@Test
	public void positionGettersAndSettersTest()
	{
		Position pos = new Position();
		pos.setPosX(PositionFixture.posX);
		pos.setPosY(PositionFixture.posY);
		assertTrue(pos.getPosX() >=0);
		assertTrue(pos.getPosY() >=0);
		assertNotNull(pos);
		assertEquals(PositionFixture.posX, pos.getPosX());
		assertEquals(PositionFixture.posY, pos.getPosY());
	}
	
}
