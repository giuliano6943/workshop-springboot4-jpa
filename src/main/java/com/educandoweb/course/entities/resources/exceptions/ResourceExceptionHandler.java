package com.educandoweb.course.entities.resources.exceptions;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/* @ControllerAdvice -> Diz que essa classe centraliza o tratamento de exceções para todos os controladores
* @ExceptionHandler(ResourceNotFoundException.class) → Sempre que uma ResourceNotFoundException for lançada, esse método será chamado.
* StandardError -> Monta um objeto com timestamp → hora do erro.
status → código HTTP (404).
error → descrição genérica ("Resource not found").
message → mensagem detalhada da exception (Resource not found with id X).
path → URI da requisição que causou o erro.
*
*
* */
@ControllerAdvice
public class ResourceExceptionHandler {

    //“Sempre que uma ResourceNotFoundException for lançada em qualquer controlador, use este metodo para tratar
    //ResourceNotFoundException e → a exceção lançada, com a mensagem "Resource not found with id 999"
    //HttpServletRequest request → informações da requisição que causou o erro (ex.: qual URI foi chamada)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found"; // Define uma descrição generica do erro
        HttpStatus status = HttpStatus.NOT_FOUND; //Define o HTTP como 404 not found

        //Cria um objeto StandardError que encapsula todas as informações do erro.
        //Monta a resposta HTTP:
        //Status: 404 Not Found.
        //Body: o JSON com os detalhes do erro (StandardError).
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error"; // Define uma descrição generica do erro
        HttpStatus status = HttpStatus.BAD_REQUEST; //Define o HTTP como 404 not found
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
