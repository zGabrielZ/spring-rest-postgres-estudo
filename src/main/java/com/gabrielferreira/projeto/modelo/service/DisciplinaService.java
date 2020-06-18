package com.gabrielferreira.projeto.modelo.service;

import java.util.List;

import com.gabrielferreira.projeto.modelo.entidade.Disciplina;

public interface DisciplinaService {

	Disciplina inserir(Disciplina disciplina,Long idAluno);
	
	Disciplina atualizar(Long idDisciplina,Disciplina disciplina,Long idAluno);
	
	void deletar(Long idDisciplina);
	
	List<Disciplina> listagem(Long idAluno);
	
	Disciplina consultarPorId(Long idDisciplina,Long idAluno);
	
	List<Disciplina> consultarNome(Disciplina disciplina,Long idAluno);
	
	void validarNome(String nome,Long idAluno);
	
	void validarNomeAtualizado(String nome,Long idAluno,Long idDisciplina);
}
