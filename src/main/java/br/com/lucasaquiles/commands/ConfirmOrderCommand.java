package br.com.lucasaquiles.commands;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@RequiredArgsConstructor
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

}
