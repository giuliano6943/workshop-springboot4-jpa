package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        //Se o Optional estiver vazio, ou seja, se o usuário nao foi encontrado, crie uma nova exceção
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Inserir um novo usuário ao banco de dados
    public User insert(User obj) {
        return repository.save(obj);
    }

    //Deletar um usuário pelo id
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
    /*
    * repository.getReferenceById(id) → Carrega uma referência para o usuário existente no banco
    * Se o ID não existir, vai lançar uma exceção quando tentar usar.
    */
    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    //Metodo diz quais campos poderão ser atualizados
    //só é permitido atualizar email,nome e telefone.
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
