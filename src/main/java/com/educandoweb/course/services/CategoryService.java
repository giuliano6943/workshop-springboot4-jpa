package com.educandoweb.course.services;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por encapsular a lógica de acesso a dados de categorias.
 *
 * Essa camada delega operações ao CategoryRepository e pode conter regras de negócio
 * relacionadas a categorias.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    /**
     * Retorna todas as categorias cadastradas.
     *
     * @return lista de categorias
     */
    public List<Category> findAll() {
        return repository.findAll();
    }

    /**
     * Busca uma categoria pelo ID.
     *
     * @param id identificador da categoria
     * @return a categoria encontrada
     * @throws java.util.NoSuchElementException se não existir categoria com o ID informado
     */
    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }
}
