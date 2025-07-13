package it.unicam.cs.FilieraAgricola.Order;

import it.unicam.cs.FilieraAgricola.CheckStrategy.UpdateOrderStateCheckStrategy;
import it.unicam.cs.FilieraAgricola.Repository.OrderRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    @Autowired
    private UpdateOrderStateCheckStrategy updateOrderStateCheckStrategy;

    @Autowired
    private OrderRepository orderRepository;


    public void updateOrderState(User user, Order order, OrderState state){

        if(!this.updateOrderStateCheckStrategy.validate(user, order, state))
            throw new IllegalArgumentException("Order non valid for state update");

        this.orderRepository.updateOrderState(user.getUserID(), order.getOrderID(), state);

    }
}
