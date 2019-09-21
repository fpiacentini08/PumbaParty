package board.cells.impl.walkable.impl;

import board.cells.Position;
import board.cells.impl.walkable.WalkableCell;
import effects.Effect;

public class WinCoinsCellImpl extends WalkableCell
{
	public WinCoinsCellImpl(Position pos)
	{
		super(pos, new Effect(10));
	}

}
