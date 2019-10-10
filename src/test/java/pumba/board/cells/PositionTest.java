package pumba.board.cells;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	
	@Test
	public void positionEquals()
	{
		Position pos1 = new Position(0, 15);
		Position pos2 = new Position(6, 3);
		Position pos3 = new Position(6, 3);
		Position pos4 = new Position(6, 4);
		Position pos5 = new Position(0, 4);
		assertFalse(pos1.equals(pos2));
		assertFalse(pos2.equals(pos4));
		assertFalse(pos3.equals(pos5));
		assertFalse(pos4.equals(pos5));
		assertTrue(pos2.equals(pos3));
	}
	
}
