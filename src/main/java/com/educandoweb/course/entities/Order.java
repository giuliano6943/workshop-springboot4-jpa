package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//Dizendo que a classe representa uma tabela no banco e o nome dessa tabela
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Id //-> Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //-> O banco gera o valor automaticamente
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant moment; //Usado para representar um ponto específico no tempo


    private Integer orderStatus;

    @ManyToOne //Mutios pedidos estão relacionados a um usuário só
    @JoinColumn(name = "client_id")//Cria a coluna client_id na tabela tb_order  que é a chave estrangeira apontando para tb_user
   //cada pedido tem um cliente associado.
    private User client;


    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();



    public Order() {}

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
        this.orderStatus = orderStatus.getCode();
    }
        }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

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
