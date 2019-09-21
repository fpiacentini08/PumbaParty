package board;

import java.util.ArrayList;
import java.util.List;

import board.cells.Cell;
import board.cells.Position;
import board.cells.impl.CellA;
import board.cells.impl.CellB;

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
				if (y % 2 == 1)
				{
					this.cells.add(new CellA(new Position(x, y)));

				}
				else
				{
					this.cells.add(new CellB(new Position(x, y)));

				}
			}
		}
		this.cells = cellsList;
	}

}
