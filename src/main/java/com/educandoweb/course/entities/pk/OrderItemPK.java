package com.educandoweb.course.entities.pk;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe auxiliar que representa a chave primária composta de OrderItem.
 *
 * A chave é formada pela combinação de:
 * - order_id (referência para Order)
 * - product_id (referência para Product)
 *
 * Essa classe é embutida em OrderItem através da anotação @EmbeddedId.
 */
@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id") // chave estrangeira para Order
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id") // chave estrangeira para Product
    private Product product;

    // Getters e Setters
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    // equals e hashCode baseados em order e product
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(order);
        result = 31 * result + Objects.hashCode(product);
        return result;
    }
}
