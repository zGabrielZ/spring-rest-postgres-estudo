package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaNovoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Disciplona id não pode ser nulo")
	private Long id;

}
