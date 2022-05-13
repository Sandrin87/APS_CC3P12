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

import Entidades.Aluno;
import Entidades.Cadastro;
import Entidades.Curso;

public class Repository {
	
	private String alunoPath;
	private String cursoPath;
	private String relacaoPath;

	//csv's estão linkados na classe main entao aqui ele não vai puxar
	
	private Cadastro cadastrar;
	
	public Repository(String aAlunoPath, String aCursoPath, String aRelacaoPath) {
		this.alunoPath = aAlunoPath;
		this.cursoPath = aCursoPath;
		this.relacaoPath = aRelacaoPath;
	}
    
	public Cadastro getCadastro(){
		
        this.cadastrar = new Cadastro();
        
        loadAlunos();
        loadCursos();
        
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
				String nivel = palavras[3];
                int ano = Integer.parseInt(palavras[2]);


                Curso curso = new Curso(nome,nivel, ano);
                Aluno aluno = this.cadastrar.getAlunoFromId(idAluno);
                
                cadastrar.addRelacaoAlunoCurso(aluno, curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return this.cadastrar; 

    }

	private void loadAlunos(){

        try (   InputStream is = new FileInputStream(this.alunoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            

            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String id = palavras[0];
                String nome = palavras[1];

                Aluno aluno = new Aluno(id, nome);
                this.cadastrar.addAlunos(aluno);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void loadCursos(){

        try (   InputStream is = new FileInputStream(this.cursoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
        

            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String nivel = palavras[0];
                String nome = palavras[1];
                int ano = Integer.parseInt(palavras[2]);
                		
                Curso curso = new Curso(nivel, nome, ano);
                this.cadastrar.addCurso(curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	
	public void saveCadastro(Cadastro cadastroOutput){
	
		saveAluno(cadastroOutput.getAluno());
		saveCurso(cadastroOutput.getCurso());
		saveRelacoes(cadastroOutput);

    }
	private void saveRelacoes(Cadastro cadastroOutput){

        try(    OutputStream os = new FileOutputStream(this.relacaoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Aluno aluno: cadastroOutput.getAluno()){
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