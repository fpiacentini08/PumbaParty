package main.java.pumba.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private Map<Position, List<Position>> adjacenceMap;

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
		this.adjacenceMap = createAdjacentMap();
	}

	public void setTestBoard()
	{
		List<Cell> cellsList = new ArrayList<>();
		for (int x = 0; x < dimension; x++)
		{
			for (int y = 0; y < dimension; y++)
			{
				cellsList.add(new CommonCellImpl(new Position(x, y)));
			}
		}
		this.cells = cellsList;
		this.adjacenceMap = createAdjacentMap();
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

	protected Map<Position, List<Position>> createAdjacentMap()
	{
		List<Cell> walkableCells = cells.stream().filter(cell -> cell.getWalkable()).collect(Collectors.toList());
		List<Position> positions = new ArrayList<>();
		for (Cell cell : walkableCells)
		{
			positions.add(cell.getPosition());
		}
		adjacenceMap = new HashMap<Position, List<Position>>();
		for (Position referencePosition : positions)
		{
			List<Position> adjacentPositions = new ArrayList<>();
			for (Position position : positions)
			{
				if (adjacentPositions(referencePosition, position))
				{
					adjacentPositions.add(position);
				}
			}
			adjacenceMap.put(referencePosition, adjacentPositions);

		}
		return adjacenceMap;
	}

	
	
	public Map<Position, List<Position>> getAdjacenceMap()
	{
		return adjacenceMap;
	}

	public static Boolean adjacentPositions(Position pos1, Position pos2)
	{

		if (pos1.getPosX().equals(pos2.getPosX()) && Math.abs(pos1.getPosY() - pos2.getPosY()) == 1)
		{
			return true;
		}

		if (pos1.getPosY().equals(pos2.getPosY()) && Math.abs(pos1.getPosX() - pos2.getPosX()) == 1)
		{
			return true;
		}

		return false;
	}
}
