package pumba.messages.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	protected static ObjectMapper mapper = initializeMapper();

	public SocketMessage()
	{
		super();
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
		Stream<ClientListener> filtered = PumbaServer.getConnectedClients().stream()
				.filter(clientListener -> clientListener != null && clientListener.getClientId() != null
						&& clientListener.getClientId().equals(getClientId()));
		return filtered.collect(Collectors.toList()).get(0);
	}

	protected List<ClientListener> notCurrentClients()
	{
		Stream<ClientListener> filtered = PumbaServer.getConnectedClients().stream()
				.filter(clientListener -> clientListener.getClientId() != null && !clientListener.getClientId().equals(this.getClientId()));
		return filtered.collect(Collectors.toList());
	}

	protected void sendMessageToAllOtherClients(SocketMessage message)
	{
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
