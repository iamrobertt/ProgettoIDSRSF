package it.unicam.cs.FilieraAgricola.Product;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductType {

    SINGLE("SINGLE"),
    BUNDLE("BUNDLE");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProductType fromValue(String value) {
        for (ProductType type : ProductType.values())
            if (type.getValue().equals(value))
                return type;

        throw new IllegalArgumentException("Valore non valido per ProductType: " + value);
    }
}
