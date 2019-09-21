package board.cells.impl.walkable;

import board.cells.Cell;
import board.cells.Position;
import effects.Effect;

public abstract class WalkableCell extends Cell
{

	public WalkableCell(Position pos, Effect eff)
	{
		super(pos, eff, true);
	}
}
