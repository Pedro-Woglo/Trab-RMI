package model;

public class Pessoa{
    private String nome;
    private int cpf;
    private int idade;
    
    public Pessoa(String _nome, int _cpf, int _idade) {
    	this.nome = _nome;
    	this.cpf = _cpf;
    	this.idade = _idade;
    }
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String toString() {
		return "tamanhoNomePessoa: "+ nome.getBytes().length +", nome: " + this.nome + ", cpf: " + this.cpf + ", idade: " + this.idade;
	}

}