package main.java.pumba.board.cells;

public class Position
{
	private Integer posX;
	private Integer posY;

	public Integer getPosX()
	{
		return posX;
	}

	public void setPosX(Integer posX)
	{
		this.posX = posX;
	}

	public Integer getPosY()
	{
		return posY;
	}

	public void setPosY(Integer posY)
	{
		this.posY = posY;
	}

	public Position(Integer posX, Integer posY)
	{
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public Position()
	{
		super();
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
		Position other = (Position) obj;
		if (posX == null)
		{
			if (other.posX != null)
				return false;
		}
		else if (!posX.equals(other.posX))
			return false;
		if (posY == null)
		{
			if (other.posY != null)
				return false;
		}
		else if (!posY.equals(other.posY))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "(" + posX + "," + posY + ")";
	}

}
