package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Certificate.CertificateProduct;
import it.unicam.cs.FilieraAgricola.Certificate.CertificateUtility;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class LoadCertificateCheckStrategy implements CustomCheckStrategy<CertificateProduct, MultipartFile> {

    @Autowired
    private ProductUtility productUtility;

    @Autowired
    private CertificateUtility certificateUtility;


    @Override
    public boolean validate(User user, CertificateProduct certificate, MultipartFile file) {

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if(!certificateUtility.checkCertificateForLoading(certificate))
            throw new IllegalArgumentException("Certificate cannot be loaded");

        if(file == null)
            throw new IllegalArgumentException("Error retrieving file information.");

        if(!productUtility.checkProductInfo(certificate.getCertificateProduct()))
            throw new IllegalArgumentException("The product is not valid for a certificate insert.");

        if(!productUtility.checkExistProductWithUser(user, certificate.getCertificateProduct()))
            throw new IllegalArgumentException("The product is not valid for a certificate insert.");


        return true;
    }
}
