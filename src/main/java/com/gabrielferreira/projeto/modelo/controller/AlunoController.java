package com.gabrielferreira.projeto.modelo.controller;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoAutenticarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoInserirDTO;
import com.gabrielferreira.projeto.modelo.service.impl.AlunoServiceImpl;

@RestController
@RequestMapping("/alunos/login")
public class AlunoController {
	
	@Autowired
	private AlunoServiceImpl alunoServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AlunoDTO inserir(@Valid @RequestBody AlunoInserirDTO dto) {
		Aluno aluno = toEntityInserir(dto);
		return toModel(alunoServiceImpl.inserirAluno(aluno));
	}
	
	@PostMapping("/autenticar")
	@ResponseStatus(value = HttpStatus.OK)
	public AlunoDTO autenticar(@Valid @RequestBody AlunoAutenticarDTO dto) {
		Aluno aluno = toEntityAutenticar(dto);
		return toModel(alunoServiceImpl.autenticarAluno(aluno.getEmail(),aluno.getSenha()));
	}
	
	private AlunoDTO toModel(Aluno aluno) {
		return modelMapper.map(aluno,AlunoDTO.class);
	}
	
	private Aluno toEntityInserir(AlunoInserirDTO dto) {
		return modelMapper.map(dto,Aluno.class);
	}
	
	private Aluno toEntityAutenticar(AlunoAutenticarDTO dto) {
		return modelMapper.map(dto,Aluno.class);
	}
	
}
