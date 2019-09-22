package main.java.pumba.board.cells.notwalkable;

import main.java.pumba.board.cells.Cell;
import main.java.pumba.board.cells.Position;

public abstract class NotWalkableCell extends Cell
{

	public NotWalkableCell(Position pos)
	{
		super(pos, null, false);
	}
}
