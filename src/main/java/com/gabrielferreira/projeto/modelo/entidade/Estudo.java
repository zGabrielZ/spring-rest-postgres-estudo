package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielferreira.projeto.modelo.entidade.enums.StatusEstudo;
import com.gabrielferreira.projeto.modelo.service.exceptions.NegocioException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tab_estudo",schema = "estudos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudo implements Serializable{
	 
	
	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(timezone = MY_TIME_ZONE)
	private LocalDateTime dataInicio;
		
	@JsonFormat(timezone = MY_TIME_ZONE)
	private LocalDateTime dataFim;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	
	@Enumerated(EnumType.STRING)
	private StatusEstudo statusEstudo;
	
	private Integer horasPrevista;
	
	public boolean naoPodeSerFinalizada() {
		return StatusEstudo.PARALISADO.equals(getStatusEstudo()) || StatusEstudo.TERMINADO.equals(getStatusEstudo());
	}
	
	public boolean naoPodeSerReiniciado() {
		return StatusEstudo.INICIO.equals(getStatusEstudo()) || StatusEstudo.TERMINADO.equals(getStatusEstudo());
	}
	
	public void reinicio() {
		
		if(naoPodeSerReiniciado()) {
			throw new NegocioException("Estudo não pode ser reiniciado, pois ja está iniciado ou finalizada");
		}
		
		setStatusEstudo(StatusEstudo.REINICIO);
	}
	
	public void finalizar() {
		
		if(naoPodeSerFinalizada()) {
			throw new NegocioException("Estudo não pode ser finalizada, pois ja está finalizada ou paralisada");
		}
		
		setStatusEstudo(StatusEstudo.TERMINADO);
		setDataFim(LocalDateTime.now());
	}
	
	public void paralisada() {
		
		if(naoPodeSerFinalizada()) {
			throw new NegocioException("Estudo não pode ser paralisado, pois já está paralisada ou finalizada");
		}
		
		setStatusEstudo(StatusEstudo.PARALISADO);
	}

}
