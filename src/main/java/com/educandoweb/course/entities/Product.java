package com.educandoweb.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade que representa um produto.
 * Mapeada para a tabela "tb_product".
 * Cada produto pode pertencer a várias categorias (relação ManyToMany).
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento no banco
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    /**
     * Relação ManyToMany entre Produto e Categoria.
     * Usa uma tabela intermediária "tb_product_category" para armazenar os vínculos.
     * - joinColumns: chave estrangeira para Product
     * - inverseJoinColumns: chave estrangeira para Category
     * O uso de Set evita duplicidades.
     */
    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();


    // Construtor vazio (obrigatório para JPA)
    public Product() {}

    // Construtor completo
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    /**
     * Retorna as categorias associadas ao produto.
     * Apenas getter para evitar substituição da coleção inteira.
     */
    public Set<Category> getCategories() { return categories; }
@JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for(OrderItem x : items){
            set.add(x.getOrder());
        }
        return set;
    }


    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
