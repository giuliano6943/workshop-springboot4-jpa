package com.educandoweb.course.entities.resources;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por expor a API de pedidos.
 *
 * Endpoints:
 * - GET /orders        → retorna todos os pedidos
 * - GET /orders/{id}   → retorna um pedido específico pelo ID
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    /**
     * Retorna todos os pedidos como JSON.
     *
     * @return ResponseEntity contendo a lista de pedidos e status HTTP 200.
     */
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Retorna um pedido específico pelo ID.
     *
     * @param id identificador do pedido (extraído da URL via @PathVariable)
     * @return ResponseEntity contendo o pedido encontrado e status HTTP 200.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
