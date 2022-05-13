package Entidades;

import java.util.Objects;

public class Curso {
	
	private String nivel;
	private String nome;
    private int ano;
	
	public Curso (String nivel,String nome, int ano) {
		this.nivel = nivel;
		this.nome = nome;
        this.ano= ano;
	}
	
	public Curso () {
		
	}
	
	public String getNivel() {
		return nivel;
	}

    public String getNome() {
		return this.nome;

    }


    public int getAno() {
		return this.ano;

    }


	public void setNivel(String nivel) {
		this.nivel = nivel;

    }    
	
	public void setNome(String nome) {
		this.nome = nome;
	}

    public void setAno(Integer ano) { 
		this.ano = ano;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Curso curso = (Curso) o;
		return ano == curso.ano && nivel.equals(curso.nivel) && nome.equals(curso.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nivel, nome, ano);
	}

	@Override
	public String toString() {
		return "Curso{" +
				"nivel='" + nivel + '\'' +
				", nome='" + nome + '\'' +
				", ano=" + ano +
				'}';
	}
}
