package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Order.Order;
import it.unicam.cs.FilieraAgricola.Order.OrderItem;
import it.unicam.cs.FilieraAgricola.Order.OrderState;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Repository.OrderRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class BuyProductCommand extends Command<List<Product>> {


    private OrderRepository orderRepository;
    private ProductRepository productRepository;


    public BuyProductCommand(User user, List<Product> productsToBuy, OrderRepository orderRepository, ProductRepository productRepository) {
        super(user, productsToBuy);
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(UserRole.CUSTOMER);
        return neededRoles;
    }


    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }


    @Transactional
    @Override
    public void execute() {

        List<OrderItem> itemList= new ArrayList<>();
        double totalOrderPrice = 0;

        Order order = new Order();
        order.setOrderBuyer(this.user);
        order.setOrderState(OrderState.ORDER_RECEIVED);

        for(Product product : this.item) {
            OrderItem orderItem = new OrderItem();
            orderItem.setParentOrder(order);
            orderItem.setOrderItemProduct(product);
            orderItem.setOrderItemPrice(product.getProductPrice());
            orderItem.setOrderItemQuantity(product.getProductQuantity()); //todo rivedi cosa passare per mettere quantita
            itemList.add(orderItem);
            totalOrderPrice += product.getProductPrice();
        }

        order.setOrderItems(itemList);
        order.setTotalOrderPrice(totalOrderPrice);

        this.orderRepository.save(order);

    }
}
