package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE p.productID = 2")
    Optional<Product> findById(long id);
}
