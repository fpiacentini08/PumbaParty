package pumba;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import pumba.handlers.GameHandler;
import pumba.server.PumbaServer;

public class ServerMain
{

	public static void main(String[] args)
	{
		String cantPlayers = "DUMMY";

		while (cantPlayers != null && !StringUtils.isNumeric(cantPlayers))
		{
			cantPlayers = JOptionPane.showInputDialog(
					"Bienvenido al servidor de Pumba Party\nPor favor, ingrese la cantidad de jugadores:");
		}

		if (cantPlayers == null)
		{
			System.exit(1);
		}
		GameHandler.setCantPlayers(Integer.parseInt(cantPlayers));

		PumbaServer.startServer();
	}
}
