package test.java.pumba.board.cells.walkable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.pumba.board.cells.Cell;
import main.java.pumba.board.cells.walkable.impl.CommonCellImpl;
import main.java.pumba.board.cells.walkable.impl.coins.LoseCoinsCellImpl;
import main.java.pumba.board.cells.walkable.impl.coins.WinCoinsCellImpl;
import test.java.pumba.board.cells.PositionFixture;

public class WalkableCellImplTest
{
	@Test
	public void winCoinsCellTest()
	{
		Cell nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertNotNull(nWCell);
		assertNotNull(nWCell.getEffect());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		assertEquals(PositionFixture.withDefaults(), nWCell.getPosition());
		assertTrue(nWCell.getWalkable());
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
		nWCell = new WinCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() > 0);
	}
	
	@Test
	public void loseCoinsCellTest()
	{
		Cell nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertNotNull(nWCell);
		assertNotNull(nWCell.getEffect());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		assertEquals(PositionFixture.withDefaults(), nWCell.getPosition());
		assertTrue(nWCell.getWalkable());
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
		nWCell = new LoseCoinsCellImpl(PositionFixture.withDefaults());
		assertTrue(nWCell.getEffect().getCoins() < 0);
	}
	
	@Test
	public void commonCellTest()
	{
		Cell nWCell = new CommonCellImpl(PositionFixture.withDefaults());
		assertNotNull(nWCell);
		assertNull(nWCell.getEffect());
		assertEquals(PositionFixture.withDefaults(), nWCell.getPosition());
		assertTrue(nWCell.getWalkable());
	}

}
