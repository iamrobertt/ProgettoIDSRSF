package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/insertProduct")
    public String insertProduct(@RequestBody ProductDTO productDTO) {
        Product product = convertToProduct(productDTO);

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
    public String findProduct(@PathVariable("productID") int productID) {

        Optional<ProductDTO> product1 = this.productRepository.findById(productID);

        if(product1.isPresent())
            return product1.get().getProductName();
        else
            return "porcodio";
    }


    private Product convertToProduct(ProductDTO productDTO) {
        if (productDTO.getBundleProducts() != null && !productDTO.getBundleProducts().isEmpty()) {
            List<Product> products = productDTO.getBundleProducts()
                    .stream()
                    .map(this::convertToProduct)
                    .collect(Collectors.toList());

            return new BundleProduct(
                    0,
                    productDTO.getProductName(),
                    productDTO.getProductDescription(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    ProductState.PRODUCT_INSERTED,
                    products
            );
        }

        // Altrimenti è un SingleProduct
        return new SingleProduct(
                0,
                productDTO.getProductName(),
                productDTO.getProductDescription(),
                productDTO.getProductPrice(),
                productDTO.getProductQuantity(),
                ProductState.PRODUCT_INSERTED
        );
    }

}
