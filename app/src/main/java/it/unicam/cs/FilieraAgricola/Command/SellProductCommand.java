package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class SellProductCommand extends Command<Product> {

    private final ProductRepository productRepository;


    public SellProductCommand(User user, Product product, ProductRepository productRepository) {
        super(user, product);
        this.productRepository = productRepository;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(UserRole.SELLER);
        return neededRoles;
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        this.productRepository.updateProductState(this.user.getUserID(), this.item.getProductID(), ProductState.PRODUCT_TO_VALIDATE);
    }
}
