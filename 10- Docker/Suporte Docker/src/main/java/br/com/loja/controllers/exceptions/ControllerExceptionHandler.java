package br.com.loja.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.loja.services.exceptions.BadRequestException;
import br.com.loja.services.exceptions.EntityNotFoundException;
import br.com.loja.services.exceptions.ServiceException;
import br.com.loja.services.exceptions.UnprocessableEntityException;

//Captura e trata uma exceção específica no Controller 
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//Status code 400
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("ERRO");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	
	}
	//Status code 404
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("ERRO");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	
	}
	
	//Status code 422
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<StandardError> unprocessableEntity(UnprocessableEntityException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setError("Não processado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	
	}
	
	//Status code 500
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<StandardError> exceptions(Exception e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError("Erro servidor");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		
	}
	
}
