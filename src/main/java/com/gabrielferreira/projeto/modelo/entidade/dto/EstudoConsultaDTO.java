package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.util.Date;

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
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataFim;
	
	@Enumerated(EnumType.STRING)
	private StatusEstudo statusEstudo;
	
	private DisciplinaDTO disciplina;
	
}
