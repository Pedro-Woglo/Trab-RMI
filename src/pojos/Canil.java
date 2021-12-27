package pojos;

import java.io.Serializable;
import java.util.ArrayList;

public class Canil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static ArrayList<Cachorro> caes;
	
	static {
		caes = new ArrayList<>();
		Cachorro cachorro1 = new Cachorro("Charlie", 1, "Poodle");
		caes.add((Cachorro)cachorro1);
		Cachorro cachorro2 = new Cachorro("Gta", 1, "Dog Alemão");
		caes.add((Cachorro)cachorro2);
		Cachorro cachorro3 = new Cachorro("Fred", 1, "Golden Retriver");
		caes.add((Cachorro)cachorro3);
	}
	
	public void addCachorro(Cachorro c) {
		caes.add(c);
	}
	
	public ArrayList<Cachorro> getCaes() {
		return caes;
	}
	
	
}