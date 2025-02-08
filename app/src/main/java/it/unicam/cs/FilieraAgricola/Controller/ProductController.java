package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
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
    private ProductRepository productRepository;

    @PostMapping("/insertProduct")
    public String insertProduct(@RequestBody ProductDTO productDTO) {

        ControllerUtility controllerUtility = new ControllerUtility();
        Product product = controllerUtility.convertToProduct(productDTO);

        List<UserRole> userRole = new ArrayList<UserRole>();
        userRole.add(UserRole.SELLER);

        User user = new User(
                1,
                "ciao",
                "ciao",
                "ciao",
                "ciao",
                123456,
                userRole,
                UserState.AUTHENTICATED
        );

        this.productManager.loadProductRequest(user, product);

        return "ciao";
    }


    @GetMapping("/findProduct/{productID}")
    public String findProduct(@PathVariable long productID) {

        Optional<Product> product1 = this.productRepository.findById(productID);

        if(product1.isPresent())
            return product1.get().getProductName();
        else
            return "ciao";
    }


}
