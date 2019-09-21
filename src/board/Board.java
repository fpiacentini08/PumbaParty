package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import board.cells.Cell;
import board.cells.Position;
import board.cells.impl.notwalkable.impl.NotPlayableCellImpl;
import board.cells.impl.walkable.impl.CommonCellImpl;
import board.cells.impl.walkable.impl.LoseCoinsCellImpl;
import board.cells.impl.walkable.impl.WinCoinsCellImpl;

public class Board
{

	private List<Cell> cells;
	private static final Integer dimension = 20;

	public List<Cell> getCells()
	{
		return cells;
	}

	public void setCells(List<Cell> cells)
	{
		this.cells = cells;
	}

	public static Integer getDimension()
	{
		return dimension;
	}

	public Board()
	{
		super();
		List<Cell> cellsList = new ArrayList<>();
		for (int x = 0; x < dimension; x++)
		{
			for (int y = 0; y < dimension; y++)
			{
				Random rand = new Random();
				switch (rand.nextInt(4))
				{
					case 0:
						this.cells.add(new NotPlayableCellImpl(new Position(x, y)));
						break;
					case 1:
						this.cells.add(new CommonCellImpl(new Position(x, y)));
						break;
					case 2:
						this.cells.add(new LoseCoinsCellImpl(new Position(x, y)));
						break;
					case 3:
						this.cells.add(new WinCoinsCellImpl(new Position(x, y)));
						break;
					default:
						this.cells.add(new CommonCellImpl(new Position(x, y)));
						break;
				}
			}
		}
		this.cells = cellsList;
	}

}
