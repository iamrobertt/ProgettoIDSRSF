package it.unicam.cs.FilieraAgricola.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMINISTRATOR("ADMINISTRATOR"),
    SELLER("SELLER"),
    PRODUCER("PRODUCER"),
    DISTRIBUTOR("DISTRIBUTOR"),
    VALIDATOR("VALIDATOR"),
    MANUFACTURER("MANUFACTURER"),
    GENERIC_USER("GENERIC_USER"),
    PROMOTER("PROMOTER"),
    CUSTOMER("CUSTOMER");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UserRole fromValue(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("UserRole not valid: " + value);
    }
}
