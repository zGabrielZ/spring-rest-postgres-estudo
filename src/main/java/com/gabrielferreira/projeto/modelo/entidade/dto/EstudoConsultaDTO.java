package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielferreira.projeto.modelo.entidade.enums.StatusEstudo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudoConsultaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = MY_TIME_ZONE)
	private LocalDateTime dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = MY_TIME_ZONE)
	private LocalDateTime dataFim;
		
	@Enumerated(EnumType.STRING)
	private StatusEstudo statusEstudo;
	
	private Integer horasPrevista;
	
	private DisciplinaDTO disciplina;
	
}
