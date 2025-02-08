package it.unicam.cs.FilieraAgricola.Product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductState {
    PRODUCT_INSERTED("PRODUCT_INSERTED"),
    PRODUCT_TO_VALIDATE("PRODUCT_TO_VALIDATE"),
    PRODUCT_VALIDATED("PRODUCT_VALIDATED"),
    PRODUCT_NOT_VALIDATED("PRODUCT_NOT_VALIDATED");

    private final String value;

    ProductState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProductState fromValue(String value) {
        for (ProductState state : ProductState.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Valore non valido per ProductState: " + value);
    }
}
