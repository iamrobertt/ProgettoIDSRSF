package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BundleProductLoader implements ProductLoader {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void loadProduct(Product product) {

        BundleProduct bundleProduct = (BundleProduct) product;

        for (BundleItem bundleItem : bundleProduct.getBundleItems()) {
            Product productInBundle = bundleItem.getProduct();
            int quantityOfProductInBundle = bundleItem.getProductQuantityPerBundle();

            if (productInBundle instanceof BundleProduct nestedBundle)
                loadProduct(nestedBundle);

            //updating the quantity of the real product linked with its id, not the one inside the bundle which contains
            //quantity per bundle (not the real quantity)
            Product realProduct = this.productRepository.findById(productInBundle.getProductID()).orElse(null);
            if(realProduct == null)
                return;

            int actualQuantity = realProduct.getWarehouseProduct().getProductQuantity();
            int newProductQuantity = actualQuantity - (quantityOfProductInBundle * bundleProduct.getWarehouseProduct().getProductQuantity());


            realProduct.getWarehouseProduct().setProductQuantity(newProductQuantity);
            this.productRepository.save(realProduct);

        }

        this.productRepository.save(bundleProduct);
    }
}
