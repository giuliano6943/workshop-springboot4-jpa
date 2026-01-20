package com.educandoweb.course.entities.resources;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por expor a API de categorias.
 *
 * Endpoints:
 * - GET /categories        → retorna todas as categorias
 * - GET /categories/{id}   → retorna uma categoria específica pelo ID
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    /**
     * Retorna todas as categorias como JSON.
     *
     * @return ResponseEntity contendo a lista de categorias e status HTTP 200.
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Retorna uma categoria específica pelo ID.
     *
     * @param id identificador da categoria (extraído da URL via @PathVariable)
     * @return ResponseEntity contendo a categoria encontrada e status HTTP 200.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
