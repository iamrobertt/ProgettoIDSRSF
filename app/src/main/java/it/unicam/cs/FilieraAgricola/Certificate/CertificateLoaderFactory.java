package it.unicam.cs.FilieraAgricola.Certificate;

import java.util.HashMap;
import java.util.Map;


public class CertificateLoaderFactory {

    private final Map<Class<? extends Certificate>, CertificateLoader> certificateLoaders = new HashMap<>();

    public CertificateLoaderFactory() {
        certificateLoaders.put(CertificateProduct.class, new CertificateProductLoader());
    }

    public CertificateLoader getCertificateLoader(Class<? extends Certificate> certificateClass) {
        return certificateLoaders.get(certificateClass);
    }
}
