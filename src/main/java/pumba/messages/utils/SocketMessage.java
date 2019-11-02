package pumba.messages.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import pumba.exceptions.PumbaException;
import pumba.server.ClientListener;
import pumba.server.PumbaServer;

public abstract class SocketMessage 
{
	private String errorMessage;
	private Boolean approved;
	private String clientId;
	private Boolean autenticate;

	protected static ObjectMapper mapper = initializeMapper();

	public SocketMessage(Boolean autenticate)
	{
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
	
	public abstract void processResponse(Object object) throws PumbaException, InterruptedException;

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this, this.getClass());
	}

	protected ClientListener currentClient()
	{
		return PumbaServer.getConnectedClients().stream()
				.filter(clientListener -> clientListener.getClientId().equals(this.getClientId()))
				.collect(Collectors.toList()).get(0);
	}

	protected List<ClientListener> notCurrentClients()
	{
		return PumbaServer.getConnectedClients().stream()
				.filter(clientListener -> !clientListener.getClientId().equals(this.getClientId()))
				.collect(Collectors.toList());
	}
	
	protected void sendMessageToAlOtherClients(SocketMessage message) {
		for (ClientListener client : notCurrentClients())
		{
			message.setClientId(client.getClientId());
			try
			{
				client.sendMessage(message);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
