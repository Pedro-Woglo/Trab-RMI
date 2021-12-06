package socket;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;

import model.Animal;

public class TCPServer2 {
	
	public static void main(String args[]) {
		try {
			System.out.println("SERVIDOR INICIADO");
			int serverPort = 8080;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				new Connection1(clientSocket); // TALVEZ GERE ERRO - Cod antigo = "Connection c"
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}


class Connection1 extends Thread {
	ArrayList<Animal> animais;
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;

	public Connection1(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		
		try { 
			Gson gson = new Gson();
			animais = (ArrayList<Animal>)in.readObject(); 
			out.writeObject(animais);
			String json = gson.toJson(animais);
			System.out.println(json);
			
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("class:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace(); // AQUI TB
			}
		}
	}
}
