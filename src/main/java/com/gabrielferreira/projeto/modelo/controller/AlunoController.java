package com.gabrielferreira.projeto.modelo.controller;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoAutenticarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.enums.Ensino;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
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
		if(dto.getEnsino() != null) {
			aluno.setEnsino(Ensino.valueOf(String.valueOf(dto.getEnsino())));
		}
		if(dto.getSexo() != null) {
			aluno.setSexo(Sexo.valueOf(String.valueOf(dto.getSexo())));
		}
		return toModel(alunoServiceImpl.inserirAluno(aluno));
	}
	
	@PostMapping("/autenticar")
	@ResponseStatus(value = HttpStatus.OK)
	public AlunoDTO autenticar(@Valid @RequestBody AlunoAutenticarDTO dto) {
		Aluno aluno = toEntityAutenticar(dto);
		return toModel(alunoServiceImpl.autenticarAluno(aluno.getEmail(),aluno.getSenha()));
	}
	
	@GetMapping("/lista")
	public List<AlunoDTO> listar() {
		return toCollectionModel(alunoServiceImpl.listar());
	}
	
	@GetMapping("/{idAluno}")
	public ResponseEntity<AlunoDTO> consultar(@PathVariable Long idAluno) {
		Aluno aluno = alunoServiceImpl.consultarPorId(idAluno);
		AlunoDTO alunoDTO = toModel(aluno);
		return ResponseEntity.ok().body(alunoDTO);
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
	
	private List<AlunoDTO> toCollectionModel(List<Aluno> alunos) {
		return alunos.stream().map(aluno -> toModel(aluno)).collect(Collectors.toList());
	}
	
}
