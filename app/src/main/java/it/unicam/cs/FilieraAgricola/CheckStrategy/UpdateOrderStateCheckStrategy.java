package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Order.Order;
import it.unicam.cs.FilieraAgricola.Order.OrderState;
import it.unicam.cs.FilieraAgricola.Order.OrderUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderStateCheckStrategy implements CustomCheckStrategy<Order, OrderState> {

    @Autowired
    private OrderUtility orderUtility;

    @Override
    public boolean validate(User user, Order order, OrderState newOrderState) {

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");


        if(!this.orderUtility.checkOrderInfo(order))
            throw new IllegalArgumentException("Error retrieving order information.");


        if(!this.orderUtility.checkExistOrder(user, order))
            throw new IllegalArgumentException("Order with id" + order.getOrderID() + " does not exist.");


        if(newOrderState == null)
            throw new IllegalArgumentException("The new state chosen does not exist.");


        if(!order.getOrderState().canTransitionTo(newOrderState))
            throw new IllegalArgumentException("The new state chosen is not valid for the current state of this order.");

        return true;
    }
}
