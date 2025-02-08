package it.unicam.cs.FilieraAgricola.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private long productID;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productState;
    private String productType;
    private List<ProductDTO> bundleProducts;
}
