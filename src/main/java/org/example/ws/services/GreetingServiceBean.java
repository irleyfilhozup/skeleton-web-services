package org.example.ws.services;

import java.util.Collection;

import org.example.ws.repository.GreetingRepository;
import org.example.ws.models.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class GreetingServiceBean implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Collection<Greeting> findAll() {
        Collection<Greeting> greetings = greetingRepository.findAll();
        return greetings;
    }

    @Override
    public Greeting findOne(Integer id) {
        Greeting greeting = greetingRepository.findOne(id);
        return greeting;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public Greeting create(Greeting greeting) {
        if(greeting.getId() <0) {
            return null;
        }

        Greeting saveGreeting = greetingRepository.save(greeting);

        //ILustrate to rollback
        if(saveGreeting.getId()==4L) {
            throw new RuntimeException("Roll me back!");
        }

        return saveGreeting;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public Greeting update(Greeting greeting) {
        Greeting greetingPersisted = findOne(greeting.getId());
        if(greetingPersisted == null) {
            //Nao existe no bd
            return null;
        }

        Greeting updateGreeting = greetingRepository.save(greeting);
        return updateGreeting;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public void delete(Integer id) {
        greetingRepository.delete(id);
    }
}
