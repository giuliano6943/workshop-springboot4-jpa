package com.educandoweb.course.services;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service diz ao spring que a classe é um componente de serviço
//O spring gerencia a classe como se ela tivesse pronta para ser injetada em outras partes do sistema
@Service
public class OrderService {
    //Injeção de dependencia da classe OrderRepository
    //Isso permite que você use os métodos do JPA (findAll, findById, save, etc.) sem instanciar nada manualmente.
    @Autowired
    private OrderRepository repository;

    //Utilizando o metodo que traz todos os usuários
    public List<Order> findAll() {
        return repository.findAll();
    }
    //Busca um usuário por id.
    //Usa o Optional para evitar NullPointerException
    //O Optional é usado porque o resultado da busca pode não existir
    //Futuramente vamos tratar essa excessão, caso nao exista o usuário com o ID passado.
    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
