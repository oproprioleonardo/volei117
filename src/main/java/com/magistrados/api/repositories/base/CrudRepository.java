package com.magistrados.api.repositories.base;

import java.util.Map;
import java.util.Set;

public interface CrudRepository<B, T> {

    void create(T object);

    T findById(B id);

    void update(T object);

    void deleteById(B id);

    void save(T object);


}
