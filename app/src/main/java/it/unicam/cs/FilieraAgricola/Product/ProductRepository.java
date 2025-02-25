package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.DTO.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productID = ?1")
    Optional<ProductDTO> findById(long id);
}
