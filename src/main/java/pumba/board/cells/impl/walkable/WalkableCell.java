package main.java.pumba.board.cells.impl.walkable;

import main.java.pumba.board.cells.Cell;
import main.java.pumba.board.cells.Position;
import main.java.pumba.effects.Effect;

public abstract class WalkableCell extends Cell
{

	public WalkableCell(Position pos, Effect eff)
	{
		super(pos, eff, true);
	}
}
