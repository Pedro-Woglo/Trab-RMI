package test;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import stream.PessoasInputStream;

public class PessoasInputStreamTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataInputStream dip = new DataInputStream(new InputStream(){
			
			public int read() throws IOException {
				return 0;
			}
		});
		
		PessoasInputStream pis = new PessoasInputStream(dip);
		pis.readSystem();
		pis.readFile();
		pis.readTCP();
	}
}
