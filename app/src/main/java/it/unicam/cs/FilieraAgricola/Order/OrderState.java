package it.unicam.cs.FilieraAgricola.Order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderState {

    ORDER_RECEIVED("ORDER_RECEIVED"),
    ORDER_READY("ORDER_READY"),
    ORDER_SHIPPED("ORDER_SHIPPED"),
    ORDER_DELIVERED("ORDER_DELIVERED");


    private final String value;


    OrderState(String value) {
        this.value = value;
    }


    @JsonValue
    public String getValue() {
        return value;
    }


    @JsonCreator
    public static OrderState fromValue(String value) {
        for (OrderState state : OrderState.values())
            if (state.getValue().equals(value))
                return state;

        return null;
    }


    public boolean canTransitionTo(OrderState newState) {
        return switch (this) {
            case ORDER_RECEIVED -> newState == ORDER_READY || newState == ORDER_SHIPPED || newState == ORDER_DELIVERED;
            case ORDER_READY -> newState == ORDER_SHIPPED || newState == ORDER_DELIVERED;
            case ORDER_SHIPPED -> newState == ORDER_DELIVERED;
            case ORDER_DELIVERED -> false;
        };
    }

}
