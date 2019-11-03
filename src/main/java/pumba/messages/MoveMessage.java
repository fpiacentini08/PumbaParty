package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.board.cells.Position;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.OneOnAllMessage;
import pumba.models.board.cells.PositionReduced;

public class MoveMessage extends OneOnAllMessage
{

	PositionReduced destination;
	List<PositionReduced> possiblePositions = new ArrayList<>();

	public MoveMessage()
	{
		super();

	}

	@Override
	protected void executeAction(Object object) throws PumbaException
	{
		Position finalPosition = mapper.convertValue(destination, Position.class);
		List<Position> positions = GameHandler.move(finalPosition);
		for (Position pos : positions)
		{
			possiblePositions.add(mapper.convertValue(pos, PositionReduced.class));
		}
		if (positions.size() > 1)
		{
			this.destination = null;
		}
	}

}
