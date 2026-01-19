package com.educandoweb.course.entities.resources;

import com.educandoweb.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Essa classe representa um controlador REST
//É responsavel por expor a API http ao mundo externo

//@RestController -> Diz ao Spring que essa classe é um controlador REST
//Vai retornar dados JSON diretamente para o cliente.
//@RequestMapping(value = "/users") -> Define a rota base da API, tudo que estiver dentro dessa classe
//vai responder a URLs que comecem com /users
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    //@GetMapping -> Esse metodo responde a requisição GET na rota /users
    @GetMapping
    //É uma reposta HTTP que contém um objeto USER e o status da requisição.
    //Dentro desse metodo é criado um usuário ficticio e retorna ele como resposta
    public ResponseEntity<User> findAll(){
      User u = new User(1L,"Maria","maria@gmail.com","121313131", "12345");
      return ResponseEntity.ok().body(u);
    }
}
