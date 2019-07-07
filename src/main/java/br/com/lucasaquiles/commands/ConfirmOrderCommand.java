package br.com.lucasaquiles.commands;

import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

}
