package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingleProductLoader implements ProductLoader {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void loadProduct(Product product) {
        this.productRepository.save(product);
    }
}
