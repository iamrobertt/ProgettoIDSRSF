import it.unicam.cs.FilieraAgricola.CheckStrategy.BuyProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.LoadProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.SellProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.ValidateProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.Product.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.Buffer;

public class ProductTest extends Product {

    @Autowired
    private ProductUtility productUtility;

    private ValidateProductCheckStrategy validateProductCheckStrategy;
    private SellProductCheckStrategy sellProductCheckStrategy;
    private LoadProductCheckStrategy loadProductCheckStrategy;
    private BuyProductCheckStrategy buyProductCheckStrategy;
    private Product product;

    public ProductTest(
        long productID,
        String productName,
        String productDescription,
        double productPrice,
        int productQuantity,
        ProductState productState,
        ProductType productType
    ){
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productState = productState;
        this.productType = productType;
    }

    ProductTest product1 = new ProductTest(1L,"penna","per scrivere",
            5.00,1,ProductState.PRODUCT_INSERTED, ProductType.SINGLE);
    ProductTest product2 = new ProductTest(1L,"","",0,
            1,ProductState.PRODUCT_TO_VALIDATE,ProductType.BUNDLE);
}
