package it.unicam.cs.FilieraAgricola.Event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.unicam.cs.FilieraAgricola.Product.ProductType;

public enum EventType {

    SIMPLE("SIMPLE"),

    TASTING("TASTING");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EventType fromValue(String value) {
        for (EventType type : EventType.values())
            if (type.getValue().equals(value))
                return type;
        return null;
    }
}
