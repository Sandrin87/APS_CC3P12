package Controller;

public enum Opcao {
	LISTA_ALUNOS(1, "Listar todos os funcionarios cadastrados"), 
	LISTA_CURSOS(2, "Listar todos os departamentos cadastrados"), 
	LISTA_ALUNOS_FROM_CURSO(3, "Listar todos os funcionarios de um departamento escolhido"), 
	LISTA_CURSOS_FROM_ALUNO(4, "Listar todos os departamentos que um funcionario escolhido trabalha"),
	ADICIONA_ALUNO(5, "Cadastrar um funcionario"),
	ADICIONA_CURSO(6, "Cadastrar um novo departamento"),
	ADICIONA_RELACAO(7, "Adicionar uma relacao entre um funcionario e um departamento"),
	SAIR(0, "Sair do programa e salvar os dados");
	
	public String descricao;
	public int codigo;
	
	private Opcao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
