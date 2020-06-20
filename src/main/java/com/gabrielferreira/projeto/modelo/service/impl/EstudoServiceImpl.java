package com.gabrielferreira.projeto.modelo.service.impl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;
import com.gabrielferreira.projeto.modelo.entidade.enums.StatusEstudo;
import com.gabrielferreira.projeto.modelo.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.modelo.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.modelo.repositorio.EstudoRepositorio;
import com.gabrielferreira.projeto.modelo.service.EstudoService;
import com.gabrielferreira.projeto.modelo.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.modelo.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.modelo.service.exceptions.RegraDeNegocioException;

@Service
public class EstudoServiceImpl implements EstudoService{

	@Autowired
	private EstudoRepositorio estudoRepositorio;
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Override
	@Transactional
	public Estudo inserir(Estudo estudo) {
		buscarDatas(estudo.getDataInicio(),estudo.getAluno().getId());
		validarDatas(estudo.getDataInicio());
		Optional<Aluno> aluno = alunoRepositorio.findById(estudo.getAluno().getId());
		if(!aluno.isPresent()){
			throw new EntidadeNotFoundException("Aluno não encontrado");
		}
		
		Optional<Disciplina> disciplina = disciplinaRepositorio.findById(estudo.getDisciplina().getId());
		if(!disciplina.isPresent()){
			throw new EntidadeNotFoundException("Disciplina não encontrado");
		}
		
		buscarDisciplinaAluno(estudo.getDisciplina().getId(),estudo.getAluno().getId());
		
		estudo.setStatusEstudo(StatusEstudo.INICIO);
		estudo.setAluno(aluno.get());
		aluno.get().getEstudos().add(estudo);
		estudo.setDisciplina(disciplina.get());
		disciplina.get().getEstudos().add(estudo);
		
		return estudoRepositorio.save(estudo);
		

	}

	@Override
	public void paralisar(Long id) {
		Optional<Estudo> estudo = estudoRepositorio.findById(id);
		if(!estudo.isPresent()) {
			throw new EntidadeNotFoundException("Estudo não encontrado");
		}
		
		estudo.get().paralisada();
		
		estudoRepositorio.save(estudo.get());
	}

	@Override
	public void terminado(Long id) {
		Optional<Estudo> estudo = estudoRepositorio.findById(id);
		if(!estudo.isPresent()) {
			throw new EntidadeNotFoundException("Estudo não encontrado");
		}
		
		estudo.get().finalizar();
		
		estudoRepositorio.save(estudo.get());
	}

	@Override
	@Transactional
	public void deletarEstudo(Long id) {
		try {
			estudoRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Estudo não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com sua lista de disciplinas e alunos");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estudo> listagem(Long id) {
		return estudoRepositorio.listagemDeEstudo(id);
	}

	@Override
	public Estudo consultarPorId(Long idEstudo,Long idAluno) {
		Aluno aluno = alunoRepositorio.findById(idAluno)
				.orElseThrow(()-> new EntidadeNotFoundException("Aluno não encontrado"));
		Optional<Estudo> estudo = estudoRepositorio.verificarEstudoAluno(aluno.getId(), idEstudo);
		if(!estudo.isPresent()) {
			throw new EntidadeNotFoundException("Estudo não encontrado");
		}
		
		return estudo.get();
	}

	@Override
	public void buscarDatas(LocalDateTime inicio,Long idAluno) {
		Optional<Estudo> estudo = estudoRepositorio.verificarHoraEstudoAluno(idAluno,inicio);
		if(estudo.isPresent()) {
			throw new RegraDeNegocioException("Já existe essa data cadastrada, por favor tente novamente");
		}
	}

	@Override
	public void reinicar(Long id) {
		Optional<Estudo> estudo = estudoRepositorio.findById(id);
		if(!estudo.isPresent()) {
			throw new EntidadeNotFoundException("Estudo não encontrado");
		}
		
		estudo.get().reinicio();
		
		estudoRepositorio.save(estudo.get());
	}

	@Override
	public void validarDatas(LocalDateTime inicio) {
		if (inicio.isBefore(LocalDateTime.now())) {

			throw new RegraDeNegocioException("A data inicial deve ser futura");

		}
	}

	@Override
	public void buscarDisciplinaAluno(Long idDisciplina,Long idAluno) {
		Optional<Disciplina> disciplina = disciplinaRepositorio.verificarDisciplinaAluno(idAluno, idDisciplina);
		if(!disciplina.isPresent()) {
			throw new RegraDeNegocioException("Não podemos inserir, pois esta disciplina não esta cadastrada no aluno");
		}
	}
	
}
