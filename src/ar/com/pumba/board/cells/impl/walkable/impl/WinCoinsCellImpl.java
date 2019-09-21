package ar.com.pumba.board.cells.impl.walkable.impl;

import ar.com.pumba.board.cells.Position;
import ar.com.pumba.board.cells.impl.walkable.WalkableCell;
import ar.com.pumba.effects.Effect;

public class WinCoinsCellImpl extends WalkableCell
{
	public WinCoinsCellImpl(Position pos)
	{
		super(pos, new Effect(10));
	}

}
