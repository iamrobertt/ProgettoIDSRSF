package it.unicam.cs.FilieraAgricola.Test.Order;

import it.unicam.cs.FilieraAgricola.CheckStrategy.UpdateOrderStateCheckStrategy;
import it.unicam.cs.FilieraAgricola.Order.Order;
import it.unicam.cs.FilieraAgricola.Order.OrderItem;
import it.unicam.cs.FilieraAgricola.Order.OrderState;
import it.unicam.cs.FilieraAgricola.Order.OrderUtility;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @Autowired
    private OrderUtility orderUtility;
    @Autowired
    private UserUtility userUtility;
    @Autowired
    private ProductUtility productUtility;

    private UpdateOrderStateCheckStrategy updateOrderStateCheckStrategy;

    @Test
    public void testOrder() {

        Product product = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5, ProductState.PRODUCT_TO_VALIDATE, ProductType.SINGLE);
        User user = new User(2,"Franco","Accia","prova2@prova.prova","juventus12345","1234567", UserRole.VALIDATOR, UserState.VALIDATED);

        Order order = new Order(user,12.3,OrderState.ORDER_DELIVERED);
        OrderItem orderItem = new OrderItem(order,product,1,12.3);

        List<OrderItem> item = new ArrayList<>();
        item.add(orderItem);



        // mi aspetto un errore poichè l'utente è nullo
        assertThrows(IllegalArgumentException.class, () -> updateOrderStateCheckStrategy.validate(null,order,OrderState.ORDER_DELIVERED));

        // mi aspetto un errore, user o order sono nulli, o entrambi
        assertThrows(IllegalStateException.class, () -> orderUtility.checkExistOrderWithUser(null,null));
        assertThrows(IllegalStateException.class, () -> orderUtility.checkExistOrderWithUser(user,null));
        assertThrows(IllegalStateException.class, () -> orderUtility.checkExistOrderWithUser(null, order));

        // mi aspetto un errore, non conosco lo stato dell'ordine
        assertThrows(IllegalStateException.class, () -> updateOrderStateCheckStrategy.validate(user,order, null));

        //mi aspetto che mi dia errore poichè le info su l'ordine non sono complete
        orderItem.setOrderItemProduct(null);
        order.setOrderBuyer(null);
        assertThrows(IllegalStateException.class, () -> orderUtility.checkOrderInfo(order));

    }
}
