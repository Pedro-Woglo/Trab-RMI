package pojos;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Consulta extends UnicastRemoteObject implements IConsulta, Serializable {

	private static final long serialVersionUID = 1L;

	public Consulta() throws RemoteException {
		super();
	}

	@Override
	public String CastraAnimal(Estoque e, String nome) throws RemoteException {
		if(!existeAnimal(e, nome)) {
			return "Animal não cadastrado na clínica";
		}
		return nome + " foi castrado(a) com sucesso!";
	}

	@Override
	public ArrayList<Animal> AddAnimal(Estoque e, String nome, int idade, String raca) throws RemoteException {
		e.addAnimal(new Animal(nome, idade, raca));
		return e.getAnimais();
	}

	@Override
	public ArrayList<Cachorro> AddCachorroCanil(Canil c, String nome, int idade, String raca) throws RemoteException {
		c.addCachorro(new Cachorro(nome, idade, raca));
		return c.getCaes();
	}

	@Override
	public String curaAnimal(Estoque e, String nome) throws RemoteException {
		if(!existeAnimal(e, nome)) {
			return "Animal não cadastrado na clínica";
		}
		return nome + " foi curado(a) com sucesso!";
	}

	@Override
	public boolean existeAnimal(Estoque e, String nome) throws RemoteException {
		for(Animal animal : e.getAnimais()) {
			if(animal.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}

}
