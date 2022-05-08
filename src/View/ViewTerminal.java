package View;

import java.util.*;

import Controller.Controller;
import Controller.Opcao;
import Entidades.Aluno;
import Entidades.Curso;
import Repository.Repository;

public class ViewTerminal implements View {

    private Repository repositorio;

    public ViewTerminal(Repository repositorioCarregado) {

        this.repositorio = repositorioCarregado;
    }

    @Override
    public Opcao menu() {
        try {

            System.out.println("Escolha uma das opções");
            for (Opcao o : Opcao.values()) {
                System.out.println("" + o.codigo + " - " + o.descricao);

            }

            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);
            try {
                String entrada = in.nextLine();
                int opcaoCodigo = Integer.parseInt(entrada);

                if (!Controller.opcoesByCodigos.keySet().contains(opcaoCodigo)) {
                    throw new InputMismatchException("A opção escolhida não corresponde a nunhum código listado");
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
    public Curso getCursoFromLista(Repository repository) {
        System.out.println("Escolha um curso");
        Curso curso = this.escolherCurso(repository);
        if (!repository.getCursos().contains(curso)) {
            System.out.println("Não encontramos um curso com essas informações");
            return null;
        }
        return curso;
    }

    @Override
    public Aluno getAlunoFromLista(Repository repository) {
        System.out.println("Entre com o id do Aluno: ");
        listarAlunos(repository);
        System.out.println("Entre com o id do Aluno: ");
        String id = getString();

        Aluno aluno = repository.getAlunoFronId(id);
        if(aluno==null){
            System.out.println("Não encontramos um aluno com esse id");
        }

        return aluno;
    }

    @Override
    public void listarAlunos(Repository repository) {
        /**
         * Retorna todos alunos
         * alunoCSV precisa ser carregado a partir do database (repository)
         * */

        Collection<Aluno> alunos = repositorio.getAllAlunos();

        System.out.println("Todos os alunos cadastrados: ");

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    @Override
    public void listarCursos(Repository repository) {
        System.out.println("Todos os Cursos cadastrados: ");
        for (Curso curso : repositorio.getAllCursos()) {
            System.out.println(curso);
        }
    }

    @Override
    public void listarCursosFromAluno(Repository repository, Aluno aluno) {
        System.out.println("Todos os cursos do Aluno: " + aluno.getNome());

        for (Curso curso : repository.getCursoFromAluno(aluno)) {
            System.out.println(curso);
        }

    }

    @Override
    public void listarAlunosFromCursos(Repository repository, Curso curso) {
        /**
         * Retorna uma lista de alunos que possuem o curso fornecido
         * */
        System.out.println("Todos os alunos do curso: " + curso.getNome());

        for (Aluno aluno : repository.getAlunosFromCurso(curso)) {
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
        System.out.println("Qual o nível do curso: ");
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

    private Curso escolherCurso(Repository repository) {
        String nivel = escolherNivelCurso(repository);
        String nome = escolherNomeCurso(repository);
        int ano = escolherAnoCurso(repository);
        return new Curso(nivel, nome, ano);

    }

    private String escolherNivelCurso(Repository repository) {
        Set<String> niveis = new TreeSet<>();
        for(Curso c: repository.getCursos()){
            niveis.add(c.getNivel());
        }
        System.out.println("Escolha um dos níveis: ");
        for(String nivel: niveis) {
            System.out.println(nivel);
        }
        System.out.println("Digite o nível escolhido: ");
        return getString();
    }

    private String escolherNomeCurso(Repository repository) {
        Set<String> nomes = new TreeSet<>();
        for(Curso c: repository.getCursos()){
            nomes.add(c.getNome());
        }
        System.out.println("Escolha um dos nomes: ");
        for(String nome: nomes) {
            System.out.println(nome);
        }
        System.out.println("Digite o nome escolhido: ");
        return getString();

    }

    private int escolherAnoCurso(Repository repository) {
        Set<Integer> anos = new TreeSet<>();
        for(Curso c: repository.getCursos()) {
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
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        return str.trim();
    }
    
    public int getInt() {
        Scanner in = new Scanner(System.in);
        int inteiro = in.nextInt();
        return inteiro;
    }
}


