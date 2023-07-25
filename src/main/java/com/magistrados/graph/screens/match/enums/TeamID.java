package com.magistrados.graph.screens.match.enums;

public enum TeamID {

    TIME_A("A"),
    TIME_B("B");

    private final String id;

    TeamID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
