package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.board.cells.Position;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnOneMessage;
import pumba.models.board.cells.PositionReduced;

public class GetPossiblePositionsMessage extends OneOnOneMessage
{

	List<PositionReduced> possiblePositions = new ArrayList<>();

	public GetPossiblePositionsMessage()
	{
		super();

	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		List<Position> positions = GameHandler.getPossiblePositions();
		for (Position pos : positions)
		{
			possiblePositions.add(mapper.convertValue(pos, PositionReduced.class));
		}
	}

}
