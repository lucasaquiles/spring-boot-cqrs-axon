package br.com.lucasaquiles.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderConfirmedEvent {

    private String orderId;
}
