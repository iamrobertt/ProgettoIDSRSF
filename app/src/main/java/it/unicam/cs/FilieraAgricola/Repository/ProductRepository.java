package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p where p.productID = :productID and p.productUser.userID = :userID")
    Optional<Product> findByProductIDAndUser(long userID, long productID);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.productState = :newState where p.productID = :productID and p.productUser.userID = :userID")
    void updateProductState(long userID, long productID, ProductState newState);

    @Modifying
    @Transactional
    @Query("UPDATE WarehouseProduct wp SET wp.productQuantity = wp.productQuantity - :orderItemQuantity WHERE wp.product.productID = :productID")
    void subProductQuantity(long productID, int orderItemQuantity);
}
