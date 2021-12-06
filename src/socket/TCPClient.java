package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	public static void main(String args[]) {
		
		Socket s = null;
		try {
			int serverPort = 8080;
			s = new Socket("localhost", serverPort);

			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF("msg2_turma_sd_2021.2");

			DataInputStream in = new DataInputStream(s.getInputStream());
			String data = in.readUTF(); 
			System.out.println("Received: " + data);
			
		} catch (UnknownHostException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline: " + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}
