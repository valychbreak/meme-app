package com.int20h.task.memeapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUserName() {
        return new ResponseEntity<>("UserName1", HttpStatus.OK);
    }
}
