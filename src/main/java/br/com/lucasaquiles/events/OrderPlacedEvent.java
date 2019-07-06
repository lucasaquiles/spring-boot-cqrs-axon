package br.com.lucasaquiles.events;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class OrderPlacedEvent {

    private final String orderId;
    private final String products;

}
