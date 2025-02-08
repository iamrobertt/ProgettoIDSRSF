package it.unicam.cs.FilieraAgricola.Manager;

import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.Command.*;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductLoaderFactory;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductManager {

    @Autowired
    private LoadProductCheckStrategy loadProductCheckStrategy;
    @Autowired
    private SellProductCheckStrategy sellProductCheckStrategy;
    @Autowired
    private BuyProductCheckStrategy buyProductCheckStrategy;
    @Autowired
    private ValidateProductCheckStrategy validateProductCheckStrategy;
    @Autowired
    private ProductLoaderFactory productLoaderFactory;

    public void loadProductRequest(User user, Product product) {

        if(!this.loadProductCheckStrategy.validate(user, product))
            throw new IllegalArgumentException("Product non valid for loading");

        Command<Product> loadProductCommand = new LoadProductCommand(user, product, this.productLoaderFactory);

        //if(loadProductCommand.hasCallerNeededAuthorization())

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(loadProductCommand);
        invoker.invoke();

    }

    public void sellProductRequest(User user, Product product) {

        if(!this.sellProductCheckStrategy.validate(user, product))
            throw new IllegalArgumentException("Product non valid for selling");

        Command<Product> sellProductCommand = new SellProductCommand(user, product);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(sellProductCommand);
        invoker.invoke();

    }


    public void buyProductRequest(User user, Product product) {

        if(!this.buyProductCheckStrategy.validate(user, product))
            throw new IllegalArgumentException("Product non valid for buying");

        Command<Product> buyProductCommand = new BuyProductCommand(user, product);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(buyProductCommand);
        invoker.invoke();
    }


    public void validateProductRequest(User user, Product product, ProductState newProductState) {

        if(!this.validateProductCheckStrategy.validate(user, product, newProductState))
            throw new IllegalArgumentException("Product non valid for buying");

        Command<Product> validateProductCommand = new ValidateProductCommand(user, product, newProductState);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(validateProductCommand);
        invoker.invoke();
    }

}
