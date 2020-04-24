package services;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import data.Empleado;
import data.Estudiante;
import data.Persona;

public class Client extends Thread{
	
	protected Socket s;
	
	private final int PORT = 1234;
	private final String HOST = "localhost";
	
	protected ObjectOutputStream oos;
    protected ObjectInputStream ois;
    private int idCliente;
    
	public Client(int idCliente){
		this.idCliente=idCliente;
	}
	
	
	@Override
	public void run() {
		try {
			this.onClient(this.idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}



	public void onClient(int idClient) throws ClassNotFoundException {

		try {
	        
	        Random aleatorio = new Random();
            int N=aleatorio.nextInt(101);
	        
	        	Random aleatorio2 = new Random();
	            int M=aleatorio2.nextInt(3);
	            
	            if (M==0) {

	            	System.out.println("Imprime "+N+ " personas");

	        }else {

	           if(M==1) {
	        	   System.out.println("Imprime "+N+ " empleados");
	           }else {
	        	   System.out.println("Imprime "+N+ " estudiantes");
	           }

	        }
	            System.out.println("tipo de usuario "+M);
	            for (int i = 0; i < N; i++) {
	            	s = new Socket(HOST, PORT);
	    			
	    			oos = new ObjectOutputStream(s.getOutputStream());
	    	        ois = new ObjectInputStream(s.getInputStream());
	            switch (M) {
				case 0:{
					Persona persona = new Persona(i, "Carlos 00"+ i, 28, "M");
					System.out.println("Client " + idClient + "> send object "+persona);
					oos.writeObject(persona);
					System.out.println((String)ois.readObject());
					break;
				}
				case 1:{
					Empleado empleado = new Empleado(i, "Eduardo 00"+ i, 20, "M", "Estudiante", "24/02/2020", 1250.3);
					System.out.println("Client " + idClient + "> send object Empleado "+empleado);
					oos.writeObject(empleado);
					System.out.println((String)ois.readObject());
					break;
				}
				case 2:{
					Estudiante estudiante = new Estudiante(i, "Monroy 00"+ i, 25, "M",123, "Sistemas", "24/02/2020");
					System.out.println("Client  " + idClient + "> send object Estudiante "+estudiante);
					oos.writeObject(estudiante);
					System.out.println((String)ois.readObject());
					break;
				}

				default:
					break;
				}
	            
		}
			
			ois.close();
			oos.close();
			
			s.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}