package com.educandoweb.course.entities;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade que representa um item de pedido.
 * Mapeada para a tabela "tb_order_item".
 *
 * Cada item de pedido associa um produto a um pedido,
 * incluindo informações adicionais como quantidade e preço.
 */
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    /**
     * Chave primária composta (order_id + product_id).
     * Representada pela classe OrderItemPK.
     */
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    // Construtor vazio (obrigatório para JPA)
    public OrderItem() {}

    // Construtor completo
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Retorna o pedido associado ao item.
     * @JsonIgnore evita loop infinito na serialização JSON.
     */
    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    /**
     * Retorna o produto associado ao item.
     */
    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    // Getters e Setters para atributos extras
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    // equals e hashCode baseados na chave composta
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    //Metodo subtotal do item de pedido
    public Double getSubtotal() {
        return price * quantity;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
