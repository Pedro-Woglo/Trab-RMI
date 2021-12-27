package pojos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IConsulta extends Remote{
	
	String CastraAnimal(Estoque e, String nome) throws RemoteException;
	
	ArrayList<Animal> AddAnimal(Estoque e, String nome, int idade, String raca) throws RemoteException;
	
	ArrayList<Cachorro> AddCachorroCanil(Canil c, String nome, int idade, String raca) throws RemoteException;
	
	String curaAnimal(Estoque e, String nome) throws RemoteException;
	
	boolean existeAnimal(Estoque e, String nome) throws RemoteException;
	
}
