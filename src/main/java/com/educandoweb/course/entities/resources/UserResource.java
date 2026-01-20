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

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(obj));
    }

    @DeleteMapping(value = "/{id}" )
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
