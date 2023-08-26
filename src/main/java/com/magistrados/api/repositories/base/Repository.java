package com.magistrados.api.repositories.base;

public interface Repository<B, T> {

    void create(T object);

    T findById(B id);

    void update(T object);

    void deleteById(B id);

    void save(T object);


}
