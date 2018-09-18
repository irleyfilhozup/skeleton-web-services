package org.example.ws.api;


import java.util.Collection;
import org.example.ws.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.example.ws.models.Greeting;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(
            value = "api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {

        Collection<Greeting> greetings = greetingService.findAll();
        return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            value = "api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreetings(@PathVariable("id") Integer id) {

        Greeting greeting = greetingService.findOne(id);
        if(greeting == null) {

            return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }

    @RequestMapping(
            value = "api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreetings(@RequestBody Greeting greeting) {

        Greeting saveGreeting = greetingService.create(greeting);
        return new ResponseEntity<Greeting>(saveGreeting, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "api/greetings/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreetings(@RequestBody Greeting greeting) {

        Greeting updateGreeting = greetingService.update(greeting);
        if(updateGreeting == null) {
            return  new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Greeting>(updateGreeting, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "api/greetings/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> deleteGreetings(@PathVariable("id") Integer id, @RequestBody Greeting greeting) {

       greetingService.delete(id);
        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
    }
}