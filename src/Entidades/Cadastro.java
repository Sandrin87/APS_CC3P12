package Entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Cadastro {    
    Map<Aluno, Set<Curso>> alunos = new TreeMap<>();//vetor aluno//
	Map<Curso, Set<Aluno>> cursos = new TreeMap<>();

    Map<String, Aluno> listaDeAlunos = new TreeMap<>();


    public Aluno getAlunoFromId (String id){
        return listaDeAlunos.get(id);

    }

    public Set<Aluno> getAlunos(){ 
        return alunos.keySet();

    }

    public void addAluno(Aluno _aluno){
        this.alunos.put(_aluno, new TreeSet<>());
        this.listaDeAlunos.put(_aluno.getId(), _aluno);
    }

    public Set<Curso> getCursos(){
    	return cursos.keySet();
    }

    public void addCurso (Curso _curso){
        this.cursos.put(_curso, new TreeSet<>());
    }

    public Set<Curso> getCursoFromAluno(String id){
        return alunos.get(new Aluno(id, ""));
    }

    public Set<Aluno> getAlunoFromCurso(Curso _curso){ 
        return cursos.get(_curso);
    }

    public void addRelacao(Aluno _aluno, Curso _curso){
    	this.cursos.get(_curso).add(_aluno);
		this.alunos.get(_aluno).add(_curso);
    }
}