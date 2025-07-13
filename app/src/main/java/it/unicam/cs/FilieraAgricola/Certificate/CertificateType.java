package it.unicam.cs.FilieraAgricola.Certificate;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CertificateType {

    PRODUCT_INFO("PRODUCT_INFO"),
    PRODUCT_TRANSFORM_INFO("PRODUCT_TRANSFORM_INFO");

    private final String value;

    CertificateType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CertificateType fromValue(String value) {
        for (CertificateType type : CertificateType.values())
            if (type.getValue().equals(value))
                return type;

        return null;
    }
}
