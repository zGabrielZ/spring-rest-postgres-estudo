package com.gabrielferreira.projeto.modelo.service;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;
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
		
		Aluno aluno = new Aluno(null,"Gabriel Ferreira","gabriel@gmail.com","123",new Date());
		
		Aluno aluno2 = new Aluno(null,"Josue Fernando","fernando@gmail.com","123",new Date());
			
		Disciplina disciplina = new Disciplina(null,"Sistemas Embarcados de Tempo Real","É um sistema de computador "
				+ "(hardware + software) que possui uma função dedicada, geralmente operando dentro de um outro sistema.",
				aluno);
		
		Disciplina disciplina2 = new Disciplina(null,"HTML5","É uma linguagem de marcação,"
				+ " uma tecnologia chave da Internet, originalmente proposto por Opera Software."
				+ "É a quinta versão da linguagem HTML.",
				aluno2);
		
		aluno.getDisciplinas().add(disciplina);
		
		aluno2.getDisciplinas().add(disciplina2);
		
		Estudo estudo = new Estudo(null,new Date(),new Date(),aluno,disciplina,StatusEstudo.INICIO);
		Estudo estudo2 = new Estudo(null,new Date(),new Date(),aluno2,disciplina2,StatusEstudo.INICIO);
		
		aluno.getEstudos().add(estudo);
		disciplina.getEstudos().add(estudo);
		
		aluno2.getEstudos().add(estudo2);
		disciplina2.getEstudos().add(estudo2);
		
		alunoRepositorio.saveAll(Arrays.asList(aluno,aluno2));
		disciplinaRepositorio.saveAll(Arrays.asList(disciplina,disciplina2));
		estudoRepositorio.saveAll(Arrays.asList(estudo,estudo2));
	}

}
