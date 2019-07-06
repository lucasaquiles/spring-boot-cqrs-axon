package br.com.lucasaquiles.commands;

import br.com.lucasaquiles.aggregations.OrderAggregate;
import br.com.lucasaquiles.events.OrderConfirmedEvent;
import br.com.lucasaquiles.events.OrderPlacedEvent;
import br.com.lucasaquiles.events.OrderShippedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class TestCommand {

    private FixtureConfiguration<OrderAggregate> fixture;

    @Before
    public void setUp(){
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }


    @Test
    public void testHandlesPlaceOrderCommand(){

        String orderId = UUID.randomUUID().toString();
        String product = "violao";

        fixture.givenNoPriorActivity()
                .when(new PlaceOrderCommand(orderId, product))
                .expectEvents(new OrderPlacedEvent(orderId, product));

    }


    @Test
    public void testShipOrderNotConfirmed(){
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
                .when(new ShipOrderCommand(orderId))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void testShipConfirmedOrder(){
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));
    }
}
