package Main;

import Controller.Controller;
import Repository.Repository;
import View.View;
import View.ViewTerminal;

public class Main {
	public static void main(String[] args) {
		
		View view = new ViewTerminal();
		
		String alunoPath = "database/Aluno.csv";
		String cursoPath = "database/Curso.csv";
		String relacaoPath = "database/Cadastro.csv";
		
		Repository repo = new Repository(alunoPath, cursoPath, relacaoPath);
		
		new Controller(view, repo).init();
		
	}
}
