package test.java.pumba.board.cells;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		assertEquals(PositionFixture.posX, pos.getPosX());
		assertEquals(PositionFixture.posY, pos.getPosY());
	}
	
	@Test
	public void positionGettersAndSettersTest()
	{
		Position pos = new Position();
		pos.setPosX(PositionFixture.posX);
		pos.setPosY(PositionFixture.posY);
		assertNotNull(pos);
		assertEquals(PositionFixture.posX, pos.getPosX());
		assertEquals(PositionFixture.posY, pos.getPosY());

	}
}
