package br.com.lucasaquiles.commands;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;


@RequiredArgsConstructor
@Data
public class PlaceOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String product;
}
