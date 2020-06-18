package com.gabrielferreira.projeto.modelo.service;

import java.time.LocalDateTime;
import java.util.List;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;

public interface EstudoService {

	Estudo inserir(Estudo estudo);
	
	void paralisar(Long id);
	
	void terminado(Long id);
	
	void reinicar(Long id);
	
	void deletarEstudo(Long id);
	
	List<Estudo> listagem(Long id);
	
	Estudo consultarPorId(Long idEstudo,Long idAluno);
	
	void buscarDatas(LocalDateTime inicio);
	
	void validarDatas(LocalDateTime inicio);
	
	void buscarDisciplinaAluno(Long disciplinaId,Long  alunoId);

	
}
