package View;

import Controller.Opcao;
import Entidades.Aluno;
import Entidades.Cadastro;
import Entidades.Curso;


public interface View {
	
	public Opcao menu();
	
	void listarAlunos(Cadastro cadastro);
	void listarCursos(Cadastro cadastro);
	

	void listarCursosFromAluno(Cadastro cadastro, Aluno aluno);
	void listarAlunosFromCursos(Cadastro cadastro, Curso curso);
	
	Aluno adicionaAluno();
	Curso adicionaCurso();
	
	Curso getCursoFromLista(Cadastro cadastro);
	Aluno getAlunoFromLista(Cadastro cadastro);
}
