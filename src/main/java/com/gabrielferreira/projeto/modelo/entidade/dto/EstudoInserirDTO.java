package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class EstudoInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";

	@NotNull(message = "Data início não pode ser nulo")
	@JsonFormat(timezone = MY_TIME_ZONE)
	private LocalDateTime dataInicio;
		
	@Valid
	@NotNull(message = "Aluno não pode ser nulo")
	private AlunoNovoId aluno;
	
	@NotNull(message = "Horas previstas não pode ser vazio")
	@Min(value = 1,message = "Mínimo é de 1 hora")
	@Max(value = 24,message = "Máximo é de 24 horas")
	private Integer horasPrevista;
	
	@Valid
	@NotNull(message = "Disciplina não pode ser nulo")
	private DisciplinaNovoId disciplina;
}
