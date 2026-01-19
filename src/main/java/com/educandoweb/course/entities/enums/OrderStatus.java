package com.educandoweb.course.entities.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPED(3),
    DELIVERED(4),
    CANCELED(5);

    //Cada constante do enum carrega um número
    //Criação do construtor para cada constante
    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }
    //Permite acessar o numero associado ao status
    //EX: OrderStatus.PAID.getCode() retorna 2
    public int getCode() {
        return code;
    }

    //Caminho inverso, recebe um numero e retorna o status
    //Exemplo: OrderStatus.valueOf(2) retorna OrderStatus.PAID.
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code " + code);
    }
}

