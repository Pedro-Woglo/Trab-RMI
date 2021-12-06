package stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Pessoa;

public class PessoasInputStream extends InputStream{
	private InputStream is;
	
	public PessoasInputStream(InputStream _is) {
		this.is = _is;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}
	
	public void readSystem() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String s = "Número de pessoas: ";
		System.out.print("Quantas pessoas deseja inserir?: ");
		int qtd = sc.nextInt();
		s += qtd + "\n\n";
		for(int i = 0; i <= qtd-1; i++) {
			Pessoa p;
			System.out.print("Digite o nome: ");
			String nome = sc.next();
			System.out.print("Digite o cpf: ");
			int cpf = sc.nextInt();
			System.out.print("Digite a idade: ");
			int idade = sc.nextInt();
			pessoas.add(new Pessoa(nome, cpf, idade));
			p = new Pessoa(nome, cpf, idade);
			s += p + "\n";
		}
		System.out.println("\n");
		this.is = new ByteArrayInputStream(s.getBytes());
		Scanner sc2 = new Scanner(this.is).useDelimiter("\\A");
		String result = sc2.hasNext() ? sc2.next() : "";
		sc.close();
		sc2.close();
		System.out.println(result);
	}
	
	public void readFile() throws IOException, FileNotFoundException{
		this.is = new FileInputStream("/home/pedro/input_text");
		int data = this.is.read();
        while (data != -1) {
              System.out.print((char)data);
              data = this.is.read();
        }
        this.is.close();
	}
	
	public void readTCP() {
		Socket s = null;
		Scanner sc = new Scanner(System.in);
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		String str = "Número de pessoas: ";
		System.out.print("Quantas pessoas deseja inserir?: ");
		int qtd = sc.nextInt();
		str += qtd + "\n\n";
		try {
			int serverPort = 8080;
			s = new Socket("localhost", serverPort);
			
			DataOutputStream op = new DataOutputStream(s.getOutputStream());
			DataOutputStream out = (DataOutputStream) op;
			
			for(int i = 0; i <= qtd-1; i++) {
				Pessoa p;
				System.out.print("Digite o nome: ");
				String nome = sc.next();
				System.out.print("Digite o cpf: ");
				int cpf = sc.nextInt();
				System.out.print("Digite a idade: ");
				int idade = sc.nextInt();
				pessoas.add(new Pessoa(nome, cpf, idade));
				p = new Pessoa(nome, cpf, idade);
				str += p + "\n";
			}
			sc.close();
			System.out.println("\n");
			out.writeUTF(str);
			
			this.is = new DataInputStream(s.getInputStream());
			String data = ((DataInputStream)this.is).readUTF(); 
			System.out.println("Received: \n" + data);
			
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
