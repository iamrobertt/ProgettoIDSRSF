package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Order.Order;
import it.unicam.cs.FilieraAgricola.Order.OrderItem;
import it.unicam.cs.FilieraAgricola.Order.OrderState;
import it.unicam.cs.FilieraAgricola.Product.BundleItem;
import it.unicam.cs.FilieraAgricola.Product.BundleProduct;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.SingleProduct;
import it.unicam.cs.FilieraAgricola.Repository.OrderRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;


public class BuyProductCommand extends Command<List<Pair<Product, Integer>>> {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public BuyProductCommand(User user, List<Pair<Product, Integer>> productsToBuy, OrderRepository orderRepository, ProductRepository productRepository) {
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


        for (Pair<Product, Integer> productPair : this.item){

            OrderItem orderItem = createOrderItem(order, productPair.a, productPair.b);
            Product realProduct = this.productRepository.findById(productPair.a.getProductID()).orElse(null);

            totalOrderPrice += realProduct.getProductPrice();
            this.productRepository.subProductQuantity(realProduct.getProductID(), productPair.b);

            itemList.add(orderItem);
        }

        order.setOrderItems(itemList);
        order.setTotalOrderPrice(totalOrderPrice);
        this.orderRepository.save(order);

    }


    private OrderItem createOrderItem(Order order, Product product, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setParentOrder(order);
        orderItem.setOrderItemProduct(product);
        orderItem.setOrderItemPrice(product.getProductPrice());
        orderItem.setOrderItemQuantity(quantity);
        return orderItem;
    }
}
