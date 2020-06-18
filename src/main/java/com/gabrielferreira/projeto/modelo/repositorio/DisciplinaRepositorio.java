package com.gabrielferreira.projeto.modelo.repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina,Long>{

	@Query("select d from Disciplina d where d.nome =:nome and d.aluno.id =:id")
	Optional<Disciplina> existeNome(@Param("nome")String nome,@Param("id")Long id);
	
	@Query("select d from Disciplina d where d.nome =:nome and d.aluno.id =:idAluno and d.id <> :idDisciplina")
	Optional<Disciplina> existeNomeAtualizado(@Param("nome")String nome,@Param("idAluno")Long id,
			@Param("idDisciplina")Long idDisciplina);
	
	@Query("select d FROM Disciplina d where d.nome like %:nome% and d.aluno.id =:idAluno")
	public List<Disciplina> pesquisarDisciplina(@Param("nome")String nome,Long idAluno);
	
	@Query("select d from Disciplina d where d.aluno.id = ?1")
	public List<Disciplina> getDisciplinas(Long idAluno);
	
	@Query("select d from Disciplina d where d.aluno.id =:idAluno and d.id =:idDisciplina")
	public Optional<Disciplina> verificarDisciplinaAluno(Long idAluno,Long idDisciplina);
}
