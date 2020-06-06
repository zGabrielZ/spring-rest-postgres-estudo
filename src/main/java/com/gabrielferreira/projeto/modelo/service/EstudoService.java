package com.gabrielferreira.projeto.modelo.service;

import java.util.Date;
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
	
	void buscarDatas(Date inicio,Date fim);
	
	void validarDatas(Date inicio,Date fim);
	
	void buscarDisciplinaAluno(Long disciplinaId,Long  alunoId);
	
}
