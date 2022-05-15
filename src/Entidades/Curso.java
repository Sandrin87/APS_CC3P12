package Entidades;

import java.util.Objects;

public class Curso implements Comparable<Curso>{
	
	private String nivel;
	private String nome;
    private String ano;
	
	public Curso (String nivel,String nome, String ano) {
		this.nivel = nivel;
		this.nome = nome;
        this.ano= ano;
	}
	
	public String getNivel() {
		return this.nivel;
	}

    public String getNome() {
		return this.nome;

    }


    public String getAno() {
		return this.ano;

    }


	public void setNivel(String nivel) {
		this.nivel = nivel;

    }    
	
	public void setNome(String nome) {
		this.nome = nome;
	}

    public void setAno(String ano) { 
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

	@Override
	public int compareTo(Curso o) {
		if(!this.nivel.equals(o.nivel)) {
			return this.nivel.compareTo(o.nivel);
		}else if(!this.ano.equals(o.ano)) {
			return this.ano.compareTo(o.ano);
		}
		return this.nome.compareTo(o.nome);
	}
}
