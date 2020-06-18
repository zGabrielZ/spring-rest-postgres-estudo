package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Campo do nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nomeCompleto;
	
	@Email(message = "Email inválido")
	@NotBlank(message = "Campo do email não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String email;
	
	@NotBlank(message = "Campo da senha não pode ser vazio")
	@Size(max = 100,message = "Não pode passa de 100 caracteres")
	private String senha;
	
	@NotNull(message = "Campo do nascimento não pode ser vazio ou digitado incorretamente")
	private Date dataNascimento;
	
	@NotBlank(message = "Campo do ensino não pode ser vazio")
	private String ensino;
	
	@NotBlank(message = "Campo do sexo não pode ser vazio")
	private String sexo;
	
}
