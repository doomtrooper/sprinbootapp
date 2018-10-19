package com.morganstanley.anand.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogoutController {

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @PostMapping(value = "/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody void create(@RequestParam("token") String value) throws InvalidClientException {
        defaultTokenServices.revokeToken(value);
    }
}
