package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.board.cells.Position;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;

public class MoveMessage extends SocketMessage
{

	PositionReduced destination;
	List<PositionReduced> possiblePositions = new ArrayList<>();

	public MoveMessage()
	{
		super(true);

	}

	@Override
	public void processResponse(Object object)
	{
		try
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
			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}

		try
		{
			currentClient().sendMessage(this);
			sendMessageToAllOtherClients(this);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
