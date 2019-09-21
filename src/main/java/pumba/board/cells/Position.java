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

}
