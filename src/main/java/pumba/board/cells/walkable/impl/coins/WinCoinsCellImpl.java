package main.java.pumba.board.cells.walkable.impl.coins;

import java.util.Random;

import main.java.pumba.board.cells.Position;
import main.java.pumba.board.cells.walkable.WalkableCell;
import main.java.pumba.effects.Effect;

public class WinCoinsCellImpl extends WalkableCell
{
	private static final Integer maxCoins = 100;
	
	private static Random rand = new Random();
	
	public WinCoinsCellImpl(Position pos)
	{
		super(pos, new Effect(rand.nextInt(maxCoins) + 1));
	}

}
