package ar.com.pumba.board.cells.impl.walkable;

import ar.com.pumba.board.cells.Cell;
import ar.com.pumba.board.cells.Position;
import ar.com.pumba.effects.Effect;

public abstract class WalkableCell extends Cell
{

	public WalkableCell(Position pos, Effect eff)
	{
		super(pos, eff, true);
	}
}
