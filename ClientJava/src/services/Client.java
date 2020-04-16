package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.Persona;

public class Client {
	protected Socket s;
	private final int PORT = 1234;
	private final String HOST = "lOCALHOST";
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;

	public Client()  {
		
	}
	
	public void onClient(int idClient) throws ClassNotFoundException {
		
		try {
			
			Persona persona = new Persona(idClient, "carlos", 28 , "M");
			System.out.println("client>send Object");
			s = new Socket(HOST,PORT);
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			//Persona persona = new Persona (idClient, "carlos,",28,"M");
			
			oos.writeObject(persona);
			System.out.println("client>receive answer server"+(String)ois.readObject());
		    ois.readObject();
			
			ois.close();
			oos.close();
					
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
