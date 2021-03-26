package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class UDPServer
{
	//Atributos servidor
	private int port;
	private DatagramSocket server;
	private byte[] buffer;
	private String ip;
	private InetAddress ipComp;
	//private DatagramPacket segmentoRecibido;
	
	//Atributos para datos recibidos
	private int direccionIP;
	private int portOrigen;
	private ArrayList listEstudiantes;
	
	public UDPServer(int puertoI) 
	{
		this.port = puertoI;
		this.buffer = new byte[1024];
		this.listEstudiantes = new ArrayList();
		
	}
	public void starServer() throws SocketException
	{
		//Se obtiene la IP local de PC
		try {
			ipComp = InetAddress.getLocalHost();
			ip = ipComp.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Se crea un servidor en un puerto en cuestion y la pc donde se ejecute
		server = new DatagramSocket(this.getPort());
		
		System.out.println("Server creado: "+ip+":"+server.getLocalPort()); //LOG
		
	}
	
	public void serverListening()
	{
		System.out.println("Server a la escucha...");
		while(true) {
			
			//Se crea el datagrama a recibir mientras se escucha por el puerto en cuestion
			DatagramPacket datagramaRecibido = new DatagramPacket(buffer, buffer.length);
			
			try {
				
				server.receive(datagramaRecibido);
			} catch (IOException e) {
				//
				e.printStackTrace();
			}
			System.out.println(LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond()+ "||Dato recibido de:" + datagramaRecibido.getAddress().getHostAddress()+":"+server.getLocalPort());
			this.reconstruirObjeto(datagramaRecibido);
			this.verDatosRecibidos();
			this.enviarConfirmacion(datagramaRecibido);
			
		}
		
		
	}
	
	public void reconstruirObjeto(DatagramPacket datagramI) 
	{
		byte[] buffer = datagramI.getData(); //Obteniendo la data enviada por el usuario
		
		ByteArrayInputStream arrayFlujo =  new ByteArrayInputStream(buffer);//Convierte un array en un flujo de datos
		try {
			ObjectInputStream iStream = new ObjectInputStream(arrayFlujo);
			try {
				listEstudiantes.add((Estudiante) iStream.readObject());//Convierte el flujo de datos en un tipo de objeto(Estudiante)
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void verDatosRecibidos() 
	{
		for (int i = 0; i < listEstudiantes.size(); i++) {
			
			System.out.println("----------");
			System.out.println("Nombre:"+ ((Estudiante)listEstudiantes.get(i)).getName());
			System.out.println("Edad:"+((Estudiante)listEstudiantes.get(i)).getEdad());
			System.out.println("Codigo:"+((Estudiante)listEstudiantes.get(i)).getCodigo());
			System.out.println("Semestre:"+((Estudiante)listEstudiantes.get(i)).getSemestre());
			System.out.println("Ciudad:"+((Estudiante)listEstudiantes.get(i)).getCiudad());
			System.out.println("----------");
		}
		
	}
	
	public void enviarConfirmacion(DatagramPacket respuestaOut) {
		String mensaje = "Recibido";
		byte[] buffer = new byte[1024];
		buffer = mensaje.getBytes();
		DatagramPacket respuestaOutF = new DatagramPacket(buffer, buffer.length, respuestaOut.getAddress(), respuestaOut.getPort());
		try {
			server.send(respuestaOutF);
			System.out.println("Mensaje de confirmacion enviado a: "+respuestaOut.getPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la salida o entrada");
		}
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	

	
	
	

}
