package test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import model.Pessoa;
import stream.PessoasOutputStream;

public class PessoasOutputStreamTest {
	
		public static void main(String[] args) {
			ArrayList<Pessoa> pessoas = new ArrayList<>();
			pessoas.add(new Pessoa("Rafael", 123456, 30));
			pessoas.add(new Pessoa("Paulo", 426456, 12));
			pessoas.add(new Pessoa("Pedro", 123156, 20));
			pessoas.add(new Pessoa("George", 923156, 17));
			pessoas.add(new Pessoa("Maria Eduarda", 923156, 16));

			DataOutputStream dop = new DataOutputStream(new OutputStream() {
				
				@Override
				public void write(int b) throws IOException {
					
				}
			});
			
			PessoasOutputStream pos = new PessoasOutputStream(pessoas, dop);
			pos.writeFile();
			pos.writeSystem();
			pos.writeTCP();
		}
		
}
