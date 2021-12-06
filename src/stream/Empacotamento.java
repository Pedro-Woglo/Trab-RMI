package stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.google.gson.Gson;

import model.Animal;
import model.Estoque;

public class Empacotamento {

	 public void gravarAnimaisTCP() {
	      Estoque estoque = new Estoque();
	      ArrayList<Animal> animais = estoque.getAnimais();
	      ArrayList<Animal> input;
	      Gson gson = new Gson();
	      Socket s = null;
		  try {
			  int serverPort = 8080;
			  s = new Socket("localhost", serverPort);
			  
			  ObjectOutputStream op = new ObjectOutputStream(s.getOutputStream());
			  ObjectOutputStream out = (ObjectOutputStream) op;
				
			  out.writeObject(animais);
				
			  ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			  input = (ArrayList<Animal>)in.readObject(); 
			  String json = gson.toJson(input);
			  System.out.println(json);
				
		  } catch (UnknownHostException e) {
			  System.out.println("Socket: " + e.getMessage());
		  } catch (EOFException e) {
			  System.out.println("EOF: " + e.getMessage());
		  } catch (IOException e) {
			  System.out.println("readline: " + e.getMessage());
		  } catch (ClassNotFoundException e) {
			  System.out.println("class: " + e.getMessage());
		  } finally {
			  if (s != null)
				  try {
					  s.close();
				  } catch (IOException e) {
					  System.out.println("close:" + e.getMessage());
				  }
		  }
	      
	 }
	 
	 public static void main(String[] args) {
		 
		 Empacotamento emp = new Empacotamento();
		 emp.gravarAnimaisTCP();
	 }
}
