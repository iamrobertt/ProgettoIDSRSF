package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Order.Order;
import it.unicam.cs.FilieraAgricola.Order.OrderState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o from Order o where o.orderID = :orderID and o.orderBuyer = :userID")
    Optional<Order> findByOrderIDAndUser(long userID, long orderID);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderState = :newState where o.orderID = :orderID and o.orderBuyer = :userID")
    void updateOrderState(long userID, long orderID, OrderState newState);
}
