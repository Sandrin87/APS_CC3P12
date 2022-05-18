package Repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import java.util.Set;

import Entidades.Cadastro;
import Entidades.Curso;
import Entidades.Aluno;
import Controller.Controller;

public class Repository {
	
	private String alunoPath;
	private String cursoPath;
	private String relacaoPath;

	//csv's estão linkados na classe main então aqui ele só vai puxar
	
	private Cadastro cadastrarInput;
	
	public Repository(String aAlunoPath, String aCursoPath, String aRelacaoPath) {
		this.alunoPath = aAlunoPath;
		this.cursoPath = aCursoPath;
		this.relacaoPath = aRelacaoPath;
	}
    
	public Cadastro getCadastro(){
		
        this.cadastrarInput = new Cadastro();
        
        loadAluno();
        loadCurso();
        
        return loadRelacoes();
    }

	private Cadastro loadRelacoes(){
		
        try (   InputStream is = new FileInputStream(this.relacaoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String idAluno = palavras[0];
                String nome = palavras[1];
				String ano = palavras[2];
				String nivel = palavras[3];
				
				Curso curso = new Curso(nivel, nome, ano);
				
                Aluno aluno = this.cadastrarInput.getAlunoFromId(idAluno);
                
                this.cadastrarInput.addRelacao(aluno, curso);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return this.cadastrarInput; 

    }

	private void loadAluno(){

        try (   InputStream is = new FileInputStream(this.alunoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String id = palavras[0];
                String nome = palavras[1];
                
                Aluno aluno = new Aluno(id, nome);
                this.cadastrarInput.addAluno(aluno);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void loadCurso(){

        try (   InputStream is = new FileInputStream(this.cursoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            while((linha = br.readLine()) != null){
            	
            	String[] palavras = linha.split(",");
            	
                String nome = palavras[0];
                String nivel = palavras[1];
                String ano = palavras[2];
                
                Curso curso = new Curso(nivel, nome, ano);
                this.cadastrarInput.addCurso(curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	
	public void saveCadastro(Cadastro cadastroOutput){
		
		saveAluno(cadastroOutput.getAlunos());
		saveCurso(cadastroOutput.getCursos());
		saveRelacoes(cadastroOutput);

    }
	private void saveRelacoes(Cadastro cadastroOutput){

        try(    OutputStream os = new FileOutputStream(this.relacaoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Aluno aluno: cadastroOutput.getAlunos()){
                for(Curso curso: cadastroOutput.getCursoFromAluno(aluno.getId())){
                    pw.println(aluno.getId()+","+curso.getNome()+","+curso.getAno()+","+curso.getNivel());
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void saveAluno(Set<Aluno> alunoOutput){

        try(    OutputStream os = new FileOutputStream(this.alunoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Aluno aluno: alunoOutput){
                pw.println(aluno.getId()+","+aluno.getNome());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }


	private void saveCurso(Set<Curso> cursoOutput){

        try(    OutputStream os = new FileOutputStream(this.cursoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Curso curso: cursoOutput){
                pw.println(curso.getNome()+","+curso.getNivel()+","+curso.getAno());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}