package com.educandoweb.course.entities.enums;

/**
 * Enum que representa os possíveis status de um pedido.
 * Cada constante possui um código numérico associado,
 * que é armazenado no banco de dados.
 *
 * Valores:
 * - WAITING_PAYMENT → 1
 * - PAID            → 2
 * - SHIPED          → 3
 * - DELIVERED       → 4
 * - CANCELED        → 5
 */
public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPED(3),
    DELIVERED(4),
    CANCELED(5);

    private final int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    /**
     * Retorna o código numérico associado ao status.
     * Exemplo: OrderStatus.PAID.getCode() → 2
     */
    public int getCode() {
        return code;
    }

    /**
     * Converte um código numérico em um valor do enum.
     * Exemplo: OrderStatus.valueOf(2) → OrderStatus.PAID
     *
     * @param code código numérico do status
     * @return o enum correspondente
     * @throws IllegalArgumentException se o código for inválido
     */
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code " + code);
    }
}
