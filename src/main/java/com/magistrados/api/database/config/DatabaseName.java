package com.magistrados.api.database.config;

import java.util.Arrays;

public enum DatabaseName {

    POSTGRES(1),
    MYSQL(2);

    private final int id;

    DatabaseName(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DatabaseName valueOf(int id) {
        return Arrays.stream(DatabaseName.values()).filter(databaseName -> databaseName.getId() == id).findFirst().orElseThrow(NullPointerException::new);
    }
}
