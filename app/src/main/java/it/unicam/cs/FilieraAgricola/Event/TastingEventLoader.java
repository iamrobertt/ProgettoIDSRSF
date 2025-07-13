package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class TastingEventLoader implements EventLoader{

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public void loadEvent(Event event) {
        TastingEvent tastingEvent = (TastingEvent) event;

        for(EventProduct eventProduct : tastingEvent.getProductList()){
            Product realProduct = this.productRepository.findById(eventProduct.getProduct().getProductID()).orElse(null);
            this.productRepository.subProductQuantity(realProduct.getProductID(), eventProduct.getProductQuantity());
        }

        this.eventRepository.save(event);
    }
}
