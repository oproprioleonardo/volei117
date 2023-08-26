package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.Repository;
import com.magistrados.models.Time;

public interface TimeRepository extends Repository<Long, Time> {

    Time findByName(String name);

}
