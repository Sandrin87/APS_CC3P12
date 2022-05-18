package Controller;

public enum Opcao {
	LISTA_ALUNOS(1, "Listar todos os alunos cadastrados"),
	LISTA_CURSOS(2, "Listar todos os cursos cadastrados"),
	LISTA_ALUNOS_FROM_CURSO(3, "Listar todos os alunos de um curso escolhido"),
	LISTA_CURSOS_FROM_ALUNO(4, "Listar todos os cursos que um aluno escolhido esta cadastrado"),
	ADICIONA_ALUNO(5, "Cadastrar um aluno"),
	ADICIONA_CURSO(6, "Cadastrar um novo curso"),
	ADICIONA_RELACAO(7, "Adicionar uma relacao entre um aluno e um curso"),
	SAIR(0, "Sair do programa e salvar os dados");
	
	public String descricao;
	public int codigo;
	
	private Opcao(int _codigo, String _descricao) {
		this.codigo = _codigo;
		this.descricao = _descricao;
	}
}
