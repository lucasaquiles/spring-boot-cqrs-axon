package br.com.lucasaquiles.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@Data
@Entity
public class OrderSumary {

    @Id
    private String orderId;
    private String product;
    private String status;
}


