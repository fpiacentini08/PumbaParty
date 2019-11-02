package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.board.cells.Position;
import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;

public class GetPossiblePositionsMessage extends SocketMessage
{

	List<PositionReduced> possiblePositions = new ArrayList<>();

	public GetPossiblePositionsMessage()
	{
		super(false);

	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			List<Position> positions = GameHandler.getPossiblePositions();
			for (Position pos : positions)
			{
				possiblePositions.add(mapper.convertValue(pos, PositionReduced.class));
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
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
