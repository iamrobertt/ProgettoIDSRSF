package it.unicam.cs.FilieraAgricola.Manager;

import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.Command.*;
import it.unicam.cs.FilieraAgricola.Exception.InsufficientUserAuthorizationException;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductLoaderFactory;
import it.unicam.cs.FilieraAgricola.Product.ProductValidationState;
import it.unicam.cs.FilieraAgricola.Repository.OrderRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    public void loadProductRequest(User user, Product product) {

        if(!this.loadProductCheckStrategy.validate(user, product))
            throw new IllegalArgumentException("Product non valid for loading");

        Command<Product> loadProductCommand = new LoadProductCommand(user, product, this.productLoaderFactory);

        if(!loadProductCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to perform a loading product request");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(loadProductCommand);
        invoker.invoke();

    }


    //todo qualcosa non va, prende anche id che non esistono
    public void sellProductRequest(User user, Product product) {

        if(!this.sellProductCheckStrategy.validate(user, product))
            throw new IllegalArgumentException("Product non valid for selling");

        Command<Product> sellProductCommand = new SellProductCommand(user, product, this.productRepository);

        if (!sellProductCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to sell a product");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(sellProductCommand);
        invoker.invoke();

    }


    public void buyProductRequest(User user, List<Pair<Product, Integer>> productList) {

        //for (Pair<Product, Integer> product : productList)
            //if(!this.buyProductCheckStrategy.validate(user, product.a))
                //throw new IllegalArgumentException("Product  with id: " + product.a.getProductID() + " non valid for buying");

        BuyProductCommand buyProductCommand = new BuyProductCommand(user, productList, this.orderRepository, this.productRepository);

        if (!buyProductCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to buy product");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(buyProductCommand);
        invoker.invoke();
    }


    public void validateProductRequest(User user, Product product, ProductValidationState productValidationState) {

        if(!this.validateProductCheckStrategy.validate(user, product, productValidationState))
            throw new IllegalArgumentException("Product non valid for validation");

        Command<Product> validateProductCommand = new ValidateProductCommand(user, product, productValidationState, this.productRepository);

        if (!validateProductCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to perform a validation of product");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(validateProductCommand);
        invoker.invoke();
    }

}
