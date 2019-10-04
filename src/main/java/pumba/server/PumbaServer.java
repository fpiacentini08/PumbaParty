package main.java.pumba.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PumbaServer extends Thread
{

	private static ArrayList<ClientListener> connectedClients = new ArrayList<>();

	private static Thread server;

	private static ServerSocket serverSocket;
	private final int PUERTO = 9999;

	private final static int WIDTH = 700;
	private final static int HEIGTH = 640;
	private final static int HEIGTH_LOG = 520;
	private final static int WIDTH_LOG = WIDTH - 25;

	public static JTextArea log;

	public static void main(String[] args)
	{
		startUI();
	}

	private static void startUI()
	{
		JFrame frame = new JFrame("Servidor Pumba Party");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGTH);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);

		JLabel title = new JLabel("Log del servidor...");
		title.setFont(new Font("Courier New", Font.BOLD, 16));
		title.setBounds(10, 0, 200, 30);
		frame.add(title);

		log = new JTextArea();
		log.setEditable(false);
		log.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 40, WIDTH_LOG, HEIGTH_LOG);
		frame.add(scroll);

		final JButton btnStart = new JButton();
		final JButton btnStop = new JButton();
		btnStart.setText("Iniciar");
		btnStart.setBounds(220, HEIGTH - 70, 100, 30);
		btnStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				server = new Thread(new PumbaServer());
				server.start();
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
			}
		});

		frame.add(btnStart);

		btnStop.setText("Detener");
		btnStop.setBounds(360, HEIGTH - 70, 100, 30);
		btnStop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					server.stop();
					for (ClientListener cliente : connectedClients)
					{
						cliente.closeConnections();
					}
					serverSocket.close();
				}
				catch (IOException e1)
				{
					log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
					e1.printStackTrace();
				}
				btnStop.setEnabled(false);
				btnStart.setEnabled(true);
			}
		});
		btnStop.setEnabled(false);
		frame.add(btnStop);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				if (serverSocket != null)
				{
					try
					{
						server.stop();
						for (ClientListener cliente : connectedClients)
						{
							cliente.closeConnections();
						}
						serverSocket.close();
					}
					catch (IOException e)
					{
						log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
						e.printStackTrace();
						System.exit(1);
					}
				}
				System.exit(0);
			}
		});

		frame.setVisible(true);
	}

	public void run()
	{
		try
		{

			log.append("Iniciando el servidor..." + System.lineSeparator());
			serverSocket = new ServerSocket(PUERTO);
			log.append("Servidor esperando conexiones..." + System.lineSeparator());

			while (true)
			{
				Socket client = serverSocket.accept();
				log.append(client.getInetAddress().getHostAddress() + " se ha conectado" + System.lineSeparator());

				ClientListener atencion = new ClientListener(client);
				atencion.start();
				connectedClients.add(atencion);
			}
		}
		catch (Exception e)
		{
			log.append("Fallo la conexion." + System.lineSeparator());
			e.printStackTrace();
		}
	}

	public static ArrayList<ClientListener> getConnectedClients()
	{
		return connectedClients;
	}

}