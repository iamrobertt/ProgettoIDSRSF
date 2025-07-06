package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.CheckStrategy.BuyProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.LoadProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.SellProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.ValidateProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest extends Product {

    @Autowired
    private ProductUtility productUtility;

    private ValidateProductCheckStrategy validateProductCheckStrategy;
    private SellProductCheckStrategy sellProductCheckStrategy;
    private LoadProductCheckStrategy loadProductCheckStrategy;
    private BuyProductCheckStrategy buyProductCheckStrategy;
    private Product product;
    private User user;

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

    ProductTest product1 = new ProductTest(1L,"","",
            0,0,ProductState.PRODUCT_NOT_VALIDATED,null);
    ProductTest product2 = new ProductTest(1L,"","",
            0,0,ProductState.PRODUCT_INSERTED,null);
    ProductTest product3 = new ProductTest(1L,"","",
            0,0,ProductState.PRODUCT_VALIDATED,null);

    ProductTest product4 = new ProductTest(-1,"a","b",0.1,
            1,ProductState.PRODUCT_INSERTED,ProductType.SINGLE);
    ProductTest product5 = new ProductTest(-1,"a","b",0.1,
            1,ProductState.PRODUCT_NOT_VALIDATED,ProductType.SINGLE);
    ProductTest product6 = new ProductTest(-1,"a","b",0.1,
            1,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);

    ProductTest product7 = new ProductTest(1L,"a","b",0.1,
            1,ProductState.PRODUCT_INSERTED,ProductType.SINGLE);
    ProductTest product8 = new ProductTest(1L,"a","b",0.1,
            1,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
    ProductTest product9 = new ProductTest(1L,"a","b",0.1,
            1,ProductState.PRODUCT_NOT_VALIDATED,ProductType.SINGLE);

    ProductTest product10 = new ProductTest(1,"","",0.0,
            0,ProductState.PRODUCT_NOT_VALIDATED,ProductType.SINGLE);
    ProductTest product11 = new ProductTest(1,"","",0.0,
            0,ProductState.PRODUCT_INSERTED,ProductType.SINGLE);
    ProductTest product12 = new ProductTest(1,"","",0.0,
            0,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);



    @Test
    public void validateProduct(){

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

    }

    @Test
    public void valideteSellProduct(){

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

    }

    @Test
    public void validateBoughtProduct() {
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.SELLER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));
    }

    @Test public void valodateLoadProduct(){

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PROMOTER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.DISTRIBUTOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.MANUFACTURER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.VALIDATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.ADMINISTRATOR,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.PRODUCER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.VALIDATED),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.VALIDATED),product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(new User(1L,"","",""
                ,"","",UserRole.CUSTOMER,UserState.WAITING_FOR_VALIDATION),product1,ProductValidationState.ACCEPTED));
    }


}
