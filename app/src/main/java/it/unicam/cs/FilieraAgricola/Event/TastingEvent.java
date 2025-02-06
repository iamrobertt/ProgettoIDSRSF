package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity

public  class TastingEvent extends Event{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventID")

    protected List<Product> productList;

    public TastingEvent(List<Product> productList){this.productList = productList;}

    public List<Product> getProductList() {return productList;}
    public void setProductList(List<Product> productList) {this.productList = productList;}
}
