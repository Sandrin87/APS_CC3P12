package Entidades;

import java.util.Objects;
 
public class Aluno implements Comparable<Aluno>{

	private String id;
	private String nome;

	public Aluno (String id, String nome) {
		this.id = id;
		this.nome = nome;

	}

	public String getId() {
		return id;
    }

    public String getNome() {
		return nome;

    }

	public void setId (String id) {
		this.id = id;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Aluno aluno = (Aluno) o;
		return id.equals(aluno.id) && nome.equals(aluno.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public String toString() {
		return "Aluno{" +
				"id='" + id + '\'' +
				", nome='" + nome + '\'' +
				'}';
	}

	@Override
	public int compareTo(Aluno o) {
		return this.id.compareTo(o.id);
	}
}