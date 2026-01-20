package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por encapsular a lógica de acesso a dados de usuários.
 *
 * Essa camada delega operações ao UserRepository e pode conter regras de negócio
 * relacionadas a usuários.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    /**
     * Retorna todos os usuários cadastrados.
     *
     * @return lista de usuários
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id identificador do usuário
     * @return o usuário encontrado
     * @throws java.util.NoSuchElementException se não existir usuário com o ID informado
     */
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    //Inserir um novo usuário ao banco de dados
    public User insert(User obj) {
        return repository.save(obj);
    }

    //Deletar um usuário pelo id
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
