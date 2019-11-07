package pumba.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import pumba.board.cells.Cell;
import pumba.board.cells.Position;
import pumba.effects.Effect;

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

	private void boardTestMoveTest(Position initialPosition, Integer steps, Position... positions)
	{
		Board board = new Board();
		board.setTestBoard();

		List<Position> possiblePositions = board.getPossiblePositions(initialPosition, steps);

		for (int i = 0; i < positions.length; i++)
		{
			assertTrue(possiblePositions.contains(positions[i]));

		}

	}

//	@Test
//	public void boardTestMoveTestPrueba()
//	{
//		Position initialPosition = new Position(3, 18);
//		Integer steps = 2;
//		Board board = new Board();
//
//		List<Position> possiblePositions = board.getPossiblePositions(initialPosition, steps);
//
//		assertNotNull(possiblePositions);
//
//	}
	
	@Test
	public void boardTestMove01Test()
	{
		Position pos = new Position(0, 0);
		Position posExpected1 = new Position(0, 1);
		Position posExpected2 = new Position(1, 0);
		boardTestMoveTest(pos, 1, posExpected1, posExpected2);

	}

	@Test
	public void boardTestMove02Test()
	{
		Position pos = new Position(0, 0);
		Position posExpected1 = new Position(0, 2);
		Position posExpected2 = new Position(2, 0);
		Position posExpected3 = new Position(1, 1);
		boardTestMoveTest(pos, 2, posExpected1, posExpected2, posExpected3);

	}

	@Test
	public void boardTestMove03Test()
	{
		Position pos = new Position(0, 0);
		Position posExpected1 = new Position(0, 4);
		Position posExpected2 = new Position(4, 0);
		Position posExpected3 = new Position(1, 3);
		Position posExpected4 = new Position(3, 1);
		Position posExpected5 = new Position(2, 2);
		Position posExpected6 = new Position(1, 1);
		Position posExpected7 = new Position(2, 0);
		Position posExpected8 = new Position(0, 2);
		boardTestMoveTest(pos, 4, posExpected1, posExpected2, posExpected3, posExpected4, posExpected5, posExpected6,
				posExpected7, posExpected8);

	}

	@Test
	public void boardTestMove04Test()
	{
		Position pos = new Position(0, 0);
		Position posExpected1 = new Position(0, 6);
		Position posExpected2 = new Position(6, 0);
		Position posExpected3 = new Position(5, 1);
		Position posExpected4 = new Position(1, 5);
		Position posExpected5 = new Position(2, 4);
		Position posExpected6 = new Position(4, 2);
		Position posExpected7 = new Position(3, 3);
		Position posExpected8 = new Position(0, 4);
		Position posExpected9 = new Position(4, 0);
		Position posExpected10 = new Position(1, 3);
		Position posExpected11 = new Position(3, 1);
		Position posExpected12 = new Position(2, 2);
		boardTestMoveTest(pos, 6, posExpected1, posExpected2, posExpected3, posExpected4, posExpected5, posExpected6,
				posExpected7, posExpected8, posExpected9, posExpected10, posExpected11, posExpected12);

	}

}
