package it.unicam.cs.FilieraAgricola.Certificate;

import it.unicam.cs.FilieraAgricola.Product.ProductUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateUtility {

    @Autowired
    private ProductUtility productUtility;


    public boolean checkCertificateForLoading(CertificateProduct certificateProduct) {

        return certificateProduct != null &&
                certificateProduct.getCertificateProduct() != null &&
                certificateProduct.getCertificateFilePath() != null &&
                certificateProduct.getCertificateType() != null;
    }
}
