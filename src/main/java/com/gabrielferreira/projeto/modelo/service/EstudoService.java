package com.gabrielferreira.projeto.modelo.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;

public interface EstudoService {

	Estudo inserir(Estudo estudo);
	
	void paralisar(Long id);
	
	void terminado(Long id);
	
	void reinicar(Long id);
	
	void deletarEstudo(Long id);
	
	List<Estudo> listagem(Long id);
	
	Estudo consultarPorId(Long id);
	
	Page<Estudo> consultarEstudo(Date inicio,Date fim,Integer pagina,Integer linhasPorPagina,
			String ordernarPor,String direcao);
	
	void buscarDatas(Date inicio,Date fim);
	
	void validarDatas(Date inicio,Date fim);
	
	void buscarDisciplinaAluno(Long disciplinaId,Long  alunoId);
	
}
