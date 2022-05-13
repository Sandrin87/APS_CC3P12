package View;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import Controller.Controller;
import Controller.Opcao;
import Entidades.Aluno;
import Entidades.Cadastro;
import Entidades.Curso;

public class ViewTerminal implements View {


    @Override
    public Opcao menu() {
        try {
        	
            System.out.println();
            System.out.println("Escolha uma das opção");
            System.out.println();
            System.out.println();
            for (Opcao o : Opcao.values()) {
                System.out.println("" + o.codigo + " - " + o.descricao);

            }

            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);
            try {
                String entrada = in.nextLine();
                int opcaoCodigo = Integer.parseInt(entrada);

                if (!Controller.opcoesByCodigos.keySet().contains(opcaoCodigo)) {
                    throw new InputMismatchException("A opcao escolhida nao corresponde a nunhum código listado");
                }
                return Controller.opcoesByCodigos.get(opcaoCodigo);

            } catch (NumberFormatException e) {
                throw new InputMismatchException("A opção de entranda não é um número inteiro");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            menu();
        }

        return null;
    }


    @Override
    public Aluno adicionaAluno() {
        return addNewAluno();
    }

    @Override
    public Curso adicionaCurso() {
        return addNewCurso();
    }

    @Override
    public Curso getCursoFromLista(Cadastro cadastro) {
        System.out.println("Escolha um curso");
        Curso curso = this.escolherCurso(cadastro);
        if (!cadastro.getCurso().contains(curso)) {
            System.out.println("Não encontramos um curso com essas informações");
            return null;
        }
        return curso;
    }

    @Override
    public Aluno getAlunoFromLista(Cadastro cadastro) {
        System.out.println("Entre com o id do Aluno: ");
        listarAlunos(cadastro);
        System.out.println("Entre com o id do Aluno: ");
        String id = getString();

        Aluno aluno = cadastro.getAlunoFromId(id);
        if(aluno==null){
            System.out.println("Não encontramos um aluno com esse id");
        }

        return aluno;
    }

    @Override
    public void listarAlunos(Cadastro cadastro) {
        /**
         * Retorna todos alunos
         * alunoCSV precisa ser carregado a partir do database (repository)
         * */

    	System.out.println("Todos os alunos cadastrados: ");
        for (Aluno aluno : cadastro.getAluno()) {
            System.out.println(aluno);
        }
    }

    @Override
    public void listarCursos(Cadastro cadastro) {
        System.out.println("Todos os Cursos cadastrados: ");
        for (Curso curso : cadastro.getCurso()) {
            System.out.println(curso);
        }
    }

    @Override
    public void listarCursosFromAluno(Cadastro cadastro, Aluno aluno) {
        System.out.println("Todos os cursos do Aluno: " + aluno.getNome());

        for (Curso curso : cadastro.getCursoFromAluno(getIdAluno())) {
            System.out.println(curso);
        }

    }

    @Override
    public void listarAlunosFromCursos(Cadastro cadastro, Curso curso) {
        /**
         * Retorna uma lista de alunos que possuem o curso fornecido
         * */
        System.out.println("Todos os alunos do curso: " + curso.getNome());

        for (Aluno aluno : cadastro.getAlunoFromCurso(curso)) {
            System.out.println(aluno);
        }
    }

    public Aluno addNewAluno() {
        System.out.println("Entre com os dados do aluno: ");
        String id = getIdAluno();
        String nome = getNomeAluno();
        return new Aluno(id, nome);
    }

    private String getIdAluno() {
        System.out.println("Digite o id do aluno: ");
        return getString();
    }

    private String getNomeAluno() {
        System.out.println("Digite o nome do aluno: ");
        return getString();
    }

    public Curso addNewCurso() {
        System.out.println("Entre com os dados do curso: ");
        String nivel = getNivelCurso();
        String nome = getNomeCurso();
        int ano = getAnoCurso();
        return new Curso(nivel, nome, ano);

    }

    private String getNivelCurso() {
        System.out.println("Qual o nivel do curso: ");
        return getString();
    }

    private String getNomeCurso() {
        System.out.println("Qual o nome do curso: ");
        return getString();
    }

    private int getAnoCurso() {
        System.out.println("Qual o ano do curso: ");
        return getInt();
    }

    private Curso escolherCurso(Cadastro cadastro) {
        String nivel = escolherNivelCurso(cadastro);
        String nome = escolherNomeCurso(cadastro);
        int ano = escolherAnoCurso(cadastro);
        return new Curso(nivel, nome, ano);

    }

    private String escolherNivelCurso(Cadastro cadastro) {
        Set<String> niveis = new TreeSet<>();
        for(Curso c: cadastro.getCurso()){
            niveis.add(c.getNivel());
        }
        System.out.println("Escolha um dos niveis: ");
        for(String nivel: niveis) {
            System.out.println(nivel);
        }
        System.out.println("Digite o nivel escolhido: ");
        return getString();
    }

    private String escolherNomeCurso(Cadastro cadastro) {
        Set<String> nomes = new TreeSet<>();
        for(Curso c: cadastro.getCurso()){
            nomes.add(c.getNome());
        }
        System.out.println("Escolha um dos nomes: ");
        for(String nome: nomes) {
            System.out.println(nome);
        }
        System.out.println("Digite o nome escolhido: ");
        return getString();

    }

    private int escolherAnoCurso(Cadastro cadastro) {
        Set<Integer> anos = new TreeSet<>();
        for(Curso c: cadastro.getCurso()) {
            anos.add(c.getAno());
        }
        System.out.println("Escolha um dos anos: ");
        for(int ano: anos) {
            System.out.println(ano);
        }
        System.out.println("Digite o ano escolhido: ");
        return getInt();
    }

    public String getString() {
        try (Scanner in = new Scanner(System.in)) {
			String str = in.nextLine();
			return str.trim();
		}
    }
    
    public int getInt() {
        try (Scanner in = new Scanner(System.in)) {
			int inteiro = in.nextInt();
			return inteiro;
		}
    }
}


