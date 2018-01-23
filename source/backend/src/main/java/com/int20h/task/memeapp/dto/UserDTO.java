package com.int20h.task.memeapp.dto;

import com.int20h.task.memeapp.domain.User;

public class UserDTO {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    private UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = "Name";
        this.lastname = "Surname";
        this.email = "admin@example.com";
    }

    public static UserDTO createUserDTO(User user) {
        return new UserDTO(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
