package com.educandoweb.course.config;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

//Dizendo ao spring que é uma classe de configuracao
//E que estamos usando o perfil de teste
@Configuration
@Profile("test")
//CommandLineRunner é uma interface do Spring
//Tudo que está dentro do metodo run será executado assim que a aplicação rodar
public class TestConfig implements CommandLineRunner {

    //@Autowired INJEÇÃO DE DEPENDENCIA
    //Por favor, injete aqui uma instância de UserRepository que você já gerencia no contexto da aplicação.
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void run(String... args) throws Exception {
        //Criação de dois usuários no banco de dados
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);


        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
