package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.CrudRepository;
import com.magistrados.models.Time;

public interface TimeRepository extends CrudRepository<Long, Time> {

    Time findByName(String name);

}
