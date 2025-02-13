package it.unicam.cs.FilieraAgricola.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserState {
    WAITING_FOR_VALIDATION("WAITING_FOR_VALIDATION"),
    VALIDATED("VALIDATED");

    private final String value;

    UserState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UserState fromValue(String value) {
        for (UserState state : UserState.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Valore non valido per UserState: " + value);
    }
}
