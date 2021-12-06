package model;

import java.io.Serializable;

public class Animal implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
    private int idade;
    private String raca;
    
    public Animal(String _nome, int _idade, String _raca) {
    	this.nome = _nome;
    	this.idade = _idade;
    	this.raca = _raca;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	public String toString() {
		return "Animal [nome=" + this.nome + ", idade=" + this.idade + " raça=" + this.raca + "]";
	}
}
