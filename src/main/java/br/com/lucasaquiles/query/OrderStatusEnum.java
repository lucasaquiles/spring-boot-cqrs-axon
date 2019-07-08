package br.com.lucasaquiles.query;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    CREATED("Created"),
    CONFIRMED("Confirmed"),
    SHIPPED("Shipped");

    private String status;
    OrderStatusEnum(String status) {
        this.status = status;
    }


}
