package pojos;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorRMI {

	public static void main(String[] args) {
		
		try {
			IConsulta consulta = new Consulta();
			String objname = "rmi://localhost/Consulta";
			
			System.out.println("Registrando objeto 'Consulta' no RMIRegistry...");
			LocateRegistry.createRegistry(1099);
			Naming.rebind(objname, consulta);
			
			System.out.println("Aguardando requisições");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
