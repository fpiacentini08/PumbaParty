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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effect == null) ? 0 : effect.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((walkable == null) ? 0 : walkable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (effect == null)
		{
			if (other.effect != null)
				return false;
		}
		else if (!effect.equals(other.effect))
			return false;
		if (position == null)
		{
			if (other.position != null)
				return false;
		}
		else if (!position.equals(other.position))
			return false;
		if (walkable == null)
		{
			if (other.walkable != null)
				return false;
		}
		else if (!walkable.equals(other.walkable))
			return false;
		return true;
	}

	
}
