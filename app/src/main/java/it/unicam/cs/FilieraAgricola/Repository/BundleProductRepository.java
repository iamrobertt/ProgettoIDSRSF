package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Product.BundleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BundleProductRepository extends JpaRepository<BundleProduct, Long> {
}
