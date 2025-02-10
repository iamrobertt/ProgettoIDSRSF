package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductValidationState;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.List;

public class ValidateProductCommand extends Command<Product> {

    private final ProductValidationState productValidationState;
    private final ProductRepository productRepository;


    public ValidateProductCommand(User user, Product product, ProductValidationState productValidationState, ProductRepository productRepository) {
        super(user, product);
        this.productValidationState = productValidationState;
        this.productRepository = productRepository;
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
        if (this.productValidationState.equals(ProductValidationState.ACCEPTED))
            this.productRepository.updateProductState(this.user.getUserID(), this.item.getProductID(), ProductState.PRODUCT_VALIDATED);
        else
            //the product state returns to default, so the product details can be modifid and validated again
            this.productRepository.updateProductState(this.user.getUserID(), this.item.getProductID(), ProductState.PRODUCT_INSERTED);
    }
}
