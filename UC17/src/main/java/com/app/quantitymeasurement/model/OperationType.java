package com.app.quantitymeasurement.model;

public enum OperationType {
    COMPARE("compare"),
    CONVERT("convert"),
    ADD("add"),
    SUBTRACT("subtract");

    private final String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
