package com.gabrielferreira.projeto.modelo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno,Long>{

	boolean existsByEmail(String email);
	
	Optional<Aluno> findByEmail(String email);

}
