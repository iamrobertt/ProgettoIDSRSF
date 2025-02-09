package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.SimpleEvent;
import it.unicam.cs.FilieraAgricola.Event.TastingEvent;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ControllerUtility {

    @Autowired
    private ProductRepository productRepository;

    public Product convertToProduct(ProductDTO productDTO) {

        ProductState productState = ProductState.fromValue(productDTO.getProductState());
        ProductType productType = ProductType.fromValue(productDTO.getProductType());


        if (Objects.requireNonNull(productType).getValue().equals("BUNDLE")) {

            // Getting products from database, only the ID of the product is required
            List<Product> products = productDTO.getBundleProducts()
                    .stream()
                    .map(product -> this.productRepository.findById(product.getProductID()).orElse(null))
                    .toList();

            return new BundleProduct(
                    productDTO.getProductID(),
                    productDTO.getProductName(),
                    productDTO.getProductDescription(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productState,
                    productType,
                    products
            );
        }

        // Altrimenti è un SingleProduct
        return new SingleProduct(
                productDTO.getProductID(),
                productDTO.getProductName(),
                productDTO.getProductDescription(),
                productDTO.getProductPrice(),
                productDTO.getProductQuantity(),
                productState,
                productType
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
                    eventDTO.getEventName(),
                    eventDTO.getEventDescription(),
                    eventDTO.getEventMaxParticipants(),
                    eventDTO.getEventCurrentParticipants(),
                    participants,
                    products
            );

        }

        //simple event
        return new SimpleEvent(
                eventDTO.getEventID(),
                eventDTO.getEventName(),
                eventDTO.getEventDescription(),
                eventDTO.getEventMaxParticipants(),
                eventDTO.getEventCurrentParticipants()
        );
    }


    public User convertToUser(UserDTO userDTO){

        UserRole userRole = UserRole.fromValue(userDTO.getUserRole());
        UserState userState = UserState.fromValue(userDTO.getUserState());

        return new User(
                userDTO.getUserID(),
                userDTO.getUserName(),
                userDTO.getUserSurname(),
                userDTO.getUserEmail(),
                userDTO.getUserPassword(),
                userDTO.getCompanyVATNumber(),
                userRole,
                userState
        );
    }
}
