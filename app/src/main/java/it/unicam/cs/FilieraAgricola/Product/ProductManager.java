package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.Command.*;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.stereotype.Service;

@Service
public class ProductManager {

    private ProductManager() {}

    public void loadProductRequest(User user, Product product) {

        CheckStrategy loadProductStrategy = new LoadProductCheckStrategy(product);

        if(!loadProductStrategy.validate())
            throw new IllegalArgumentException("Product non valid for loading");

        Command<Product> loadProductCommand = new LoadProductCommand(user, product);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(loadProductCommand);
        invoker.invoke();

    }

    public void sellProductRequest(User user, Product product) {

        CheckStrategy sellProductStrategy = new SellProductCheckStrategy(product);

        if(!sellProductStrategy.validate())
            throw new IllegalArgumentException("Product non valid for selling");

        Command<Product> sellProductCommand = new SellProductCommand(user, product);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(sellProductCommand);
        invoker.invoke();

    }


    public void buyProductRequest(User user, Product product) {
        CheckStrategy buyProductStrategy = new BuyProductCheckStrategy(product);

        if(!buyProductStrategy.validate())
            throw new IllegalArgumentException("Product non valid for buying");

        Command<Product> buyProductCommand = new BuyProductCommand(user, product);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(buyProductCommand);
        invoker.invoke();
    }

    public void validateProductRequest(User user, Product product, ProductState newProductState) {
        CheckStrategy validateProductStrategy = new ValidateProductCheckStrategy(product, newProductState);

        if(!validateProductStrategy.validate())
            throw new IllegalArgumentException("Product non valid for buying");

        Command<Product> validateProductCommand = new ValidateProductCommand(user, product, newProductState);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(validateProductCommand);
        invoker.invoke();
    }

}
