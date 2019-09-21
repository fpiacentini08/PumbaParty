package ar.com.pumba.board.cells.impl.walkable.impl;

import ar.com.pumba.board.cells.Position;
import ar.com.pumba.board.cells.impl.walkable.WalkableCell;
import ar.com.pumba.effects.Effect;

public class LoseCoinsCellImpl extends WalkableCell
{
	public LoseCoinsCellImpl(Position pos)
	{
		super(pos, new Effect(-10));
	}

}
