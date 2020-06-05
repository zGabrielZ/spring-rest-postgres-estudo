package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeCompleto;
	private String email;
	
	@JsonIgnore
	private String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataCadastro;
	
}
