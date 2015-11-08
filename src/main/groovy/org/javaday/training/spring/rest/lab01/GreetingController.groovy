package org.javaday.training.spring.rest.lab01;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaroslav.yermilov
 */
@RestController
class GreetingController {

    @RequestMapping("/greeting/{name}")
    HttpEntity<Greeting> greet(@PathVariable("name") String name) {
        Greeting greeting = new Greeting(name: name)
        new ResponseEntity<>(greeting, HttpStatus.OK)
    }
}
