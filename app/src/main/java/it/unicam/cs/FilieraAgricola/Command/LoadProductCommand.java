package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductLoader;
import it.unicam.cs.FilieraAgricola.Product.ProductLoaderFactory;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class LoadProductCommand extends Command<Product> {


    public LoadProductCommand(User user, Product product) {
        super(user, product);
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
        ProductLoaderFactory productLoaderFactory = new ProductLoaderFactory();
        ProductLoader productLoader = productLoaderFactory.getProductLoader(this.item.getClass());
        productLoader.loadProduct(this.item);
    }
}
