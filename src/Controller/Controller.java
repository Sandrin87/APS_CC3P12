package Controller;

import java.util.Map;
import java.util.TreeMap;

import Entidades.Aluno;
import Entidades.Cadastro;
import Entidades.Curso;
import Repository.Repository;
import View.View;

public class Controller {
	
	public static Map<Integer, Opcao> opcoesByCodigos = new TreeMap<>();
	{
		for(Opcao o: Opcao.values()) {
			opcoesByCodigos.put(o.codigo, o);
		}
	}
	
	private View view;
	private Repository repository;
	private Cadastro cadastro;
	
	public Controller(View _view, Repository _repository) {
		this.view = _view;
		this.repository = _repository;
	}
	
	public void init() {
		cadastro = repository.getCadastro();
		
		Opcao codigo = null;
		while(codigo!=Opcao.SAIR) {
			codigo = view.menu();
			switch (codigo) {
			case LISTA_ALUNOS: {listarAlunos(); break;}
			case LISTA_CURSOS: {listarCursos(); break;}
			case ADICIONA_ALUNO: {adicionarAluno(); break;}
			case ADICIONA_CURSO: {adicionarCurso(); break;}
			case LISTA_ALUNOS_FROM_CURSO: {listarAlunosFromCurso(); break;}
			case LISTA_CURSOS_FROM_ALUNO: {listarCursoFromAluno(); break;}
			case ADICIONA_RELACAO: {adicionaRelacao(); break;}
			case SAIR: {terminar(); break;}
			default:
				throw new IllegalArgumentException("Unexpected value: " + codigo);
			}
		}
	}

	private void listarAlunos() {
		view.listarAlunos(this.cadastro);
		
	}

	private void listarCursos() {
		view.listarCursos(this.cadastro);		
	}

	private void adicionarAluno() {
		Aluno novoAluno = view.adicionaAluno();
		if(novoAluno == null)return;
		this.cadastro.addAluno(novoAluno);
		
	}
	
	private void adicionarCurso() {
		Curso novoCurso = view.adicionaCurso();
		if(novoCurso == null)return;
		this.cadastro.addCurso(novoCurso);
	}
	
	private void listarAlunosFromCurso() {
		Curso curso = view.getCursoFromLista(this.cadastro);
		if(curso == null)return;		
		view.listarAlunosFromCursos(this.cadastro, curso);
	}
	
	private void listarCursoFromAluno() {
		Aluno aluno = view.getAlunoFromLista(this.cadastro);
		if(aluno == null) return;
		view.listarCursosFromAluno(this.cadastro, aluno);
	}
	
	private void adicionaRelacao() {
		Aluno aluno = view.getAlunoFromLista(this.cadastro);
		if(aluno == null) return;
		Curso curso = view.getCursoFromLista(this.cadastro);
		if(curso == null) return;
		this.cadastro.addRelacao(aluno, curso);
	}
	
	private void terminar() {
		this.repository.saveCadastro(this.cadastro);
	}
}
