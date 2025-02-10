package it.unicam.cs.FilieraAgricola.Product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductValidationState {

    ACCEPTED("ACCEPTED"),

    DENIED("DENIED");

    private final String value;

    ProductValidationState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProductValidationState fromValue(String value) {
        for (ProductValidationState state : ProductValidationState.values())
            if (state.getValue().equals(value))
                return state;

        return null;
    }
}
