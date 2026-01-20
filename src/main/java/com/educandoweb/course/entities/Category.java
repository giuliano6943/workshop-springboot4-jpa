package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade que representa uma categoria de produtos.
 * Mapeada para a tabela "tb_category".
 *
 * Cada categoria pode estar associada a vários produtos (relação ManyToMany).
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento no banco
    private Long id;

    private String name;

    /**
     * Relação ManyToMany com Product.
     * O lado dono da relação é o atributo "categories" na classe Product.
     *
     * @JsonIgnore evita loop infinito na serialização JSON.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    // Construtor vazio (obrigatório para JPA)
    public Category() {}

    // Construtor completo
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Product> getProducts() { return products; }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
