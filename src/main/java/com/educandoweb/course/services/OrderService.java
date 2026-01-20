package com.educandoweb.course.services;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por encapsular a lógica de acesso a dados de pedidos.
 *
 * Essa camada delega operações ao OrderRepository e pode conter regras de negócio
 * relacionadas a pedidos.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    /**
     * Retorna todos os pedidos cadastrados.
     *
     * @return lista de pedidos
     */
    public List<Order> findAll() {
        return repository.findAll();
    }

    /**
     * Busca um pedido pelo ID.
     *
     * @param id identificador do pedido
     * @return o pedido encontrado
     * @throws java.util.NoSuchElementException se não existir pedido com o ID informado
     */
    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
