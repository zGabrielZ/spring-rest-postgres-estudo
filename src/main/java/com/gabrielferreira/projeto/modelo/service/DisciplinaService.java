package com.gabrielferreira.projeto.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gabrielferreira.projeto.modelo.entidade.Disciplina;

public interface DisciplinaService {

	Disciplina inserir(Disciplina disciplina,Long idAluno);
	
	Disciplina atualizar(Long idDisciplina,Disciplina disciplina,Long idAluno);
	
	void deletar(Long idDisciplina);
	
	List<Disciplina> listagem(Long idAluno);
	
	Disciplina consultarPorId(Long idDisciplina,Long idAluno);
	
	Page<Disciplina> consultarNome(String nome,Integer pagina,Integer linhasPorPagina,
			String ordernarPor,String direcao,Long idAluno);
	
	void validarNome(String nome,Long idAluno);
}
