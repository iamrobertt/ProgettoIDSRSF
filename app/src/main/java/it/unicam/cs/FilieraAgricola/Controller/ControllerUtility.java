package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.*;
import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ControllerUtility {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public Product convertToProduct(ProductDTO productDTO) {

        ProductState productState = ProductState.fromValue(productDTO.getProductState());
        ProductType productType = ProductType.fromValue(productDTO.getProductType());

        if (Objects.requireNonNull(productType).getValue().equals("BUNDLE")) {

            List<Product> products = productDTO.getBundleProducts()
                    .stream()
                    .map(productWithQuantityDTO -> {
                        Product product = this.productRepository.findById(productWithQuantityDTO.getProductID())
                                .orElse(null);

                        if(product != null)
                            product.getWarehouseProduct().setProductQuantity(productWithQuantityDTO.getProductQuantity());

                        return product;
                    }).toList();

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

        if(productDTO.getBundleProducts() != null)
            throw new IllegalStateException("Bundle product defined as SINGLE");
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

        List<EventParticipant> participants = eventDTO.getParticipants()
                .stream()
                .map(this::convertToParticipant)
                .toList();

        EventType eventType = EventType.fromValue(eventDTO.getEventType());

        if(participants.isEmpty() || eventType == null)
            return null;

        //tasting event
        if(eventDTO.getEventType().equals("TASTING") &&
                eventDTO.getProductList() != null &&
                !eventDTO.getProductList().isEmpty()
        ){

            List<EventProduct> products = eventDTO.getProductList()
                    .stream()
                    .map(this::convertToProduct)
                    .toList();


            return new TastingEvent(
                    eventDTO.getEventID(),
                    eventDTO.getEventName(),
                    eventDTO.getEventDescription(),
                    eventDTO.getEventMaxParticipants(),
                    eventDTO.getEventCurrentParticipants(),
                    eventType,
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
                eventDTO.getEventCurrentParticipants(),
                eventType,
                participants
        );
    }

    public Pair<Product, Integer> convertToProduct(ProductWithQuantityDTO productWithQuantityDTO) {

        Optional<Product> product = this.productRepository.findById(productWithQuantityDTO.getProductID());

        if(product.isPresent())
            return new Pair<>(product.get(), productWithQuantityDTO.getProductQuantity());


        return null;
    }

    public EventProduct convertToProduct(EventProductDTO eventProductDTO) {

        Optional<Product> product = this.productRepository.findById(eventProductDTO.getProductID());
        EventProduct eventProduct = new EventProduct();

        if(product.isPresent()){
            eventProduct.setProduct(product.get());
            return eventProduct;
        }

        return null;
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


    public EventParticipant convertToParticipant(EventParticipantDTO eventParticipantDTO){

        Optional<User> participant = this.userRepository.findById(eventParticipantDTO.getParticipantID());
        EventParticipant eventParticipant = new EventParticipant();

        if(participant.isPresent()){
            eventParticipant.setParticipant(participant.get());
            return eventParticipant;
        }


        return null;
    }

}
