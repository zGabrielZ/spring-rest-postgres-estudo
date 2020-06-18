package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gabrielferreira.projeto.modelo.entidade.enums.Ensino;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tab_aluno",schema = "estudos")
@Getter
@Setter
@NoArgsConstructor
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCompleto;
	private String email;
	private String senha;
	private Date dataCadastro;
	private Date dataNascimento;
	private Ensino ensino;
	private Sexo sexo;
	
	@OneToMany(mappedBy = "aluno")
	private List<Estudo> estudos = new ArrayList<>();
	
	@OneToMany(mappedBy = "aluno")
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public Aluno(Long id, String nomeCompleto, String email, String senha, Date dataCadastro, Date dataNascimento,
			Ensino ensino, Sexo sexo) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.ensino = ensino;
		this.sexo = sexo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
