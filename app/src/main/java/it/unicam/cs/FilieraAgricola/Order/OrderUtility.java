package it.unicam.cs.FilieraAgricola.Order;

import it.unicam.cs.FilieraAgricola.Repository.OrderRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderUtility {

    @Autowired
    private OrderRepository orderRepository;


    public boolean checkOrderInfo(Order order) {
        return order != null &&
                order.getOrderID() != 0 &&
                order.getOrderItems() != null &&
                !order.getOrderItems().isEmpty();
    }


    public boolean checkExistOrder(User user, Order order) {
        Optional<Order> orderToSearch = this.orderRepository.findByOrderIDAndUser(user.getUserID(), order.getOrderID());
        return orderToSearch.isPresent();
    }




    public Order getOrder(long userID, long productID) {
        Optional<Order> order = this.orderRepository.findByOrderIDAndUser(userID, productID);
        return order.orElse(null);
    }
}
