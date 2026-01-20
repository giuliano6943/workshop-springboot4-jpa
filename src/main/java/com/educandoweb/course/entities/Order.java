package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade que representa um pedido.
 * Mapeada para a tabela "tb_order".
 *
 * Cada pedido possui:
 * - um cliente associado (User)
 * - um status (OrderStatus)
 * - uma data/hora (moment)
 * - uma coleção de itens (OrderItem)
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento no banco
    private Long id;

    /**
     * Momento em que o pedido foi realizado.
     * Serializado em formato ISO 8601 no JSON.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "GMT")
    private Instant moment;

    /**
     * Status do pedido armazenado como número (Integer).
     * O getter e setter convertem entre número e enum OrderStatus.
     */
    private Integer orderStatus;

    /**
     * Associação ManyToOne com User.
     * Muitos pedidos podem estar relacionados a um único cliente.
     * A coluna "client_id" é a chave estrangeira para tb_user.
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    /**
     * Associação OneToMany com OrderItem.
     * Um pedido pode ter vários itens.
     */
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    // Mapeando a relação um para um com o id do payment
    // o cascade é obrigatório
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;


    // Construtor vazio (obrigatório para JPA)
    public Order() {}

    // Construtor completo
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Instant getMoment() { return moment; }
    public void setMoment(Instant moment) { this.moment = moment; }

    /**
     * Converte o código numérico em enum OrderStatus.
     */
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    /**
     * Converte o enum OrderStatus em código numérico para persistência.
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() { return items; }


    public Double getTotal() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getSubtotal();
        }
        return sum;
    }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
