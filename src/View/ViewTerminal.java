package View;

import java.util.Collection;

import Controller.Opcao;
import Entidades.Aluno;
import Entidades.Curso;
import Repository.Repository;

public class ViewTerminal implements View{
	
	private Repository repositorio;
	
	public ViewTerminal(Repository repositorioCarregado) {
		this.repositorio = repositorioCarregado;
	}

	@Override
	public Aluno adicionaAluno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso adicionaCurso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso getCursoFromLista(Repository repository) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno getAlunoFromLista(Repository repository) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listarAlunos(Repository repository) {
		  /**
	     * Retorna todos alunos
	     * alunoCSV precisa ser carregado a partir do database (repository)
	     * */
		
		Collection<Aluno> alunos = repositorio.getAllAlunos();
		
		System.out.println("Todos os alunos cadastrados: ");
		
        for(Aluno aluno : alunos) {
            System.out.println(aluno);
        }
	}

	@Override
	public void listarCursos(Repository repository) {
		System.out.println("Todos os Cursos cadastrados: ");
		for(Curso curso : repositorio.getAllCursos()){
			System.out.println(curso);
		}
	}

	@Override
	public void listarCursosFromAluno(Repository repository, Aluno aluno) {
		System.out.println("Todos os cursos do Aluno: "+ aluno.getNome());
		
		for(Curso curso : repository.getCursoFromAluno(aluno)) {
			System.out.println(curso);
		}
		
	}

	@Override
	public void listarAlunosFromCursos(Repository repository, Curso curso) {
		 /**
	     * Retorna uma lista de alunos que possuem o curso fornecido
	     * */
		System.out.println("Todos os alunos do curso: "+curso.getNome());
		
		for(Aluno aluno :  repository.getAlunosFromCurso(curso)) {
			System.out.println(aluno);
		}
	}

	@Override
	public void adicionaRelacao(Aluno aluno, Curso curso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Opcao menu() {
		// TODO Auto-generated method stub
		return null;
	}

}
