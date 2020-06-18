package com.gabrielferreira.projeto.modelo.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;

@Repository
public interface EstudoRepositorio extends JpaRepository<Estudo,Long>{

	boolean existsByDataInicio(LocalDateTime inicio);
	
	@Query("select e from Estudo e where e.aluno.id = ?1")
	List<Estudo> listagemDeEstudo(Long id);
		
	@Query("select e from Estudo e where e.aluno.id =:idAluno and e.id =:idEstudo")
	public Optional<Estudo> verificarEstudoAluno(Long idAluno,Long idEstudo);
	

}
