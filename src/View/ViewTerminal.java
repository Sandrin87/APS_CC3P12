package View;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    @Override
    public void adicionaRelacao(Aluno aluno, Curso curso) {
        // TODO Auto-generated method stub

    }

    public Aluno getNewAluno(){
        System.out.println("Entre com os dados do aluno: ");
        String id = getIdAluno();
        String nome = getNomeAluno();
        return new Aluno(id, nome);
    }

    private String getNomeAluno() {
        return getString();
    }

    private String getIdAluno() {
        return getString();
    }
    public String getString() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        return str.trim();
    }
}


