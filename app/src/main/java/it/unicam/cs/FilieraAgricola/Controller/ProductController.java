package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.Manager.ProductManager;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductManager productManager;


    @PostMapping("/insertProduct")
    public String insertProduct(@RequestBody ProductDTO productDTO) {

        ControllerUtility controllerUtility = new ControllerUtility();
        Product product = controllerUtility.convertToProduct(productDTO);

        UserRole userRole = UserRole.SELLER;

        User user = new User(
                1,
                "ciao",
                "ciao",
                "ciao",
                "ciao",
                "123456",
                userRole,
                UserState.AUTHENTICATED
        );

        this.productManager.loadProductRequest(user, product);

        return "caio";
    }


//    @GetMapping("/findProduct/{productID}")
//    public String findProduct(@PathVariable long productID) {
//
//        Optional<Product> product1 = this.productRepository.findById(productID);
//
//        if(product1.isPresent())
//            return product1.get().getProductName();
//        else
//            return "ciao";
//    }


}
