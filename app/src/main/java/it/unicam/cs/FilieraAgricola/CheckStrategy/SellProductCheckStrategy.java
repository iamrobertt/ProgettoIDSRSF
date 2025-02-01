package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;

public class SellProductCheckStrategy implements CheckStrategy {

    private final Product product;

    public SellProductCheckStrategy(Product product) {
        this.product = product;
    }

    @Override
    public boolean validate() {

        //if the product does not have the necessary data to be uniquely identified, return false
        if (!ProductUtility.checkProductInfo(this.product))
            return false;

        //TODO aggiorna anche sequence diagram su questa funzione aggiungendo anche l'utente
        //in questo modo non si controlla se il prodotto esiste per il dato utente, ma per qualsiasi utente
        //if the product does not exist, return false
        if (!ProductUtility.checkExistProduct(this.product))
            return false;

        //if the product isn't in a pre-sell state, return false
        if(!this.product.getProductState().equals(ProductState.PRODUCT_INSERTED))
            return false;

        return true;
    }
}
