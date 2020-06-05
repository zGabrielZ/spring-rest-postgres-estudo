package com.gabrielferreira.projeto.modelo.entidade.dto;
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
public class DisciplinaAtualizarDTO {

	@NotBlank(message = "Nome não pode ser vazio")
	@Size(max = 120,message = "Não pode passa de 120 caracteres")
	private String nome;
	
	@NotBlank(message = "Descrição não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String descricao;

	
}
