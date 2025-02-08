package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.List;

public class ValidateProductCommand extends Command<Product> {

    private final ProductState productState;

    public ValidateProductCommand(User user, Product product, ProductState newProductState) {
        super(user, product);
        this.productState = newProductState;
    }


    @Override
    public List<UserRole> getNeededAuthorization() {
        return List.of(UserRole.VALIDATOR);
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        //TODO aggiorna stato
        if (this.productState.equals(ProductState.PRODUCT_VALIDATED))
                return;
        else
            return;
    }
}
