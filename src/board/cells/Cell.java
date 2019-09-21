package board.cells;

import effects.Effect;

public abstract class Cell
{

	protected Position position = new Position();
	protected Effect effect = new Effect();
	protected Boolean walkable = false;

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
