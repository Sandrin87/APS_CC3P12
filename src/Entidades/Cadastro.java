package Entidades;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Cadastro {    
    Map<Aluno, Set<Curso>> aluno = new HashMap<>();//vetor aluno//
	Map<Curso, Set<Aluno>> curso = new HashMap<>();

    Map<String, Aluno> listaAlunoPorId = new HashMap<>();


    public Aluno getAlunoFromId (String id){
        return listaAlunoPorId.get(id);

    }

    public Set<Aluno> getAluno(){ 
        return aluno.keySet();

    }

    public void addAlunos (Aluno _aluno){
        this.aluno.put(_aluno, new TreeSet<>());
        this.listaAlunoPorId.put(_aluno.getId(), _aluno);
    }

    public Set<Curso> getCurso(){
    	return curso.keySet();
    }

    public void addCurso (Curso _curso){
        this.curso.put(_curso, new TreeSet<>());
    }

    public Set<Curso> getCursoFromAluno(String id){
        return aluno.get(new Aluno(id, ""));
    }

    public Set<Aluno> getAlunoFromCurso (Curso _curso){ 
        return curso.get(_curso);
    }

    public void addRelacao(Aluno _aluno, Curso _curso){
    if(_aluno.getId() == null) {
    	this.curso.get(_curso).add(_aluno);
		this.aluno.get(_aluno).add(_curso);
    	} 
    	
    }
}