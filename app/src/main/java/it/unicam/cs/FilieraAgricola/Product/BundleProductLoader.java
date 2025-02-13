package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.Repository.BundleProductRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BundleProductLoader implements ProductLoader {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BundleProductRepository bundleProductRepository;

    @Override
    public void loadProduct(Product product) {
        BundleProduct bundleProduct = (BundleProduct) product;

        //saving the bundle into the main product table
        //the bundle is set up to auto-save the products inside the bundle into the bundle table
        this.productRepository.save(bundleProduct);
    }
}
