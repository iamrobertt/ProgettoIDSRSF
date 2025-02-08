package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
