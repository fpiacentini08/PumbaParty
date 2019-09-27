package main.java.pumba.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.pumba.board.cells.Cell;
import main.java.pumba.board.cells.Position;
import main.java.pumba.board.cells.notwalkable.impl.NotPlayableCellImpl;
import main.java.pumba.board.cells.walkable.impl.CommonCellImpl;
import main.java.pumba.board.cells.walkable.impl.coins.LoseCoinsCellImpl;
import main.java.pumba.board.cells.walkable.impl.coins.WinCoinsCellImpl;
import main.java.pumba.effects.Effect;

public class Board
{

	private List<Cell> cells;
	private static final Integer dimension = 20;

	public List<Cell> getCells()
	{
		return cells;
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
						cellsList.add(new NotPlayableCellImpl(new Position(x, y)));
						break;
					case 1:
						cellsList.add(new CommonCellImpl(new Position(x, y)));
						break;
					case 2:
						cellsList.add(new LoseCoinsCellImpl(new Position(x, y)));
						break;
					case 3:
						cellsList.add(new WinCoinsCellImpl(new Position(x, y)));
						break;
					default:
						cellsList.add(new CommonCellImpl(new Position(x, y)));
						break;
				}
			}
		}
		this.cells = cellsList;
	}

	public List<Position> move(Position initialPosition, Integer steps)
	{
		// TO DO IMPLEMENT THIS METHOD
		// THERE CAN BE MORE THAN ONE POSSIBLE FINAL POSITION
		List<Position> list = new ArrayList<>();
		list.add(defaultPosition());
		return list;
	}

	public List<Position> move(Position initialPosition, Integer steps, Position finalPosition)
	{
		// IT VERIFIES THAT THE FINAL POSITION SENT IS A POSIBBLE FINAL POSITION
		// IF NOT, RETURNS POSSIBLE POSITIONS
		List<Position> possiblePos = move(initialPosition, steps);
		if (possiblePos.contains(finalPosition))
		{
			possiblePos.clear();
			possiblePos.add(finalPosition);
		}
		return possiblePos;
	}

	public Effect getCellEffect(Position pos)
	{
		Cell cell = cells.stream().filter(cl -> pos.equals(cl.getPosition())).collect(Collectors.toList()).get(0);
		return cell.getEffect();
	}

	public Position defaultPosition()
	{
		return new Position(0, 0);
	}
}
