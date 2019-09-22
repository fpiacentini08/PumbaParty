package test.java.pumba.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import main.java.pumba.board.Board;
import main.java.pumba.board.cells.Cell;
import main.java.pumba.effects.Effect;

public class BoardTest
{

	Board board = new Board();

	@Test
	public void boardConstructorTest()
	{
		assertNotNull(Board.getDimension());
		assertTrue(Board.getDimension() > 0);
		assertNotNull(board.getCells());
		assertEquals(Board.getDimension() * Board.getDimension(), board.getCells().size());
	}

	@Test
	public void boardGetCellEffectTest()
	{
		for (int i = 0; i < Board.getDimension() * Board.getDimension(); i++)
		{
			Cell testCell = board.getCells().get(i);
			assertNotNull(testCell);
			assertNotNull(testCell.getPosition());
			Effect effect = board.getCellEffect(testCell.getPosition());
			assertEquals(testCell.getEffect(), effect);
		}

	}
}
