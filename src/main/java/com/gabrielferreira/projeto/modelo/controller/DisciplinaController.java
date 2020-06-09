package com.gabrielferreira.projeto.modelo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.dto.DisciplinaAtualizarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.DisciplinaDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.DisciplinaInserirDTO;
import com.gabrielferreira.projeto.modelo.service.impl.AlunoServiceImpl;
import com.gabrielferreira.projeto.modelo.service.impl.DisciplinaServiceImpl;

@RestController
@RequestMapping("/alunos")
public class DisciplinaController {

	@Autowired
	private DisciplinaServiceImpl disciplinaServiceImpl;
	
	@Autowired
	private AlunoServiceImpl alunoServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/disciplinas/inserir")
	@ResponseStatus(value = HttpStatus.CREATED)
	public DisciplinaDTO inserir(@Valid @RequestBody DisciplinaInserirDTO disciplinaInserirDTO) {
		Disciplina disciplina = toEntityInserir(disciplinaInserirDTO);
		return toModel(disciplinaServiceImpl.inserir(disciplina,disciplina.getAluno().getId()));
	}
	
	@PutMapping("/{idAluno}/disciplinas/{idDisciplina}/atualizar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public DisciplinaDTO atualizar(@Valid @RequestBody DisciplinaAtualizarDTO disciplinaAtualizarDTO ,
			@PathVariable Long idAluno,
			@PathVariable Long idDisciplina) {
		Aluno aluno = disciplinaServiceImpl.consultarPorId(idDisciplina, idAluno).getAluno();
		Disciplina disciplina = toEntityAtualizar(disciplinaAtualizarDTO);
		disciplina.setAluno(aluno);
		aluno.getDisciplinas().add(disciplina);
		return toModel(disciplinaServiceImpl.atualizar(idDisciplina, disciplina, idAluno));
	}
	
	@GetMapping("/{idAluno}/disciplinas")
	public List<DisciplinaDTO> listar(@PathVariable Long idAluno) {
		Aluno aluno = alunoServiceImpl.consultarPorId(idAluno);
		return toCollectionModel(disciplinaServiceImpl.listagem(aluno.getId()));
	}
	
	@GetMapping("/{idAluno}/disciplinas/{idDisciplina}")
	public ResponseEntity<DisciplinaDTO> consultarPorId(@PathVariable Long idAluno,
			@PathVariable Long idDisciplina) {
		Disciplina disciplina = disciplinaServiceImpl.consultarPorId(idDisciplina, idAluno);
		disciplina.setAluno(alunoServiceImpl.consultarPorId(idAluno));
		DisciplinaDTO disciplinaDTO = toModel(disciplina);
		return ResponseEntity.ok().body(disciplinaDTO);
	}
	
	@GetMapping("{idAluno}/disciplinas/buscar-nome-disciplina")
	public ResponseEntity<List<DisciplinaDTO>> consulta(@RequestParam(value = "nome",required = false) String nome,
			@PathVariable Long idAluno){
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(nome);
		Aluno aluno = alunoServiceImpl.consultarPorId(idAluno);
		disciplina.setAluno(aluno);
		List<Disciplina> disciplinas = disciplinaServiceImpl.consultarNome(disciplina, aluno.getId());
		return ResponseEntity.ok().body(toCollectionModel(disciplinas));
	}
	
	@DeleteMapping("/{idAluno}/disciplinas/{idDisciplina}")
	public ResponseEntity<Void> deletar(@PathVariable Long idDisciplina,
			@PathVariable Long idAluno){
		Disciplina disciplina = disciplinaServiceImpl.consultarPorId(idDisciplina, idAluno);
		disciplina.setAluno(alunoServiceImpl.consultarPorId(idAluno));
		disciplinaServiceImpl.deletar(idDisciplina);
		return ResponseEntity.noContent().build();
	}
	
	private DisciplinaDTO toModel(Disciplina disciplina) {
		return modelMapper.map(disciplina,DisciplinaDTO.class);
	}
	
	private Disciplina toEntityInserir(DisciplinaInserirDTO dto) {
		return modelMapper.map(dto,Disciplina.class);
	}
	
	private Disciplina toEntityAtualizar(DisciplinaAtualizarDTO dto) {
		return modelMapper.map(dto,Disciplina.class);
	}
	
	private List<DisciplinaDTO> toCollectionModel(List<Disciplina> disciplinas) {
		return disciplinas.stream()
				.map(disciplina -> toModel(disciplina))
				.collect(Collectors.toList());
	}
	
	
}
