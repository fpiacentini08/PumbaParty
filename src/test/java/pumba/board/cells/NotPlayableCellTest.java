package test.java.pumba.board.cells;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import main.java.pumba.board.cells.Cell;
import main.java.pumba.board.cells.impl.notwalkable.impl.NotPlayableCellImpl;

public class NotPlayableCellTest
{
	@Test
	public void notWalkableCellTest() {
		Cell nWCell = new NotPlayableCellImpl(PositionFixture.withDefaults());
		assertNotNull(nWCell);
		assertNull(nWCell.getEffect());
		assertEquals(PositionFixture.withDefaults(), nWCell.getPosition());
		assertFalse(nWCell.getWalkable());
	}	
}
