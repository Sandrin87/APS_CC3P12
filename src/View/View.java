package View;

import Controller.Opcao;
import Entidades.Aluno;
import Entidades.Curso;
import Repository.Repository;

public interface View {
	
	public Opcao menu();
	
	void listarAlunos(Repository repository);
	void listarCursos(Repository repository);
	

	void listarCursosFromAluno(Repository repository, Aluno aluno);
	void listarAlunosFromCursos(Repository repository, Curso curso);
	
	Aluno adicionaAluno();
	Curso adicionaCurso();

	void adicionaRelacao(Aluno aluno, Curso curso);
	
	Curso getCursoFromLista(Repository repository);
	Aluno getAlunoFromLista(Repository repository);
}
