package stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Pessoa;

public class PessoasOutputStream extends OutputStream{
	
	private ArrayList<Pessoa> pessoas;
	private OutputStream op;
	
	public PessoasOutputStream(ArrayList<Pessoa> p, OutputStream os) {
		this.pessoas = p;
		this.op = os;
	}
	
	@Override
	public void write(int b) throws IOException {
		
	}
	
	public void writeFile() {
		try {
	           this.setOp(new FileOutputStream("/home/pedro/input_text"));
	           String s = "";
	           String qtdPessoas = "Número de pessoas: " + pessoas.size() + "\n\n";
	           int count = 0;
	           
	           while (count <= qtdPessoas.length()-1) {
	                  this.op.write(qtdPessoas.charAt(count));
	                  count++;
	           }

	           count = 0;
	           
	           for (Pessoa pessoa : pessoas) {
					int tamanhoNomePessoa = pessoa.getNome().getBytes().length;
					String nome = pessoa.getNome();
					int cpf = pessoa.getCpf();
					int idade = pessoa.getIdade();
					
					s = ("tamanhoNomePessoa: "+ tamanhoNomePessoa +", nome: "+ nome +", cpf: "+ cpf +", idade: "+ idade +"\n");
					while (count <= s.length()-1) {
		                  this.op.write(s.charAt(count));
		                  count++;
		           }
				   count = 0;
				}
	    } catch (IOException e) {
	           e.printStackTrace();
	    } finally {
	    	if (this.op != null){
                try {
					this.op.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
	    	}
	    }
	}
	
	public void writeSystem() {
			
			int qtdpessoas = pessoas.size();
			System.out.println("Número de pessoas: " + qtdpessoas + "\n");
			for (Pessoa pessoa : pessoas) {
				int tamanhoNomePessoa = pessoa.getNome().getBytes().length;
				String nome = pessoa.getNome();
				int cpf = pessoa.getCpf();
				int idade = pessoa.getIdade();
				
				System.out.println("tamanhoNomePessoa: "+ tamanhoNomePessoa +" nome: "+ nome +" cpf: "+ cpf + " idade: "+ idade);
			}
	}
	
	public void writeTCP() {
		Socket s = null;
		String str = "";
		try {
			int serverPort = 8080;
			s = new Socket("localhost", serverPort);
			
			op = new DataOutputStream(s.getOutputStream());
			DataOutputStream out = (DataOutputStream) op;
			
			for (Pessoa pessoa : pessoas) {
				int tamanhoNomePessoa = pessoa.getNome().getBytes().length;
				String nome = pessoa.getNome();
				int cpf = pessoa.getCpf();
				int idade = pessoa.getIdade();
				
				str += "tamanhoNomePessoa: "+ tamanhoNomePessoa +" nome: "+ nome +" cpf: "+ cpf +" idade: "+ idade +"\n";
			}
			
			out.writeUTF(str);
			
			DataInputStream in = new DataInputStream(s.getInputStream());
			String data = in.readUTF(); 
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

	public void setOp(OutputStream op) {
		this.op = op;
	}
}


