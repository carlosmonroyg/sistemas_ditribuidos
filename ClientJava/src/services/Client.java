package services;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import data.Empleado;
import data.Estudiante;
import data.Persona;

public class Client  extends Thread {
	
	protected Socket s;
	
	private final int PORT = 1234;
	private final String HOST = "localhost";
	
	protected ObjectOutputStream oos;
    protected ObjectInputStream ois;
    
    
    private int idClient;

	public Client(int idClient){
		this.idClient = idClient;
		
	}
	
	

	@Override
	public void run() {
		try {
			this.onClient(this.idClient);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void onClient(int idClient) throws ClassNotFoundException {
		
		
		

		try {
			s = new Socket(HOST, PORT);
			
			oos = new ObjectOutputStream(s.getOutputStream());
	        ois = new ObjectInputStream(s.getInputStream());
	        
	        
	        for (int i = 1; i <= 100; i++) {
	        	
	        	
	    		
			
	        	Random aleatorio2 = new Random();
	            int M=aleatorio2.nextInt(3);
	           
	            switch (M) {
				case 0:{
					Persona persona = new Persona(1, "Eduardo", 21, "M");
					System.out.println("Client "+idClient+"> send object "+persona);
					oos.writeObject(persona);
					break;
				}
				case 1:{
					Empleado empleado = new Empleado(1, "Carlos", 28, "M", "Estudiante", "19/02/2020", 1250.3);
					System.out.println("Client "+idClient+"> send object Empleado "+empleado);
					oos.writeObject(empleado);
					break;
				}
				case 2:{
					Estudiante estudiante = new Estudiante(1, "Monroy", 22, "M",123, "Sistemas", "19/02/2020");
					System.out.println("Client "+idClient+"> send object Estudiante "+estudiante);
					oos.writeObject(estudiante);
					break;
				}

				default:
					break;
				}
	        }
	            
	            
	            
	            
	            
			
			System.out.println((String)ois.readObject());
			
			ois.close();
			oos.close();
			
			s.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}