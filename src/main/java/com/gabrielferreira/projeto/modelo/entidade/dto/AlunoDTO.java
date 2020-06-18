package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielferreira.projeto.modelo.entidade.enums.Ensino;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";
	
	private Long id;
	private String nomeCompleto;
	private String email;
	
	@JsonIgnore
	private String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = MY_TIME_ZONE)
	private Date dataCadastro;
	
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = MY_TIME_ZONE)
	private Date dataNascimento;
	
	private Ensino ensino;
	
	private Sexo sexo;
	
}
