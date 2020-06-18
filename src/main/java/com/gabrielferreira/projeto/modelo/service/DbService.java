package com.gabrielferreira.projeto.modelo.service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;
import com.gabrielferreira.projeto.modelo.entidade.enums.Ensino;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.modelo.entidade.enums.StatusEstudo;
import com.gabrielferreira.projeto.modelo.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.modelo.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.modelo.repositorio.EstudoRepositorio;

@Service
public class DbService implements CommandLineRunner{

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@Autowired
	private EstudoRepositorio estudoRepositorio;
	
	@Override
	public void run(String... args) throws Exception {
		
		Aluno aluno = new Aluno(null,"Gabriel Ferreira","gabriel@gmail.com","123",new Date(),
				new Date(),Ensino.SUPERIOR,Sexo.MASCULINO);
		
		Aluno aluno2 = new Aluno(null,"Josue Fernando","fernando@gmail.com","123",new Date(),new Date(),
				Ensino.FUNDAMENTAL,Sexo.MASCULINO);
		
		Aluno aluno3 = new Aluno(null,"Marcos Pereira","marcos@gmail.com","123",new Date(),new Date(),
				Ensino.MÉDIO,Sexo.MASCULINO);
		Aluno aluno4 = new Aluno(null,"Juliana Martins","juliana@gmail.com","123",new Date(),new Date(),
				Ensino.MÉDIO,Sexo.FEMININO);
		
		Aluno aluno5 = new Aluno(null,"Cristiano Júnior","cristiano@gmail.com","123",new Date(),new Date(),
				Ensino.MÉDIO,Sexo.MASCULINO);
		
		Aluno aluno6 = new Aluno(null,"Júlia Rodrigues","julia@gmail.com","123",new Date(),new Date(),
				Ensino.SUPERIOR,Sexo.FEMININO);
		
		Aluno aluno7 = new Aluno(null,"Aline Silva","aline@gmail.com","123",new Date(),new Date(),
				Ensino.SUPERIOR,Sexo.FEMININO);
		
		Aluno aluno8 = new Aluno(null,"Mariana Oliveira","mariana@gmail.com","123",new Date(),new Date(),
				Ensino.MÉDIO,Sexo.FEMININO);
		
		Aluno aluno9 = new Aluno(null,"Maria Medeiros","maria@gmail.com","123",new Date(),new Date(),
				Ensino.FUNDAMENTAL,Sexo.FEMININO);
		
		Aluno aluno10 = new Aluno(null,"Maria Juliana ","mariaJu@gmail.com","123",new Date(),new Date(),
				Ensino.MÉDIO,Sexo.FEMININO);
		
		Aluno aluno11 = new Aluno(null,"Jéssica Suzano","je@gmail.com","123",new Date(),new Date(),
				Ensino.SUPERIOR,Sexo.FEMININO);
		
		Aluno aluno12 = new Aluno(null,"Marcos Luiz da Silva","marcao@gmail.com","123",new Date(),new Date(),
				Ensino.SUPERIOR,Sexo.MASCULINO);
			
		Disciplina disciplina = new Disciplina(null,"Sistemas Embarcados de Tempo Real","É um sistema de computador "
				+ "(hardware + software) que possui uma função dedicada, geralmente operando dentro de um outro sistema.",
				aluno);
		
		Disciplina disciplina2 = new Disciplina(null,"HTML5","É uma linguagem de marcação,"
				+ " uma tecnologia chave da Internet, originalmente proposto por Opera Software."
				+ "É a quinta versão da linguagem HTML.",
				aluno2);
		
		Disciplina disciplina3 = new Disciplina(null,"Programação C","É uma linguagem estruturada, imperativa, procedural",
				aluno);
		
		aluno.getDisciplinas().add(disciplina);
		aluno.getDisciplinas().add(disciplina3);
		
		aluno2.getDisciplinas().add(disciplina2);
		
		Estudo estudo = new Estudo(null,LocalDateTime.now(),null,aluno,disciplina,StatusEstudo.INICIO,2);
		Estudo estudo2 = new Estudo(null,LocalDateTime.now(),null,aluno2,disciplina2,StatusEstudo.INICIO,2);
		
		aluno.getEstudos().add(estudo);
		disciplina.getEstudos().add(estudo);
		
		aluno2.getEstudos().add(estudo2);
		disciplina2.getEstudos().add(estudo2);
		
		alunoRepositorio.saveAll(Arrays.asList(aluno,aluno2,aluno3,aluno4,aluno5,aluno6,aluno7,aluno8,
				aluno9,aluno10,aluno11,aluno12));
		disciplinaRepositorio.saveAll(Arrays.asList(disciplina,disciplina2,disciplina3));
		estudoRepositorio.saveAll(Arrays.asList(estudo,estudo2));
	}

}
