package com.educandoweb.course.config;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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


    @Override
    public void run(String... args) throws Exception {
        //Criação de dois usuários no banco de dados
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
