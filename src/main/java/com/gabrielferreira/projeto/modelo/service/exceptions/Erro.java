package com.gabrielferreira.projeto.modelo.service.exceptions;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Erro {

	private Integer status;
	private OffsetDateTime localDateTime;
	private String titulo;
	private List<Campo> campos = new ArrayList<Erro.Campo>();
	
	@Getter
	@Setter
	public static class Campo{
		private String nome;
		private String mensagem;
		
		public Campo(String nome, String mensagem) {
			this.nome = nome;
			this.mensagem = mensagem;
		}
		
		
	}
	
	public Erro() {}
}
