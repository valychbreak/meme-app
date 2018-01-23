package com.int20h.task.memeapp.controller;

import com.int20h.task.memeapp.domain.User;
import com.int20h.task.memeapp.dto.UserDTO;
import com.int20h.task.memeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController extends ApiController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserName() {
        // TODO: return a user from principal
        User user = userRepository.findOneByUsername("admin");
        return new ResponseEntity<>(UserDTO.createUserDTO(user), HttpStatus.OK);
    }
}
