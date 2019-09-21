package main.java.pumba.board.cells;

import main.java.pumba.effects.Effect;

public abstract class Cell
{

	protected Position position = new Position();
	protected Effect effect = new Effect();
	protected Boolean walkable = false;

	
	
	public Cell(Position position, Effect effect, Boolean walkable)
	{
		super();
		this.position = position;
		this.effect = effect;
		this.walkable = walkable;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public Effect getEffect()
	{
		return effect;
	}

	public void setEffect(Effect effect)
	{
		this.effect = effect;
	}

	public Boolean getWalkable()
	{
		return walkable;
	}

	public void setWalkable(Boolean walkable)
	{
		this.walkable = walkable;
	}

}
