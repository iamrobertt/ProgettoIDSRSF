package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.BuyProductDTO;
import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.Manager.ProductManager;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ControllerUtility controllerUtility;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUtility productUtility;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/insertProduct")
    public String insertProduct(@RequestBody ProductDTO productDTO) {

        Product product = this.controllerUtility.convertToProduct(productDTO);

        UserRole userRole = UserRole.SELLER;


        User user = new User(
                3,
                "ciao",
                "ciao",
                "ciao",
                "ciao",
                "123456",
                userRole,
                UserState.AUTHENTICATED
        );

        product.setProductUser(user);
        this.productManager.loadProductRequest(user, product);

        return "caio";
    }


    @GetMapping("/findProduct/{productID}")
    public String findProduct(@PathVariable long productID) {

        Optional<Product> product1 = this.productRepository.findById(productID);

        if(product1.isPresent())
            return product1.get().getProductName();
        else
            return "ciao";
    }

    @PostMapping("/sellProduct")
    public String sellProduct(@RequestParam long productID) {

        Optional<User> user = this.userRepository.findById(3L);

        //todo rivedi con vero utente
        Product product = this.productUtility.getProduct(user.get().getUserID(), productID);

        if(product == null)
            return "Product not found associated with your user";

        product.setProductUser(user.get());
        this.productManager.sellProductRequest(user.get(), product);

        return "caio";
    }

    @PostMapping("/validateProduct")
    public String validateProduct(@RequestParam long productID, @RequestParam String validationState) {

        ProductValidationState productValidationState = ProductValidationState.valueOf(validationState);

        Optional<User> user = this.userRepository.findById(3L);

        //todo rivedi con vero utente
        Product product = this.productUtility.getProduct(user.get().getUserID(), productID);

        if(product == null)
            return "Product not found associated with your user";

        //todo rivedi se funziona
        this.productManager.validateProductRequest(user.get(), product, productValidationState);

        return "caio";
    }

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestBody List<BuyProductDTO> buyProductDTOList) {

        List<Product> productsToBuy = new ArrayList<>();

        for (BuyProductDTO buyProductDTO : buyProductDTOList)
            productsToBuy.add(this.controllerUtility.convertToProduct(buyProductDTO));

        Optional<User> user = this.userRepository.findById(3L);

        this.productManager.buyProductRequest(user.get(), productsToBuy);

        return "caio";
    }


}
