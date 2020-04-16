package services;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("client>Running");
			Client client = new Client();
			client.onClient(1);
		
	}
}
