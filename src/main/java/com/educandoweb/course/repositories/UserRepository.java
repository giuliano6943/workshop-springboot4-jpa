package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
//JpaRepository vem com todos os metodos CRUD
//O Spring Data JPA gera automaticamente a implementação dessa interface em tempo de execução.
//Não é necessario utilizar a annotation @Repository
//Pq o JpaRepository já contem essa annotation

public interface UserRepository extends JpaRepository<User, Long> {

}
