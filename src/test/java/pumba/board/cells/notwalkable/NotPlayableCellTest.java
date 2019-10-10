package pumba.board.cells.notwalkable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pumba.board.cells.Cell;
import pumba.board.cells.PositionFixture;
import pumba.board.cells.notwalkable.impl.NotPlayableCellImpl;

public class NotPlayableCellTest
{
	@Test
	public void notPlayableCellTest()
	{
		Cell nWCell = new NotPlayableCellImpl(PositionFixture.withDefaults());
		assertNotNull(nWCell);
		assertNull(nWCell.getEffect());
		assertEquals(PositionFixture.withDefaults(), nWCell.getPosition());
		assertFalse(nWCell.getWalkable());
	}
}
