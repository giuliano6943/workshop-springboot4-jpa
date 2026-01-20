package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidade que representa um usuário do sistema.
 * Mapeada para a tabela "tb_user".
 * Cada usuário pode ter vários pedidos associados.
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento no banco
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String password;

    /**
     * Associação com pedidos (Order).
     * Um usuário pode ter vários pedidos.
     * O lado dono da relação é o campo "client" na classe Order.
     *
     * @JsonIgnore evita loop infinito na serialização JSON.
     */
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    // Construtor vazio (obrigatório para JPA)
    public User() {}

    // Construtor completo
    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    /**
     * Retorna a lista de pedidos associados ao usuário.
     */
    public List<Order> getOrders() { return orders; }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
