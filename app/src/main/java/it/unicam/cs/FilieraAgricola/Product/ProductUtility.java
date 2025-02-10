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

    public boolean checkProductInfoForLoading(Product product) {
        return product != null;
    }

    public boolean checkExistProductWithUser(User user, Product product) {
        Optional<Product> productToSearch = this.productRepository.findByProductIDAndUser(user.getUserID(), product.getProductID());
        return productToSearch.isPresent();
    }


    public boolean checkExistProduct(Product product) {
        Optional<Product> productToSearch = this.productRepository.findById(product.getProductID());
        return productToSearch.isPresent();
    }

    public Product getProduct(long userID, long productID) {
        Optional<Product> product = this.productRepository.findByProductIDAndUser(userID, productID);
        return product.orElse(null);
    }

}
