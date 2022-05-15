package View;

import java.util.*;

import Controller.Controller;
import Controller.Opcao;
import Entidades.Aluno;
import Entidades.Cadastro;
import Entidades.Curso;
import Repository.Repository;

public class ViewTerminal implements View {


	@Override
    public Opcao menu() {
        try {

            System.out.println("Escolha uma das opcoes");
            for (Opcao o : Opcao.values()) {
                System.out.println("" + o.codigo + " - " + o.descricao);

            }

            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);
            try {
                String entrada = in.nextLine();
                int opcaoCodigo = Integer.parseInt(entrada);

                if (!Controller.opcoesByCodigos.keySet().contains(opcaoCodigo)) {
                    throw new InputMismatchException("A opcao escolhida nao corresponde a nunhum codigo listado");
                }
                return Controller.opcoesByCodigos.get(opcaoCodigo);

            } catch (NumberFormatException e) {
                throw new InputMismatchException("A opcao de entranda nao eh um numero inteiro");
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
        System.out.println("Escolha um curso:\n");
        Curso curso = this.escolherCurso(cadastro);
        if (!cadastro.getCurso().contains(curso)) {
            System.out.println("Nao encontramos um curso com essas informacoes");
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
            System.out.println("Nao encontramos um aluno com esse id");
        }

        return aluno;
    }

    @Override
    public void listarAlunos(Cadastro cadastro) {
        /**
         * Retorna todos alunos
         * alunoCSV precisa ser carregado a partir do database (repository)
         * */

        Collection<Aluno> alunos = cadastro.getAluno();

        System.out.println("Todos os alunos cadastrados: ");

        for (Aluno aluno : alunos) {
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

        for (Curso curso : cadastro.getCursoFromAluno(aluno.getId())) {
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
        String ano = getAnoCurso();
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

    private String getAnoCurso() {
        System.out.println("Qual o ano do curso: ");
        return getString();
    }

    private Curso escolherCurso(Cadastro cadastro) {
        String nivel = escolherNivelCurso(cadastro);
        String nome = escolherNomeCurso(cadastro);
        String ano = escolherAnoCurso(cadastro);
        return new Curso(nivel, nome, ano);

    }

    private String escolherNivelCurso(Cadastro cadastro) {
        Set<String> niveis = new TreeSet<>();
        for(Curso c: cadastro.getCurso()){
            niveis.add(c.getNivel());
        }
        System.out.println("Escolha um dos niveis ");
        for(String nivel: niveis) {
            System.out.println(nivel);
        }
        System.out.println("Digite o nivel que deseja: ");
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
        System.out.println("Digite o nome desejado: ");
        return getString();

    }

    private String escolherAnoCurso(Cadastro cadastro) {
        Set<String> anos = new TreeSet<>();
        for(Curso c: cadastro.getCurso()) {
            anos.add(c.getAno());
        }
        System.out.println("Escolha um dos anos: ");
        for(String ano: anos) {
            System.out.println(ano);
        }
        System.out.println("Digite o ano escolhido: ");
        return getString();
    }

    public String getString() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        return str.trim();
    }
}


