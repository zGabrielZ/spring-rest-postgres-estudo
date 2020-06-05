package com.gabrielferreira.projeto.modelo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;

@Repository
public interface EstudoRepositorio extends JpaRepository<Estudo,Long>{

	@Query("select e FROM Estudo e where e.dataInicio =:inicio and e.dataFim =:fim")
	public Page<Estudo> pesquisarEstudo(@Param("inicio")Date inicio,@Param("fim")Date fim,Pageable pageable);
	
	boolean existsByDataInicioAndDataFim(Date inicio,Date fim);
	
	@Query("select e from Estudo e where e.aluno.id = ?1")
	List<Estudo> listagemDeEstudo(Long id);

}
