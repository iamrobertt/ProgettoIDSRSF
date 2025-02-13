package it.unicam.cs.FilieraAgricola.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoleRequestValidationState {

    ACCEPTED("ACCEPTED"),

    DENIED("DENIED");

    private final String value;

    UserRoleRequestValidationState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UserRoleRequestValidationState fromValue(String value) {
        for (UserRoleRequestValidationState state : UserRoleRequestValidationState.values())
            if (state.getValue().equals(value))
                return state;

        return null;
    }

}



