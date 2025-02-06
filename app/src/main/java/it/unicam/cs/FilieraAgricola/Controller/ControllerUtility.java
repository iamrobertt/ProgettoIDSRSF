package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.TastingEvent;
import it.unicam.cs.FilieraAgricola.Product.BundleProduct;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.SingleProduct;
import it.unicam.cs.FilieraAgricola.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerUtility {

    public Product convertToProduct(ProductDTO productDTO) {

        if (productDTO.getBundleProducts() != null && !productDTO.getBundleProducts().isEmpty()) {
            List<Product> products = productDTO.getBundleProducts()
                    .stream()
                    .map(this::convertToProduct)
                    .toList();

            return new BundleProduct(
                    0,
                    productDTO.getProductName(),
                    productDTO.getProductDescription(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    ProductState.PRODUCT_INSERTED,
                    products
            );
        }

        // Altrimenti è un SingleProduct
        return new SingleProduct(
                0,
                productDTO.getProductName(),
                productDTO.getProductDescription(),
                productDTO.getProductPrice(),
                productDTO.getProductQuantity(),
                ProductState.PRODUCT_INSERTED
        );
    }

    public Event convertToEvent(EventDTO eventDTO){

        //TODO da fare convertToUser
        List<User> participants = eventDTO.getParticipants()
                .stream()
                .map(this::convertToUser)
                .toList();


        //tasting event
        if(eventDTO.getProductList() != null && !eventDTO.getProductList().isEmpty()){

            List<Product> products = eventDTO.getProductList()
                    .stream()
                    .map(this::convertToProduct)
                    .toList();


            return new TastingEvent(
                    eventDTO.getEventID(),
                    eventDTO.getEventMaxParticipants(),
                    eventDTO.getCurrentParticipants(),
                    participants,
                    products
            );

        }

        //simple event
        return new Event(
                eventDTO.getEventID(),
                eventDTO.getEventMaxParticipants(),
                eventDTO.getCurrentParticipants(),
                participants
        );
    }


    public User convertToUser(UserDTO userDTO){

        return new User(
                userDTO.getUserID(),
                userDTO.getUserName(),
                userDTO.getUserSurname(),
                userDTO.getUserEmail(),
                userDTO.getUserPassword(),
                userDTO.getCompanyVATNumber(),
                userDTO.getUserRole(),
                userDTO.getUserState()
        );
    }
}
