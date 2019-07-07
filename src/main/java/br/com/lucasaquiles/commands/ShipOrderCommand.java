package br.com.lucasaquiles.commands;

import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
}
