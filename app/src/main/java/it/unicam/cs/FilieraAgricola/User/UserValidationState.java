package it.unicam.cs.FilieraAgricola.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserValidationState {

    ACCEPTED("ACCEPTED"),

    DENIED("DENIED");

    private final String value;

    UserValidationState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UserValidationState fromValue(String value) {
        for (UserValidationState state : UserValidationState.values())
            if (state.getValue().equals(value))
                return state;

        return null;
    }

}



