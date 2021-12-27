package pojos;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ClienteRMI {

	public static void main(String[] args) {
		
		Estoque est = new Estoque();
		Canil can = new Canil();
		ArrayList<Cachorro> caes = new ArrayList<>();
		ArrayList<Animal> animais = new ArrayList<>();
		Gson gson = new Gson();
		String json;
		
		try {
			IConsulta stub = (IConsulta) Naming.lookup("rmi://localhost/Consulta");
			System.out.println("Conexão com servidor realizada!");
			System.out.println("");
			System.out.println("Objeto remoto localizado...");
			System.out.println("");
			
			json = stub.curaAnimal(est, "Charl");
			System.out.println(gson.toJson(json));
			
			json = stub.CastraAnimal(est, "Giba");
			System.out.println(gson.toJson(json));
			
			caes = stub.AddCachorroCanil(can, "Fella", 3, "Dog Alemão");
			json = gson.toJson(caes);
			System.out.println(json);
			
			animais = stub.AddAnimal(est, "River", 2, "Pincher");
			json = gson.toJson(animais);
			System.out.println(json);
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

	}

}
