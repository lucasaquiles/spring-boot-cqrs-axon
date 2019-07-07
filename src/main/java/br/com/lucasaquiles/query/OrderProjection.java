package br.com.lucasaquiles.query;

import br.com.lucasaquiles.events.OrderConfirmedEvent;
import br.com.lucasaquiles.events.OrderPlacedEvent;
import br.com.lucasaquiles.events.OrderShippedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderProjection {

    private final OrderRepository orderRepository;

    public OrderProjection(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    @EventHandler
    public void on(OrderPlacedEvent event){
        log.info("new order was been created, event={}", event);
        orderRepository.save(new OrderSumary(event.getOrderId(), event.getProducts(), OrderStatusEnum.CREATED.getStatus()));
    }


    @EventHandler
    public void on(OrderConfirmedEvent event){

        log.info("order {} was been confirmed", event);

        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            order.setStatus(OrderStatusEnum.CONFIRMED.getStatus());
            orderRepository.save(order);
        } );
    }

    @EventHandler
    public void on(OrderShippedEvent event){

        log.info("order {} was been shipped", event);

        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            order.setStatus(OrderStatusEnum.SHIPPED.getStatus());
            orderRepository.save(order);
        } );
    }


    @QueryHandler
    public List<OrderSumary> handle(){
        log.info("loading all orders");
        return orderRepository.findAll();
    }

}
