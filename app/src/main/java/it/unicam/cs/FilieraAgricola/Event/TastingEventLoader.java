package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class TastingEventLoader implements EventLoader{

    @Autowired
    //private EventRepository eventRepository;
    private ProductRepository productRepository;

    @Transactional
    public void loadEvent(Product product) {
        this.productRepository.save(product);
    }
    @Override
    public void loadEvent(Event event) {

    }
}
