package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoAutenticarDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Email(message = "Email inválido")
	@NotBlank(message = "Email não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String email;
	
	@NotBlank(message = "Senha não pode ser vazio")
	@Size(max = 100,message = "Não pode passa de 100 caracteres")
	private String senha;
	
}
