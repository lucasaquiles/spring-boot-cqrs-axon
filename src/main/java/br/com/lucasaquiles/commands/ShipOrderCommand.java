package br.com.lucasaquiles.commands;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@RequiredArgsConstructor
public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
}
