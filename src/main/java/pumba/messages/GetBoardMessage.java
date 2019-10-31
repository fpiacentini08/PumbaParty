package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pumba.board.Board;
import pumba.board.cells.Cell;
import pumba.handlers.BoardHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.CellReduced;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public class GetBoardMessage extends SocketMessage
{
	private List<CellReduced> cells = new ArrayList<>();
	private Integer dimension;

	public GetBoardMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Board board = BoardHandler.getBoard();
		for(Cell cell : board.getCells()) {
			this.cells.add(mapper.convertValue(cell, CellReduced.class));
		}
		
		this.dimension = Board.getDimension();
		this.setApproved(true);

		for (ClientListener connected : PumbaServer.getConnectedClients())
		{
			try
			{
				connected.sendMessage(this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	public List<CellReduced> getCells()
	{
		return cells;
	}

	public void setCells(List<CellReduced> cells)
	{
		this.cells = cells;
	}

	public Integer getDimension()
	{
		return dimension;
	}

	public void setDimension(Integer dimension)
	{
		this.dimension = dimension;
	}

}
