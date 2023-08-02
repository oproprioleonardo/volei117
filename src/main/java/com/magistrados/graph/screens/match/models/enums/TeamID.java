package com.magistrados.graph.screens.match.models.enums;

import java.util.Arrays;

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

    public static TeamID valueOfByKey(String key) {
        return Arrays.stream(values()).filter(teamID -> key.equals(teamID.getId())).findFirst().orElse(null);
    }
}
