package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.ProductWithQuantityDTO;
import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.Manager.ProductManager;
import it.unicam.cs.FilieraAgricola.Order.Order;
import it.unicam.cs.FilieraAgricola.Order.OrderManager;
import it.unicam.cs.FilieraAgricola.Order.OrderState;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.Repository.OrderRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.antlr.v4.runtime.misc.Pair;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private ControllerUtility controllerUtility;

    @Autowired
    private ProductUtility productUtility;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/insertProduct")
    public ResponseEntity<String> insertProduct(@RequestBody ProductDTO productDTO) {

        Product product = this.controllerUtility.convertToProduct(productDTO);

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUserEmail(userEmail);

        product.setProductUser(user);

        try {
            this.productManager.loadProductRequest(user, product);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("Product loaded successfully.");
    }


    @PostMapping("/sellProduct")
    public ResponseEntity<String> sellProduct(@RequestParam long productID) {

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUserEmail(userEmail);

        Product product = this.productUtility.getProduct(user.getUserID(), productID);

        try{
            this.productManager.sellProductRequest(user, product);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("Sell request for product" + product.getProductName() + " done successfully.");
    }

    @PostMapping("/validateProduct")
    public ResponseEntity<String> validateProduct(@RequestParam long productID, @RequestParam String validationState) {

        ProductValidationState productValidationState = ProductValidationState.valueOf(validationState);

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUserEmail(userEmail);

        Product product = this.productUtility.getProduct(user.getUserID(), productID);

        try{
            this.productManager.validateProductRequest(user, product, productValidationState);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("Product with id" + product.getProductID() + " validated successfully.");
    }


    @PostMapping("/buyProduct")
    public ResponseEntity<String> buyProduct(@RequestBody List<ProductWithQuantityDTO> buyProductDTOList) {

        try {
            List<Pair<Product, Integer>> productsToBuy = new ArrayList<>();

            //pairing every product with the quantity to buy
            for (ProductWithQuantityDTO buyProductDTO : buyProductDTOList) {
                Pair<Product, Integer> product = this.controllerUtility.convertToProduct(buyProductDTO);
                productsToBuy.add(product);
            }

            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = this.userRepository.findByUserEmail(userEmail);

            this.productManager.buyProductRequest(user, productsToBuy);
        }
        catch (RuntimeException e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("All the products have been bought successfully.");
    }


    @PostMapping("/manageOrderState")
    public ResponseEntity<String> manageOrderState(@RequestParam long orderID,
                                   @RequestParam String newOrderState) {

        OrderState orderState = OrderState.valueOf(newOrderState);

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUserEmail(userEmail);

        Order order = this.orderRepository.findByOrderIDAndUser(orderID, user.getUserID()).orElse(null);
        try {
            this.orderManager.updateOrderState(user, order, orderState);
        }
        catch (RuntimeException e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body("Order state with id" + orderID + " done successfully.");
    }

}
