package br.com.lucasaquiles.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderSumary, String> {

}
