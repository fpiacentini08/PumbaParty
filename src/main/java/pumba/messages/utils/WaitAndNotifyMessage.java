package pumba.messages.utils;

import java.io.IOException;

import pumba.server.ClientListener;

public abstract class WaitAndNotifyMessage extends SocketMessage
{

	protected abstract void executeActionBeforeWait(Object object);
	protected abstract void executeActionBeforeNotify();
	protected abstract void executeActionBeforeSending();

	protected abstract Integer getGameHandlerPlayersSize();

	private static Integer cantPlayers = 0;

	public WaitAndNotifyMessage()
	{
		super();
	}

	@Override
	public void processResponse(Object object) throws InterruptedException
	{
		synchronized (this)
		{
			
			executeActionBeforeWait(object);
			synchronized (cantPlayers)
			{
				cantPlayers++;
			}
			if (cantPlayers < getGameHandlerPlayersSize())
			{
				try
				{
					synchronized (object)
					{
						object.wait();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				cantPlayers = 0;

				executeActionBeforeNotify();
				
				for (ClientListener client : notCurrentClients())
				{
					synchronized (client)
					{
						client.notifyAll();
					}
				}
			}
		}

		executeActionBeforeSending();
		
		this.setApproved(true);

		try
		{
			this.setClientId(currentClient().getClientId());
			currentClient().sendMessage(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
