package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;

public class UDPClient
{
	private int port;
	private InetAddress host;
	private DatagramSocket socketClient;
	
	public UDPClient(int port, InetAddress host) {
		this.port = port;
		this.host = host;
		
	}
	
	public void enviarMensaje(Estudiante estudiante)
	{
		
		byte[] buffer = new byte[1024];
		buffer = this.deconstruirObject(estudiante);
		
		try {
			this.socketClient = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Canal del cliente creado");
		
		//Datagrama creado
		
		DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length, this.getHost(),this.getPort());
		try {
			socketClient.send(datagrama); //Se envia el datagrama
		} catch (PortUnreachableException e) {
			System.out.println("Ruta Inacecible");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error en el input o output");
		}
		
		System.out.println("Mensaje enviado al puerto de destino: "+this.getPort()+" a la direccion "+this.getHost().getHostAddress());
		
	}
	
	public byte[] deconstruirObject(Estudiante estudiante) {
		
		byte[] buffer = new byte[1024];
		//Convertir objeto a datas
		
		ByteArrayOutputStream datosBytes = new ByteArrayOutputStream();
		
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(datosBytes); //Descontituir un objeto
			oos.writeObject(estudiante); //Escribe el objete en un array de bytes
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		buffer = datosBytes.toByteArray(); //Del array  de bytes como flujo se pasa a un array de bytes
		
		return buffer;
	}
	
	
	public void isServerOnline() 
	{
		byte[] buffer = new byte[1024];
		DatagramPacket confirmacion = new DatagramPacket(buffer, buffer.length, this.getHost(), this.getPort());
		
		try {
			System.out.println("Esperando respuesta....");
			socketClient.setSoTimeout(10000);
			socketClient.receive(confirmacion);
			System.out.println("Estado LOG:"+new String(confirmacion.getData()));
			
		} catch (SocketTimeoutException  e) {
			// TODO Auto-generated catch block
			System.out.println("Error 504: El servidor no esta en linea");
			System.out.println("Cerrando socket...");
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		socketClient.close();
		System.out.println("Comunicacion terminada");
		
	}
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getHost() {
		return host;
	}

	public void setHost(InetAddress host) {
		this.host = host;
	}
	
	
	
	

}
