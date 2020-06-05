package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudoInserirDTO {

	@NotNull(message = "Data início não pode ser nulo")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInicio;
	
	@NotNull(message = "Data final não pode ser nulo")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataFim;
	
	@Valid
	@NotNull(message = "Aluno não pode ser nulo")
	private AlunoNovoId aluno;
	
	@Valid
	@NotNull(message = "Disciplina não pode ser nulo")
	private DisciplinaNovoId disciplina;
}
