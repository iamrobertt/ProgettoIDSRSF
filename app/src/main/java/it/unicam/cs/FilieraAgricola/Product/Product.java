package it.unicam.cs.FilieraAgricola.Product;

public abstract class Product {

    protected int productID;

    protected String productName;

    protected String productDescription;

    protected double productPrice;

    protected int productQuantity;

    protected ProductState productState;

    Product(int productID,
            String productName,
            String productDescription,
            double productPrice,
            int productQuantity,
            ProductState productState
    ) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productState = productState;
    }

    public int getProductID() { return productID; }

    public void setProductID(int productID) { this.productID = productID; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public String getProductDescription() { return productDescription; }

    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public double getProductPrice() { return productPrice; }

    public void setProductPrice(double productPrice){ this.productPrice = productPrice; }

    public int getProductQuantity() { return productQuantity; }

    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

    public ProductState getProductState() { return productState; }

    public void setProductState(ProductState ProductState) { this.productState = ProductState; }
}
