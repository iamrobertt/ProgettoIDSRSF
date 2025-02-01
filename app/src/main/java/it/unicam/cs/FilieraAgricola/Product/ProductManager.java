package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.CheckStrategy.CheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.LoadProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.User.User;

public class ProductManager {

    private static final ProductManager instance = null;

    private ProductManager() {}

    public static ProductManager getInstance() {
        if(instance == null) return new ProductManager();

        return instance;
    }

    public void loadProductRequest(User user, Product product) {

        CheckStrategy<Product> strategy = new LoadProductCheckStrategy(product);

        if(!strategy.validate())
            throw new IllegalArgumentException("Product non valid for loading");



    }

    public void sellProductRequest(User user, Product product) {

    }

    public void buyProductRequest(User user, Product product) {

    }

    public void validateProductRequest(User user, Product product) {

    }

}
