package ar.com.pumba.board.cells.impl.notwalkable;

import ar.com.pumba.board.cells.Cell;
import ar.com.pumba.board.cells.Position;

public abstract class NotWalkableCell extends Cell
{

	public NotWalkableCell(Position pos)
	{
		super(pos, null, false);
	}
}
