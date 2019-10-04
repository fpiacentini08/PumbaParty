package pumba.board.cells.notwalkable;

import pumba.board.cells.Cell;
import pumba.board.cells.Position;

public abstract class NotWalkableCell extends Cell
{

	public NotWalkableCell(Position pos)
	{
		super(pos, null, false);
	}
}
