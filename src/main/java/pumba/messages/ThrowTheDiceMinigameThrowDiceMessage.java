package pumba.messages;

import java.io.IOException;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.ThrowTheDiceMinigameResult;
import pumba.minigame.throwthedice.handler.ThrowTheDiceMinigameHandler;

public class ThrowTheDiceMinigameThrowDiceMessage extends SocketMessage
{

	private ThrowTheDiceMinigameResult result;

	public ThrowTheDiceMinigameThrowDiceMessage()
	{
		super(false);
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			this.result = ThrowTheDiceMinigameHandler.throwDice();
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
