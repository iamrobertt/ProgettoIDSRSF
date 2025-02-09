package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Product.BundleProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BundleProductRepository extends JpaRepository<BundleProduct, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bundle (bundleid, productid) VALUES (:bundleId, :productId)", nativeQuery = true)
    void insertBundleRelation(long bundleId, long productId);
}
