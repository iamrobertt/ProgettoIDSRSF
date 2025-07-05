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

    User user1 = new User(1L, "", "", "","",
            "", UserRole.GENERIC_USER, UserState.VALIDATED);
    User user2 = new User(1L, "", "", "","",
            "", UserRole.GENERIC_USER, UserState.WAITING_FOR_VALIDATION);
    User user3 = new User(1L, "a", "b", "c","d",
            "e", UserRole.GENERIC_USER, UserState.VALIDATED);
    User user4 = new User(1L, "a", "b", "c","d",
            "e", UserRole.GENERIC_USER, UserState.WAITING_FOR_VALIDATION);
    User user5 = new User(-1, "", "", "","",
            "", UserRole.GENERIC_USER, UserState.VALIDATED);
    User user6 = new User(-1, "", "", "","",
            "", UserRole.GENERIC_USER, UserState.WAITING_FOR_VALIDATION);
    User user7 = new User(-1, "a", "b", "c","d",
            "e", UserRole.GENERIC_USER, UserState.VALIDATED);
    User user8 = new User(-1, "a", "b", "c","d",
            "e", UserRole.GENERIC_USER, UserState.WAITING_FOR_VALIDATION);

    User user9 = new User(1L, "", "", "","",
            "", UserRole.CUSTOMER, UserState.VALIDATED);
    User user10 = new User(1L, "", "", "","",
            "", UserRole.CUSTOMER, UserState.WAITING_FOR_VALIDATION);
    User user11 = new User(1L, "a", "b", "c","d",
            "e", UserRole.CUSTOMER, UserState.VALIDATED);
    User user12 = new User(1L, "a", "b", "c","d",
            "e", UserRole.CUSTOMER, UserState.WAITING_FOR_VALIDATION);
    User user13 = new User(-1, "", "", "","",
            "", UserRole.CUSTOMER, UserState.VALIDATED);
    User user14 = new User(-1, "", "", "","",
            "", UserRole.CUSTOMER, UserState.WAITING_FOR_VALIDATION);
    User user15 = new User(-1, "a", "b", "c","d",
            "e", UserRole.CUSTOMER, UserState.VALIDATED);
    User user16 = new User(-1, "a", "b", "c","d",
            "e", UserRole.CUSTOMER, UserState.WAITING_FOR_VALIDATION);


    User user17 = new User(1L, "", "", "","",
            "", UserRole.SELLER, UserState.VALIDATED);
    User user18 = new User(1L, "", "", "","",
            "", UserRole.SELLER, UserState.WAITING_FOR_VALIDATION);
    User user19 = new User(1L, "a", "b", "c","d",
            "e", UserRole.SELLER, UserState.VALIDATED);
    User user20 = new User(1L, "a", "b", "c","d",
            "e", UserRole.SELLER, UserState.WAITING_FOR_VALIDATION);
    User user21 = new User(-1, "", "", "","",
            "", UserRole.SELLER, UserState.VALIDATED);
    User user22 = new User(-1, "", "", "","",
            "", UserRole.SELLER, UserState.WAITING_FOR_VALIDATION);
    User user23 = new User(-1, "a", "b", "c","d",
            "e", UserRole.SELLER, UserState.VALIDATED);
    User user24 = new User(-1, "a", "b", "c","d",
            "e", UserRole.SELLER, UserState.WAITING_FOR_VALIDATION);


    User user25 = new User(1L, "", "", "","",
            "", UserRole.PRODUCER, UserState.VALIDATED);
    User user26 = new User(1L, "", "", "","",
            "", UserRole.PRODUCER, UserState.WAITING_FOR_VALIDATION);
    User user27 = new User(1L, "a", "b", "c","d",
            "e", UserRole.PRODUCER, UserState.VALIDATED);
    User user28 = new User(1L, "a", "b", "c","d",
            "e", UserRole.PRODUCER, UserState.WAITING_FOR_VALIDATION);
    User user29 = new User(-1, "", "", "","",
            "", UserRole.PRODUCER, UserState.VALIDATED);
    User user30 = new User(-1, "", "", "","",
            "", UserRole.PRODUCER, UserState.WAITING_FOR_VALIDATION);
    User user31 = new User(-1, "a", "b", "c","d",
            "e", UserRole.PRODUCER, UserState.VALIDATED);
    User user32 = new User(-1, "a", "b", "c","d",
            "e", UserRole.PRODUCER, UserState.WAITING_FOR_VALIDATION);

    User user33 = new User(1L, "", "", "","",
            "", UserRole.VALIDATOR, UserState.VALIDATED);
    User user34 = new User(1L, "", "", "","",
            "", UserRole.VALIDATOR, UserState.WAITING_FOR_VALIDATION);
    User user35 = new User(1L, "a", "b", "c","d",
            "e", UserRole.VALIDATOR, UserState.VALIDATED);
    User user36 = new User(1L, "a", "b", "c","d",
            "e", UserRole.VALIDATOR, UserState.WAITING_FOR_VALIDATION);
    User user37 = new User(-1, "", "", "","",
            "", UserRole.VALIDATOR, UserState.VALIDATED);
    User user38 = new User(-1, "", "", "","",
            "", UserRole.VALIDATOR, UserState.WAITING_FOR_VALIDATION);
    User user39 = new User(-1, "a", "b", "c","d",
            "e", UserRole.VALIDATOR, UserState.VALIDATED);
    User user40 = new User(-1, "a", "b", "c","d",
            "e", UserRole.VALIDATOR, UserState.WAITING_FOR_VALIDATION);

    User user41 = new User(1L, "", "", "","",
            "", UserRole.ADMINISTRATOR, UserState.VALIDATED);
    User user42 = new User(1L, "", "", "","",
            "", UserRole.ADMINISTRATOR, UserState.WAITING_FOR_VALIDATION);
    User user43 = new User(1L, "a", "b", "c","d",
            "e", UserRole.ADMINISTRATOR, UserState.VALIDATED);
    User user44 = new User(1L, "a", "b", "c","d",
            "e", UserRole.ADMINISTRATOR, UserState.WAITING_FOR_VALIDATION);
    User user45 = new User(-1, "", "", "","",
            "", UserRole.ADMINISTRATOR, UserState.VALIDATED);
    User user46 = new User(-1, "", "", "","",
            "", UserRole.ADMINISTRATOR, UserState.WAITING_FOR_VALIDATION);
    User user47 = new User(-1, "a", "b", "c","d",
            "e", UserRole.ADMINISTRATOR, UserState.VALIDATED);
    User user48 = new User(-1, "a", "b", "c","d",
            "e", UserRole.ADMINISTRATOR, UserState.WAITING_FOR_VALIDATION);

    User user49 = new User(1L, "", "", "","",
            "", UserRole.DISTRIBUTOR, UserState.VALIDATED);
    User user50 = new User(1L, "", "", "","",
            "", UserRole.DISTRIBUTOR, UserState.WAITING_FOR_VALIDATION);
    User user51 = new User(1L, "a", "b", "c","d",
            "e", UserRole.DISTRIBUTOR, UserState.VALIDATED);
    User user52 = new User(1L, "a", "b", "c","d",
            "e", UserRole.DISTRIBUTOR, UserState.WAITING_FOR_VALIDATION);
    User user53 = new User(-1, "", "", "","",
            "", UserRole.DISTRIBUTOR, UserState.VALIDATED);
    User user54 = new User(-1, "", "", "","",
            "", UserRole.DISTRIBUTOR, UserState.WAITING_FOR_VALIDATION);
    User user55 = new User(-1, "a", "b", "c","d",
            "e", UserRole.DISTRIBUTOR, UserState.VALIDATED);
    User user56 = new User(-1, "a", "b", "c","d",
            "e", UserRole.DISTRIBUTOR, UserState.WAITING_FOR_VALIDATION);

    User user57 = new User(1L, "", "", "","",
            "", UserRole.MANUFACTURER, UserState.VALIDATED);
    User user58 = new User(1L, "", "", "","",
            "", UserRole.MANUFACTURER, UserState.WAITING_FOR_VALIDATION);
    User user59 = new User(1L, "a", "b", "c","d",
            "e", UserRole.MANUFACTURER, UserState.VALIDATED);
    User user60 = new User(1L, "a", "b", "c","d",
            "e", UserRole.MANUFACTURER, UserState.WAITING_FOR_VALIDATION);
    User user61 = new User(-1, "", "", "","",
            "", UserRole.MANUFACTURER, UserState.VALIDATED);
    User user62 = new User(-1, "", "", "","",
            "", UserRole.MANUFACTURER, UserState.WAITING_FOR_VALIDATION);
    User user63 = new User(-1, "a", "b", "c","d",
            "e", UserRole.MANUFACTURER, UserState.VALIDATED);
    User user64 = new User(-1, "a", "b", "c","d",
            "e", UserRole.MANUFACTURER, UserState.WAITING_FOR_VALIDATION);

    User user65 = new User(1L, "", "", "","",
            "", UserRole.PROMOTER, UserState.VALIDATED);
    User user66 = new User(1L, "", "", "","",
            "", UserRole.PROMOTER, UserState.WAITING_FOR_VALIDATION);
    User user67 = new User(1L, "a", "b", "c","d",
            "e", UserRole.PROMOTER, UserState.VALIDATED);
    User user68 = new User(1L, "a", "b", "c","d",
            "e", UserRole.PROMOTER, UserState.WAITING_FOR_VALIDATION);
    User user69 = new User(-1, "", "", "","",
            "", UserRole.PROMOTER, UserState.VALIDATED);
    User user70 = new User(-1, "", "", "","",
            "", UserRole.PROMOTER, UserState.WAITING_FOR_VALIDATION);
    User user71 = new User(-1, "a", "b", "c","d",
            "e", UserRole.PROMOTER, UserState.VALIDATED);
    User user72 = new User(-1, "a", "b", "c","d",
            "e", UserRole.PROMOTER, UserState.WAITING_FOR_VALIDATION);



    @Test
    public void validateProduct(){
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product2,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product3,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product4,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product5,ProductValidationState.ACCEPTED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user1,product6,ProductValidationState.ACCEPTED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user1,product7,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product8,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product9,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product10,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product11,ProductValidationState.ACCEPTED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user1,product12,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product2,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product3,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product4,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product5,ProductValidationState.DENIED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user1,product6,ProductValidationState.DENIED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user1,product7,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product8,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product9,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product10,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user1,product11,ProductValidationState.DENIED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user1,product12,ProductValidationState.DENIED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product1,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product2,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product3,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product4,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product5,ProductValidationState.ACCEPTED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user2,product6,ProductValidationState.ACCEPTED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user2,product7,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product8,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product9,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product10,ProductValidationState.ACCEPTED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product11,ProductValidationState.ACCEPTED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user2,product12,ProductValidationState.ACCEPTED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product1,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product2,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product3,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product4,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product5,ProductValidationState.DENIED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user2,product6,ProductValidationState.DENIED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user2,product7,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product8,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product9,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product10,ProductValidationState.DENIED));
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user2,product11,ProductValidationState.DENIED));
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user2,product12,ProductValidationState.DENIED));

        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user3,product1,ProductValidationState.ACCEPTED));






    }

    @Test
    public void valideteSellProduct(){
        assertThrows(IllegalStateException.class, () -> sellProductCheckStrategy.validate(user1,product2));

    }

}
