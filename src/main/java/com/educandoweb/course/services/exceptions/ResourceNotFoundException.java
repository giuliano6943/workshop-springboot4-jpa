package com.educandoweb.course.services.exceptions;

//Exception personalizada que foi criada.
//Ela estende de RuntimeException, ou seja, é uma exceção não verificada (unchecked).
//Serve para indicar que um recurso (ex.: usuário, pedido, produto) não foi encontrado no banco
public class ResourceNotFoundException extends RuntimeException {
//O construtor recebe o id e monta uma mensagem: "Resource not found with id X"
    public ResourceNotFoundException(Object id) {
        super("Resource not found with id " + id);
    }

}
