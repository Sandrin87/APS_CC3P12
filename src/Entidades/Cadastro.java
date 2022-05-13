package Entidades;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;


public class Cadastro {
    
    Map<Aluno, Set<Curso>> aluno = new TreeMap<>();//vetor aluno//
	Map<Curso, Set<Aluno>> curso = new TreeMap<>();

    Map<String, Aluno> listaAlunoPorId = new TreeMap<>();


    public Aluno getAlunoFromId (String id){
        return listaAlunoPorId.get(id);

    }

    public Set<Aluno> getAluno(){ 
        return aluno.keySet();

    }

    public void addAlunos (Aluno aluno){
        this.aluno.put(aluno, new TreeSet<>());
        this.listaAlunoPorId.put(aluno.getId(aluno));
    }

    public Set<Curso> getCurso(){
        return curso.keySet();
    }

    public void addCurso (Curso curso){
        this.curso.put(curso, new TreeSet<>());
    }

    public Set<Curso> getCursoFromAluno(String id){
        return aluno.get(new Aluno(id, ""));
    }

//Curso é objeto, o curso ele é a variável que vai armazenar o objeto// //o objeto tem que ter uma class com o msm nome do objeto//

    public Set<Aluno> getAlunoFromCurso (Curso curso){ 
        return curso.getCurso(curso);
    }

    public void addRelacaoAlunoCurso(Aluno aluno, Curso curso){
        this.curso.get(curso).add(aluno);
        this.aluno.get(aluno).add(curso);
    }


}