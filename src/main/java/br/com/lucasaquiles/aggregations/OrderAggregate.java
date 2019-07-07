package br.com.lucasaquiles.aggregations;

import br.com.lucasaquiles.commands.ConfirmOrderCommand;
import br.com.lucasaquiles.commands.PlaceOrderCommand;
import br.com.lucasaquiles.commands.ShipOrderCommand;
import br.com.lucasaquiles.events.OrderConfirmedEvent;
import br.com.lucasaquiles.events.OrderPlacedEvent;
import br.com.lucasaquiles.events.OrderShippedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
@Data
@Entity
public class OrderAggregate {

    @AggregateIdentifier
    @Id
    private String orderId;
    private boolean orderConfirmed;

    public OrderAggregate(){

    }

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command){

        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event){
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }


    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command){

        if(!orderConfirmed){
            throw new IllegalStateException("nao pode enviar um pedido que nao foi confirmado ainda");
        }

        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event){
        orderConfirmed = true;
    }

}
