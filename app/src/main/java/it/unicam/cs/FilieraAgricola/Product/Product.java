package it.unicam.cs.FilieraAgricola.Product;

public abstract class Product {

    private int productID;

    private String productName;

    private String productDescription;

    private double productPrice;

    private int productQuantity;

    private ProductState ProductState;

    Product(int productID,
            String productName,
            String productDescription,
            double productPrice,
            int productQuantity,
            ProductState ProductState
    ) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.ProductState = ProductState;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public ProductState getProductState() {
        return ProductState;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductState(ProductState ProductState) {
        this.ProductState = ProductState;
    }

    public boolean isBundle(){
        return this instanceof BundleProduct;
    }
}
