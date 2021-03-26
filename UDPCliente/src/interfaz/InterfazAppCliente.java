package interfaz;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Estudiante;
import model.UDPClient;

public class InterfazAppCliente
{
	public static void main(String[] args) throws IOException 
	{
		InterfazAppCliente ia = new InterfazAppCliente();
		
		
		int portDestino = 9107;
		
		//Creacion de mensaje de informacion
		Estudiante estudiante = ia.openDialog();
		
		try {
			UDPClient client = new UDPClient(portDestino,InetAddress.getLocalHost());
			client.enviarMensaje(estudiante);
			client.isServerOnline();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public Estudiante openDialog() 
	{
		Scanner input = new Scanner(System.in);
		
		String nombre = "";
		String ciudad = "";
		long id = 0;
		int edad = 0;
		int semestre = 0;
		
		
		System.out.println("Hola porfavor escriba el mensaje a enviar.....");
		
		System.out.println("Nombre:");
		nombre = input.next();
		
		
		System.out.println("Ciudad:");
		ciudad = input.next();
		
		System.out.println("ID:");
		id = input.nextLong();
		
		System.out.println("Edad:");
		edad = input.nextInt();
		
		System.out.println("Semestre:");
		semestre = input.nextInt();
		
		input.close();
		
		return new Estudiante(nombre, ciudad, id, edad, semestre);
	}
	
	

}
