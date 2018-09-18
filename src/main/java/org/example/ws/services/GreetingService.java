package org.example.ws.services;

import java.util.Collection;

import org.example.ws.models.Greeting;

public interface GreetingService {

    Collection<Greeting> findAll();

    Greeting findOne(Integer id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Integer id);
}