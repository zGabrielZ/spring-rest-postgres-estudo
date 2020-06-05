package com.gabrielferreira.projeto.modelo.service;



import com.gabrielferreira.projeto.modelo.entidade.Aluno;

public interface AlunoService {
	
	void validarEmail(String email);
	
	Aluno autenticarAluno(String email,String senha);

	Aluno inserirAluno(Aluno aluno);
	
	Aluno consultarPorId(Long idAluno);
	
}
