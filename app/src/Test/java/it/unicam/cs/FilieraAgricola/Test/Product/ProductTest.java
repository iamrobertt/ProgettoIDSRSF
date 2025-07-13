package it.unicam.cs.FilieraAgricola.Test.Product;

import it.unicam.cs.FilieraAgricola.CheckStrategy.BuyProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.LoadProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.SellProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.ValidateProductCheckStrategy;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest extends Product {

    @Autowired
    private ProductUtility productUtility;
    @Autowired
    private UserUtility userUtility;

    private ValidateProductCheckStrategy validateProductCheckStrategy;
    private SellProductCheckStrategy sellProductCheckStrategy;
    private LoadProductCheckStrategy loadProductCheckStrategy;
    private BuyProductCheckStrategy buyProductCheckStrategy;
    private Product product;
    private User user;




    @Test
    public void validateProduct(){

        Product  product = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5,ProductState.PRODUCT_TO_VALIDATE,ProductType.SINGLE);
        User user = new User(2,"Franco","Accia","prova2@prova.prova","juventus12345","1234567", UserRole.VALIDATOR,UserState.VALIDATED);

        // mi aspetto un errore perchè il prodotto è nullo
        assertThrows(IllegalArgumentException.class,()-> validateProductCheckStrategy.validate(user,null,ProductValidationState.ACCEPTED));

        // mi aspetto che mi di errore perchè lo stato della validazione non esiste
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,null));

        //mi aspetto un errore poichè il tipo di prodotto è nullo
        product.setProductType(null);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));

        // mi aspetto un errore poichè ho creato un single product per poi dirgli che è un bundle product
        product.setProductType(ProductType.BUNDLE);
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));

        // mi asptto un errore poichè il ruolo dell'utente non ha i permessi corrretti per validare il prodotto
        user.setUserRole(UserRole.GENERIC_USER);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.SELLER);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.CUSTOMER);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.PROMOTER);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.PRODUCER);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.ADMINISTRATOR);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.MANUFACTURER);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));
        user.setUserRole(UserRole.DISTRIBUTOR);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));

        // mi aspetto un errore poichè mancano le info dell'user
        user.setUserRole(UserRole.VALIDATOR);
        user.setUserName(null);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));

        // mi aspetto un errore poichè mancano le info sul prodotto
        user.setUserName("Franco");
        product.setProductName(null);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));

        // mi aspetto un errore poichè non esiste quel prodotto
        product.setProductName("Anatra5");
        product.setProductID(50);
        assertThrows(IllegalArgumentException.class, () -> validateProductCheckStrategy.validate(user,product,ProductValidationState.ACCEPTED));


        Product singleProduct = new SingleProduct(66,"Vodka","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
        Product singleProduct1 = new SingleProduct(66,"Gin","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
        Product singleProduct2 = new SingleProduct(66,"Rum","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);

        List<BundleItem> items = new ArrayList<>();

        BundleProduct product1 = new BundleProduct(72,"superV2", "SuperAlcolici V2", 60, 3, ProductState.PRODUCT_INSERTED,ProductType.BUNDLE, items);

        items.add(new BundleItem(product1,singleProduct,3));
        items.add(new BundleItem(product1,singleProduct1,3));
        items.add(new BundleItem(product1,singleProduct2,3));


        // mi aspetto un errore perchè basta che un solo prodotto contenuto nel bundle abbia come come stato product to validate per generare errori
        singleProduct1.setProductState(ProductState.PRODUCT_TO_VALIDATE);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product1,ProductValidationState.ACCEPTED));

        // mi aspetto un errore perchè l'id non esiste
        singleProduct1.setProductState(ProductState.PRODUCT_VALIDATED);
        product1.setProductID(-1);
        assertThrows(IllegalStateException.class, () -> validateProductCheckStrategy.validate(user,product1,ProductValidationState.ACCEPTED));

    }

    @Test
    public void valideteSellProduct(){
        Product  product = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5,ProductState.PRODUCT_TO_VALIDATE,ProductType.SINGLE);
        User user = new User(2,"Franco","Accia","prova2@prova.prova","juventus12345","1234567", UserRole.SELLER,UserState.VALIDATED);

        // mi aspetto che mi dia errore poichè user e prodotto sono nulli
        assertThrows(IllegalArgumentException.class, ()->sellProductCheckStrategy.validate(null, product));
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,null));

        // mi aspetto che mi dia errore poichè i ruoli degli utenti non sono abilitati alla vendita
        user.setUserRole(UserRole.CUSTOMER);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.GENERIC_USER);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.VALIDATOR);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.DISTRIBUTOR);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.MANUFACTURER);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.ADMINISTRATOR);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.PRODUCER);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.PROMOTER);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        user.setUserRole(UserRole.SELLER);

        // mi aspetto che mi dia errore poichè lo statto dei prodotti non è INSERITO
        product.setProductState(ProductState.PRODUCT_TO_VALIDATE);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        product.setProductState(ProductState.PRODUCT_NOT_VALIDATED);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));
        product.setProductState(ProductState.PRODUCT_VALIDATED);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        product.setProductState(ProductState.PRODUCT_INSERTED);

        // mi aspetto che mi dia errore poichè l'uetnete non esiste
        user.setUserID(-1);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        user.setUserID(2);

        //mi aspetto che mi da errore poichè il prodotto non esiste
        product.setProductID(-1);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        // mi aspetto che mi dia errore poichè user e product non esistono
        user.setUserID(-1);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        user.setUserID(2);
        product.setProductID(66);

        // mi aspetto un errore poichè mancano le info dell'utente
        user.setUserName(null);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        // mi aspetto un errore poichè mancano le info su user e product
        product.setProductName(null);
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        //mi aspetto un errore poichè le info del prodotto sono errate
        user.setUserName("Franco");
        assertThrows(IllegalStateException.class, ()->sellProductCheckStrategy.validate(user,product));

        product.setProductName("Anatra5");

        Product singleProduct = new SingleProduct(66,"Vodka","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
        Product singleProduct1 = new SingleProduct(66,"Gin","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
        Product singleProduct2 = new SingleProduct(66,"Rum","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);

        List<BundleItem> items = new ArrayList<>();

        BundleProduct product1 = new BundleProduct(72,"superV2", "SuperAlcolici V2", 60, 3, ProductState.PRODUCT_INSERTED,ProductType.BUNDLE, items);

        items.add(new BundleItem(product1,singleProduct,3));
        items.add(new BundleItem(product1,singleProduct1,3));
        items.add(new BundleItem(product1,singleProduct2,3));


        // mi aspetto un errore perchè basta che un solo prodotto contenuto nel bundle abbia come come stato product to validate per generare errori
        singleProduct1.setProductState(ProductState.PRODUCT_TO_VALIDATE);
        assertThrows(IllegalStateException.class, () -> sellProductCheckStrategy.validate(user,product1));
        singleProduct1.setProductState(ProductState.PRODUCT_INSERTED);
        assertThrows(IllegalStateException.class, () -> sellProductCheckStrategy.validate(user,product1));
        singleProduct1.setProductState(ProductState.PRODUCT_NOT_VALIDATED);
        assertThrows(IllegalStateException.class, () -> sellProductCheckStrategy.validate(user,product1));

        // mi aspetto un errore perchè l'id non esiste
        singleProduct1.setProductState(ProductState.PRODUCT_VALIDATED);
        product1.setProductID(-1);
        assertThrows(IllegalStateException.class, () -> sellProductCheckStrategy.validate(user,product1));

    }

    @Test
    public void validateBoughtProduct() {
        Product  product = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5,ProductState.PRODUCT_TO_VALIDATE,ProductType.SINGLE);
        User user = new User(2,"Franco","Accia","prova2@prova.prova","juventus12345","1234567", UserRole.CUSTOMER,UserState.VALIDATED);

        // mi aspetto un errore poichè prodotto è nullo
        assertThrows(IllegalArgumentException.class, ()->buyProductCheckStrategy.validate(user,null,2));

        //mi aspetto un errore poichè user è null
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(null,product,2));

        //mi aspetto un errore poichè la quantità è < 1
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,0));

        //mi aspetto che mi dia errore poichè utente non ha il ruolo per comprare
        user.setUserRole(UserRole.GENERIC_USER);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.SELLER);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.VALIDATOR);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.PROMOTER);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.PRODUCER);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.ADMINISTRATOR);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.MANUFACTURER);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        user.setUserRole(UserRole.DISTRIBUTOR);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        user.setUserRole(UserRole.CUSTOMER);

        //mi aspetto che mi dia errore poichè lo stato del prodotto non è corretto
        product.setProductState(ProductState.PRODUCT_TO_VALIDATE);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        product.setProductState(ProductState.PRODUCT_INSERTED);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));
        product.setProductState(ProductState.PRODUCT_NOT_VALIDATED);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        product.setProductState(ProductState.PRODUCT_VALIDATED);

        //mi aspetto che mi dia errore poichè la quantià richiesta è maggiore rispetto a quella reale
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,6));

        // mi aspetto che mi dia errore poichè l'uetnete non esiste
        user.setUserID(-1);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        user.setUserID(2);

        //mi aspetto che mi da errore poichè il prodotto non esiste
        product.setProductID(-1);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        // mi aspetto che mi dia errore poichè user e product non esistono
        user.setUserID(-1);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        user.setUserID(2);
        product.setProductID(66);

        // mi aspetto un errore poichè mancano le info dell'utente
        user.setUserName(null);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        // mi aspetto un errore poichè mancano le info su user e product
        product.setProductName(null);
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

        //mi aspetto un errore poichè le info del prodotto sono errate
        user.setUserName("Franco");
        assertThrows(IllegalStateException.class, ()->buyProductCheckStrategy.validate(user,product,2));

    }

    @Test public void validateLoadProduct(){
        Product  product = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5,ProductState.PRODUCT_TO_VALIDATE,ProductType.SINGLE);
        User user = new User(2,"Franco","Accia","prova2@prova.prova","juventus12345","1234567", UserRole.SELLER,UserState.VALIDATED);

        //mi aspetto un errore, utente non valido
        assertThrows(IllegalStateException.class, ()-> loadProductCheckStrategy.validate(null,product));

        //mi aspetto un errore, prodotto non valido
        assertThrows(IllegalStateException.class, ()-> loadProductCheckStrategy.validate(user,null));


        // Mi aspetto un errore perchè la quantità in magazzino è inferiore a quella richiesta
        WarehouseProduct whereasProduct = new WarehouseProduct(product,4);
        assertThrows(IllegalArgumentException.class, () -> loadProductCheckStrategy.validate(user, whereasProduct.getProduct()));

        //mi aspetto un errore perchè la quantità in magazzino è < 1
        whereasProduct.setProductQuantity(0);
        assertThrows(IllegalArgumentException.class, ()->loadProductCheckStrategy.validate(user, whereasProduct.getProduct()));

        whereasProduct.setProductQuantity(5);

        //MI aspetto un errore perchè il ruolo
        user.setUserRole(UserRole.CUSTOMER);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.VALIDATOR);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.PROMOTER);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.GENERIC_USER);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.PRODUCER);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.DISTRIBUTOR);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.MANUFACTURER);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));
        user.setUserRole(UserRole.ADMINISTRATOR);
        assertThrows(IllegalStateException.class, ()->loadProductCheckStrategy.validate(user,product));

        // mi aspetto che mi dia errore poichè le info del prodotto sono nulle
        user.setUserSurname(null);
        assertThrows(IllegalStateException.class, ()-> userUtility.checkUserInfo(user));

        user.setUserSurname("Accia");

        //mi aspetto che mi dia errore poichè le info sono errate
        product.setProductDescription(null);
        assertThrows(IllegalStateException.class, ()->productUtility.checkProductInfo(product));

        product.setProductDescription("pera Cotogna");

        //mi aspetto che mi dia errore poichè sono stati gia caricati
        assertThrows(IllegalStateException.class, ()-> productUtility.checkExistProductWithUser(user,product));

    }

}
