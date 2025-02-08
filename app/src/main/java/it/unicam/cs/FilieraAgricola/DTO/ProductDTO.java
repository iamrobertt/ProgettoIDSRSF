package it.unicam.cs.FilieraAgricola.DTO;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
public class ProductDTO {
    @Generated
    private int productID;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private List<ProductDTO> bundleProducts;
}
