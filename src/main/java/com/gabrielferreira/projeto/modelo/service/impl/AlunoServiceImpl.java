package com.gabrielferreira.projeto.modelo.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.modelo.service.AlunoService;
import com.gabrielferreira.projeto.modelo.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.modelo.service.exceptions.ErroAutenticacaoException;
import com.gabrielferreira.projeto.modelo.service.exceptions.RegraDeNegocioException;

@Service
public class AlunoServiceImpl implements AlunoService{

	@Autowired
	private AlunoRepositorio alunoRepositorio;

	@Override
	public void validarEmail(String email) {
		boolean validar = alunoRepositorio.existsByEmail(email);
		if(validar) {
			throw new RegraDeNegocioException("Já existe este aluno cadastrado, por favor tente novamente");
		}
	}

	@Override
	public Aluno autenticarAluno(String email, String senha) {
		Optional<Aluno> aluno = alunoRepositorio.findByEmail(email);
		if(!aluno.isPresent()) {
			throw new ErroAutenticacaoException("Aluno não encontrado para o email informado");
		}
		
		if(!aluno.get().getSenha().equals(senha)){
			throw new ErroAutenticacaoException("Senha inválida");
		}
		
		return aluno.get();
	}

	@Override
	@Transactional
	public Aluno inserirAluno(Aluno aluno) {
		validarEmail(aluno.getEmail());
		aluno.setDataCadastro(new Date());
		return alunoRepositorio.save(aluno);
	}

	@Override
	public Aluno consultarPorId(Long idAluno) {
		Optional<Aluno> aluno = alunoRepositorio.findById(idAluno);
		if(!aluno.isPresent()) {
			throw new EntidadeNotFoundException("Aluno não encontrado");
		}
		
		return aluno.get();
	}

	@Override
	public List<Aluno> listar() {
		return alunoRepositorio.findAll();
	}
	
}
