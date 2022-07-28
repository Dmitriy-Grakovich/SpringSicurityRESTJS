package ru.kata.spring.boot_security.demo.forms;

import lombok.Data;
import org.hibernate.Hibernate;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String password;
    private String role;
}
