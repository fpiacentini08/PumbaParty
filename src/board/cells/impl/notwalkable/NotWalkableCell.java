package board.cells.impl.notwalkable;

import board.cells.Cell;
import board.cells.Position;

public abstract class NotWalkableCell extends Cell
{

	public NotWalkableCell(Position pos)
	{
		super(pos, null, false);
	}
}
