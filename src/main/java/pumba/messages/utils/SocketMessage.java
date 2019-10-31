package pumba.messages.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public abstract class SocketMessage
{
	private String errorMessage;
	private Boolean approved;
	private String clientId;
	private Boolean autenticate;

	protected static ObjectMapper mapper = initializeMapper();

	public SocketMessage(Boolean autenticate) {
		super();
		this.autenticate = autenticate;
	}
	
	String getErrorMessage()
	{
		return errorMessage;
	}

	private static ObjectMapper initializeMapper()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Boolean getApproved()
	{
		return approved;
	}

	public void setApproved(Boolean approved)
	{
		this.approved = approved;
	}

	public void preProcessResponse(Object object) throws PumbaException, InterruptedException{
		if( autenticate && GameHandler.currentPlayer() != null && !GameHandler.currentPlayer().getUsername().equals(this.clientId)){
			notYourTurn(object);
			return;
		}
		processResponse(object);
	}

	private void notYourTurn(Object object)
	{
		this.setApproved(false);

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

	public abstract void processResponse(Object object) throws PumbaException, InterruptedException;

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this, this.getClass());
	}

}
