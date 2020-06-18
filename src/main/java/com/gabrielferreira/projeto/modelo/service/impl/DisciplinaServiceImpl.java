package com.gabrielferreira.projeto.modelo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.modelo.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.modelo.service.DisciplinaService;
import com.gabrielferreira.projeto.modelo.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.modelo.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.modelo.service.exceptions.RegraDeNegocioException;

@Service
public class DisciplinaServiceImpl implements DisciplinaService{
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;

	@Override
	@Transactional
	public Disciplina inserir(Disciplina disciplina,Long idAluno) {
		validarNome(disciplina.getNome(),idAluno);
		Aluno aluno = alunoRepositorio.findById(idAluno)
				.orElseThrow(()-> new EntidadeNotFoundException("Aluno não encontrado"));
		disciplina.setAluno(aluno);
		return disciplinaRepositorio.save(disciplina);
	}

	@Override
	public Disciplina atualizar(Long idDisciplina, Disciplina disciplina,Long idAluno) {
		try {
			validarNomeAtualizado(disciplina.getNome(), idAluno, idDisciplina);
			Disciplina entidade = disciplinaRepositorio.getOne(idDisciplina);
			updateData(entidade,disciplina);
			return disciplinaRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException("Disciplina não encontrada");
		}
	}

	private void updateData(Disciplina entidade, Disciplina disciplina) {
		entidade.setNome(disciplina.getNome());
		entidade.setDescricao(disciplina.getDescricao());
	}

	@Override
	public void deletar(Long idDisciplina) {
		try {
			disciplinaRepositorio.deleteById(idDisciplina);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Disciplina não encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com sua lista de estudos");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Disciplina> listagem(Long idAluno) {
		return disciplinaRepositorio.getDisciplinas(idAluno);
	}

	@Override
	public Disciplina consultarPorId(Long idDisciplina,Long idAluno) {
		Aluno aluno = alunoRepositorio.findById(idAluno)
				.orElseThrow(()-> new EntidadeNotFoundException("Aluno não encontrado"));
		Optional<Disciplina> disciplina = disciplinaRepositorio.verificarDisciplinaAluno(aluno.getId(), idDisciplina);
		if(!disciplina.isPresent()) {
			throw new EntidadeNotFoundException("Disciplina não encontrado");
		}
		
		return disciplina.get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Disciplina> consultarNome(Disciplina disciplina,Long idAluno) {
		Aluno aluno = alunoRepositorio.findById(idAluno)
				.orElseThrow(()-> new EntidadeNotFoundException("Aluno não encontrado"));
		aluno.getDisciplinas().add(disciplina);
		return disciplinaRepositorio.pesquisarDisciplina(disciplina.getNome(),aluno.getId());
	}

	@Override
	public void validarNome(String nome,Long idAluno) {
		Optional<Disciplina> disciplinaNome = disciplinaRepositorio.existeNome(nome, idAluno);
		if(disciplinaNome.isPresent()) {
			throw new RegraDeNegocioException("Já existe esta disciplina cadastrada, por favor tente novamente");
		}
	}
	
	@Override
	public void validarNomeAtualizado(String nome,Long idAluno,Long idDisciplina) {
		Optional<Disciplina> disciplinaNome = disciplinaRepositorio.existeNomeAtualizado(nome, idAluno, idDisciplina);
		if(disciplinaNome.isPresent()) {
			throw new RegraDeNegocioException("Já existe esta disciplina cadastrada, por favor tente novamente");
		}
	}
}
