package test.java.pumba.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.java.pumba.board.Board;
import main.java.pumba.board.cells.Cell;
import main.java.pumba.board.cells.Position;
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

	@Test
	public void boardTestConstructorTest()
	{
		Board board = new Board();
		board.setTestBoard();

		for (int i = 0; i < Board.getDimension() * Board.getDimension(); i++)
		{
			Cell testCell = board.getCells().get(i);
			assertNotNull(testCell);
			assertNotNull(testCell.getPosition());
			Effect effect = board.getCellEffect(testCell.getPosition());
			assertEquals(testCell.getEffect(), effect);
		}
	}

	@Test
	public void boardAdjacencePositionsTest()
	{
		Position pos1 = new Position(12, 15);
		Position pos2 = new Position(13, 15);
		Position pos3 = new Position(12, 16);
		Position pos4 = new Position(13, 16);
		Position pos5 = new Position(11, 15);
		Position pos6 = new Position(12, 14);

		assertTrue(Board.adjacentPositions(pos1, pos2));
		assertTrue(Board.adjacentPositions(pos1, pos3));
		assertTrue(Board.adjacentPositions(pos1, pos5));
		assertTrue(Board.adjacentPositions(pos1, pos6));
		assertFalse(Board.adjacentPositions(pos1, pos4));
		assertFalse(Board.adjacentPositions(pos5, pos6));

	}

	@Test
	public void boardAdjacenceMapTest()
	{
		Board board = new Board();
		board.setTestBoard();

		Map<Position, List<Position>> adjacenceMap = board.getAdjacenceMap();
		assertNotNull(adjacenceMap);

		for (Cell cell : board.getCells())
		{
			if (cell.getWalkable())
			{
				assertTrue(adjacenceMap.containsKey(cell.getPosition()));
			}
		}
		
		

	}
}
