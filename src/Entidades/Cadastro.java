package Entidades;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
<<<<<<< HEAD

public class Cadastro {
    
    Map<Aluno, Set<Curso>> alunos = new TreeMap<>();
	Map<Curso, Set<Aluno>> cursos = new TreeMap<>();
=======
import java.util.Objects;


public class Cadastro {
    
    Map<Aluno, Set<Curso>> aluno = new TreeMap<>();//vetor aluno//
	Map<Curso, Set<Aluno>> curso = new TreeMap<>();
>>>>>>> bdfb6aef3b34e10996f40a12e0922c0ee0ff3388

    Map<String, Aluno> listaAlunoPorId = new TreeMap<>();


    public Aluno getAlunoFromId (String id){
        return listaAlunoPorId.get(id);

    }

    public Set<Aluno> getAluno(){ 
<<<<<<< HEAD
        return alunos.keySet();
=======
        return aluno.keySet();
>>>>>>> bdfb6aef3b34e10996f40a12e0922c0ee0ff3388

    }

    public void addAlunos (Aluno aluno){
<<<<<<< HEAD
        this.alunos.put(aluno, new TreeSet<>());
        this.listaAlunoPorId.put(aluno.getId(), aluno);
    }

    public Set<Curso> getCurso(){
        return cursos.keySet();
    }

    public void addCurso (Curso curso){
        this.cursos.put(curso, new TreeSet<>());
    }

    public Set<Curso> getCursoFromAluno(String id){
        return alunos.get(new Aluno(id, ""));
    }


    public Set<Aluno> getAlunoFromCurso (Curso curso){ 
        return cursos.get(curso);
    }

    public void addRelacaoAlunoCurso(Aluno aluno, Curso curso){
        this.cursos.get(curso).add(aluno);
        this.alunos.get(aluno).add(curso);
=======
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
>>>>>>> bdfb6aef3b34e10996f40a12e0922c0ee0ff3388
    }


}