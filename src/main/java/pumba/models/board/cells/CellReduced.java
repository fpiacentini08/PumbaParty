package pumba.models.board.cells;

public class CellReduced
{

	protected PositionReduced position;

	public CellReduced(PositionReduced position)
	{
		super();
		this.position = position;
	}

	public CellReduced()
	{
		super();
	}

	public PositionReduced getPosition()
	{
		return position;
	}

}
