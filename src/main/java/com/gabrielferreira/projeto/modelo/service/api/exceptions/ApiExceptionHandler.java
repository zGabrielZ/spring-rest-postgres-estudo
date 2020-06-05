package com.gabrielferreira.projeto.modelo.service.api.exceptions;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gabrielferreira.projeto.modelo.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.modelo.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.modelo.service.exceptions.Erro;
import com.gabrielferreira.projeto.modelo.service.exceptions.Erro.Campo;
import com.gabrielferreira.projeto.modelo.service.exceptions.ErroAutenticacaoException;
import com.gabrielferreira.projeto.modelo.service.exceptions.NegocioException;
import com.gabrielferreira.projeto.modelo.service.exceptions.RegraDeNegocioException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private Erro erro = new Erro();

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus httpStatus,WebRequest request){
		Erro erro = new Erro();
		
		List<Campo> campos = new ArrayList<Erro.Campo>();
		
		for(ObjectError error:ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			campos.add(new Erro.Campo(nome,msg));
		}
		
		erro.setStatus(httpStatus.value());
		erro.setTitulo("Um ou mais campos estão inválidos,Faça o preenchimento correto e tenta novamente");
		erro.setLocalDateTime(OffsetDateTime.now());
		erro.setCampos(campos);
		
		return super.handleExceptionInternal(ex, erro, headers, httpStatus, request);
		
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<Erro> database(DatabaseException e,
			HttpServletRequest req){
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTitulo(e.getMessage());
		erro.setLocalDateTime(OffsetDateTime.now());
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(EntidadeNotFoundException.class)
	public ResponseEntity<Erro> entidadeNotFound(EntidadeNotFoundException e,
			HttpServletRequest req){
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setTitulo(e.getMessage());
		erro.setLocalDateTime(OffsetDateTime.now());
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
		
	@ExceptionHandler(RegraDeNegocioException.class)
	public ResponseEntity<Erro> regraNegocioException(RegraDeNegocioException e,
			HttpServletRequest req){
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTitulo(e.getMessage());
		erro.setLocalDateTime(OffsetDateTime.now());
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Erro> negocioException(NegocioException e,
			HttpServletRequest req){
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTitulo(e.getMessage());
		erro.setLocalDateTime(OffsetDateTime.now());
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(ErroAutenticacaoException.class)
	public ResponseEntity<Erro> erroAutenticacaoException(ErroAutenticacaoException e,
			HttpServletRequest req){
		erro.setStatus(HttpStatus.FORBIDDEN.value());
		erro.setTitulo(e.getMessage());
		erro.setLocalDateTime(OffsetDateTime.now());
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	
}
