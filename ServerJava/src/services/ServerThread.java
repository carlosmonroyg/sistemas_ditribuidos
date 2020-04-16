package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.Persona;

public class ServerThread extends Thread{

	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private int idClient;
	


	public ServerThread(Socket s, int idClient) throws IOException {
		this.s = s;
		this.idClient = idClient;
		ois = new ObjectInputStream(s.getInputStream());
		oos = new ObjectOutputStream(s.getOutputStream());
		
	}

	@Override
	public void run() {

		try {
			System.out.println("client>"+idClient +"Started");
			Persona persona = new Persona();
			
			
			persona = (Persona)ois.readObject();
			
			System.out.println("Object>"+persona +"Started");
			oos.writeObject("Server>...persona"+persona.getIdentificacion()+"recived");
			
			s.close();


		} catch (Exception e)  {
			System.out.println("Server> "+e.getMessage());
		}		


	}



}
