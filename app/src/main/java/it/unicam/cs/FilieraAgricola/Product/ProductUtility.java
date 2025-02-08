package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductUtility {

    @Autowired
    private ProductRepository productRepository;


    public boolean checkProductInfo(Product product) {
        return product != null && product.getProductID() != 0;
    }


    public boolean checkExistProduct(User user, Product product) {

        Optional<Product> productToSearch = this.productRepository.findById(product.getProductID());

        return productToSearch.isPresent();
    }


    public boolean checkIsProductBundle(Product product) {
        return product instanceof BundleProduct;
    }
}
