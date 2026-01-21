package com.educandoweb.course.entities.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST responsável por expor a API de usuários.
 *
 * Endpoints:
 * - GET /users        → retorna todos os usuários
 * - GET /users/{id}   → retorna um usuário específico pelo ID
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    /**
     * Retorna todos os usuários como JSON.
     *
     * @return ResponseEntity contendo a lista de usuários e status HTTP 200.
     */
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Retorna um usuário específico pelo ID.
     *
     * @param id identificador do usuário (extraído da URL via @PathVariable)
     * @return ResponseEntity contendo o usuário encontrado e status HTTP 200.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    /*
    * @RequestBody pega o JSON enviado no corpo da requisição e converte para um objeto User
    * ResponseEntity<User> -> Encapsula a resposta HTTP, permitindo controlar status code, headers e body
    * URI uri = ServletUriComponentsBuilder -> Aqui você está construindo a URI do recurso recém-criado.
    * fromCurrentRequest() -> Pegue a URL atual ex: /users
    * .path("/{id}") -> Adiciona /{id} ao final
    * .buildAndExpand(obj.getId()) -> Substitui o {id} pelo ID do usuário recem criado
    * .toUri(); transforma o objeto em URI
    *
    * ResponseEntity.created(uri) -> Retorna um HTTP 201 Created e coloca no header
    * service.insert(obj) → chama o metodo do service que salva o usuário no banco.
    */

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(service.insert(obj));
    }

    //Exemplo: DELETE /users/5 → vai tentar excluir o usuário com id = 5.
    //@PathVariable Long id -> Pega o ID passado na URL como parametro
    //return ResponseEntity.noContent().build(); -> Retorna uma resposta http 204 no content
    @DeleteMapping(value = "/{id}" )
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    //@PutMapping("/{id}") → Esse metodo responde a requisições HTTP PUT.
    //Exemplo: PUT /users/5 com um JSON no corpo da requisição.
    // @PathVariable Long id → Captura o {id} da URL. Se você chamar PUT /users/5, o valor 5 vira o argumento id
    //@RequestBody User obj → Pega o JSON enviado no corpo da requisição e converte para um objeto User.

    @PutMapping(value = "/{id}" )
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
