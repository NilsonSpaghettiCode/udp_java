package interfaz;


import java.net.SocketException;
import java.net.UnknownHostException;

import model.UDPServer;

public class interfazApp
{
	public static void main(String[] args) throws UnknownHostException 
	{
		int port = 9107;
		
		//Inicializador del servidor
		UDPServer server = new UDPServer(port);
		
		//Servidor ON
		try {
			server.starServer();
			server.serverListening();
			
		} catch (SocketException e) {
			System.out.println("Error al inicializar el servidor: "+e);
		}
		
		
		
	}

}
