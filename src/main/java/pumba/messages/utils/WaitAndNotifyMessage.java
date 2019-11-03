package pumba.messages.utils;

import java.io.IOException;

import pumba.server.ClientListener;

public abstract class WaitAndNotifyMessage extends SocketMessage
{

	protected abstract void executeActionBeforeWait(Object object);

	protected abstract void executeActionBeforeNotify();

	protected abstract void executeActionBeforeSending();

	protected abstract Integer getGameHandlerPlayersSize();

	protected static Integer cantPlayers = 0;

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
						System.out.println("Espera!");
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
				System.out.println("Libera!");
				cantPlayers = 0;

				executeActionBeforeNotify();
				System.out.println("Antes de notificar!");

				for (ClientListener client : notCurrentClients())
				{
					System.out.println("Cliente! : " + client.getClientId());

					if (!client.getClientId().contains("LISTENER"))
					{
						synchronized (client)
						{
							System.out.println("Notifica! : " + client.getClientId());

							client.notifyAll();
						}
					}

				}
			}
		}

		System.out.println("Se fue!");

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
