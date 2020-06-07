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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Estudo;
import com.gabrielferreira.projeto.modelo.entidade.dto.EstudoConsultaDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.EstudoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.EstudoInserirDTO;
import com.gabrielferreira.projeto.modelo.service.impl.AlunoServiceImpl;
import com.gabrielferreira.projeto.modelo.service.impl.EstudoServiceImpl;

@RestController
@RequestMapping("/estudos")
public class EstudoController {
	
	@Autowired
	private EstudoServiceImpl estudoServiceImpl;
	
	@Autowired
	private AlunoServiceImpl alunoServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EstudoDTO inserir(@Valid @RequestBody EstudoInserirDTO dto) {
		Estudo estudo = toEntityInserir(dto);
		return toModel(estudoServiceImpl.inserir(estudo));
	}
	
	@PutMapping("/{estudoId}/paralisada")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void paralisar(@PathVariable Long estudoId) {
		estudoServiceImpl.paralisar(estudoId);
	}
	
	@PutMapping("/{estudoId}/finalizar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long estudoId) {
		estudoServiceImpl.terminado(estudoId);
	}
	
	@PutMapping("/{estudoId}/reiniciar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void reiniciar(@PathVariable Long estudoId) {
		estudoServiceImpl.reinicar(estudoId);
	}
	
	@DeleteMapping("/{idAluno}/estudar/{idEstudo}")
	public ResponseEntity<Void> deletar(@PathVariable Long idAluno,
			@PathVariable Long idEstudo){
		Estudo estudo = estudoServiceImpl.consultarPorId(idEstudo, idAluno);
		estudo.setAluno(alunoServiceImpl.consultarPorId(idAluno));
		estudoServiceImpl.deletarEstudo(idEstudo);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idAluno}/estudar")
	public List<EstudoConsultaDTO> listar(@PathVariable Long idAluno) {
		Aluno aluno = alunoServiceImpl.consultarPorId(idAluno);
		return toCollectionModel(estudoServiceImpl.listagem(aluno.getId()));
	}
	
	@GetMapping("/{idAluno}/estudar/{idEstudo}")
	public ResponseEntity<EstudoConsultaDTO> consultarPorId(@PathVariable Long idAluno,
			@PathVariable Long idEstudo) {
		Estudo estudo = estudoServiceImpl.consultarPorId(idEstudo, idAluno);
		estudo.setAluno(alunoServiceImpl.consultarPorId(idAluno));
		EstudoConsultaDTO dto = toModelConsultaDTO(estudo);
		return ResponseEntity.ok().body(dto);
	}
	
	private EstudoDTO toModel(Estudo estudo) {
		return modelMapper.map(estudo,EstudoDTO.class);
	}
	
	private Estudo toEntityInserir(EstudoInserirDTO dto) {
		return modelMapper.map(dto,Estudo.class);
	}
	
	private EstudoConsultaDTO toModelConsultaDTO(Estudo estudo) {
		return modelMapper.map(estudo,EstudoConsultaDTO.class);
	}
	
	private List<EstudoConsultaDTO> toCollectionModel(List<Estudo> estudos) {
		return estudos.stream()
				.map(estudo -> toModelConsultaDTO(estudo))
				.collect(Collectors.toList());
	}
	
	
}
