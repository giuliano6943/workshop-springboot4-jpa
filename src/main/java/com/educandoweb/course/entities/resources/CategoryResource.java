package com.educandoweb.course.entities.resources;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Essa classe representa um controlador REST
//É responsavel por expor a API http ao mundo externo

//@RestController -> Diz ao Spring que essa classe é um controlador REST
//Vai retornar dados JSON diretamente para o cliente.
//@RequestMapping(value = "/Categorys") -> Define a rota base da API, tudo que estiver dentro dessa classe
//vai responder a URLs que comecem com /Categorys
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    //Essa classe não chama o banco diretamente, ela chama o serviço
    //O controlador delega a lógica para o serviço
    //Ele só cuida da entrada HTTP e da resposta HTTP.
    @Autowired
    private CategoryService service;

    //@GetMapping -> Esse metodo responde a requisição GET na rota /Categorys
    @GetMapping
    //É uma reposta HTTP que contém um objeto Category e o status da requisição.
    //Dentro desse metodo é criado um usuário ficticio e retorna ele como resposta
    // Retorna todos os usuários como JSON
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list =  service.findAll();

      return ResponseEntity.ok().body(list);
    }
    //Adicionando o valor /{id} no final da requisição, retorna o usuário com esse ID
    //ResponseEntity é uma classe do Spring que representa toda a resposta HTTP.
    @GetMapping(value = "/{id}")
    //PathVariable diz ao Spring que o valor do parâmetro id vem da URL.
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
