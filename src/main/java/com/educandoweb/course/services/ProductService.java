package com.educandoweb.course.services;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por encapsular a lógica de acesso a dados de produtos.
 *
 * Essa camada delega operações ao ProductRepository e pode conter regras de negócio
 * relacionadas a produtos.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    /**
     * Retorna todos os produtos cadastrados.
     *
     * @return lista de produtos
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * Busca um produto pelo ID.
     *
     * @param id identificador do produto
     * @return o produto encontrado
     * @throws java.util.NoSuchElementException se não existir produto com o ID informado
     */
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }
}
