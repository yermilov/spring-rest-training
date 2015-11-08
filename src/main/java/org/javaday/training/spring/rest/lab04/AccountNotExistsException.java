package org.javaday.training.spring.rest.lab04;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yaroslav.yermilov
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotExistsException extends RuntimeException {

    public AccountNotExistsException(String username) {
        super("Account with username " + username + " was not found");
    }
}
