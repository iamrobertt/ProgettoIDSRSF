package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventProduct;
import it.unicam.cs.FilieraAgricola.Event.SimpleEvent;
import it.unicam.cs.FilieraAgricola.Event.TastingEvent;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.Product.SingleProduct;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddBundleToEventCheckStrategy implements CheckStrategy<Event> {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Event event) {
        if(event instanceof SimpleEvent)
            throw new IllegalArgumentException("Event must be a tasting event to add bundles to it");

        TastingEvent tastingEvent = (TastingEvent) event;
        for(EventProduct eventProduct : tastingEvent.getProductList()){

            Product product = eventProduct.getProduct();
            if(product == null)
                throw new IllegalArgumentException("A product was not found.");

            if(!this.productUtility.checkExistProduct(product))
                throw new IllegalArgumentException("Product with id " + product.getProductID() + "does not exist.");

            if(product instanceof SingleProduct)
                throw new IllegalArgumentException("Product with id " + product.getProductID() + "is not a bundle.");

            Product realProduct = this.productUtility.getProduct(product.getProductID());
            int neededQuantity = product.getWarehouseProduct().getProductQuantity();

            if(!this.productUtility.checkProductAvailability(realProduct, neededQuantity))
                throw new IllegalArgumentException("Product with id " + product.getProductID() + "is not available.");

        }
        return true;
    }
}
