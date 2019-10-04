package pumba.board.cells.walkable;

import pumba.board.cells.Cell;
import pumba.board.cells.Position;
import pumba.effects.Effect;

public abstract class WalkableCell extends Cell
{

	public WalkableCell(Position pos, Effect eff)
	{
		super(pos, eff, true);
	}
}
