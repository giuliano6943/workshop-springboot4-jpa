package com.educandoweb.course.entities.resources;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por expor a API de produtos.
 *
 * Endpoints:
 * - GET /products        → retorna todos os produtos
 * - GET /products/{id}   → retorna um produto específico pelo ID
 */
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    /**
     * Retorna todos os produtos como JSON.
     *
     * @return ResponseEntity contendo a lista de produtos e status HTTP 200.
     */
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Retorna um produto específico pelo ID.
     *
     * @param id identificador do produto (extraído da URL via @PathVariable)
     * @return ResponseEntity contendo o produto encontrado e status HTTP 200.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
