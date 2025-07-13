package it.unicam.cs.FilieraAgricola.Certificate;



import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "certificate_product")
@Entity
public class CertificateProduct extends Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private long certificateID;

    @Enumerated(EnumType.STRING)
    @Column(name = "certificate_type")
    private CertificateType certificateType;

    @Id
    @ManyToOne
    @JoinColumn(name = "certificate_product", referencedColumnName = "product_id")
    private Product certificateProduct;

    @Column(name = "certificate_filePath")
    private String certificateFilePath;


    public CertificateProduct() {}

    public CertificateProduct(long certificateID, CertificateType certificateType, Product certificateProduct, String certificateFilePath) {
        this.certificateID = certificateID;
        this.certificateType = certificateType;
        this.certificateProduct = certificateProduct;
        this.certificateFilePath = certificateFilePath;
    }

}
