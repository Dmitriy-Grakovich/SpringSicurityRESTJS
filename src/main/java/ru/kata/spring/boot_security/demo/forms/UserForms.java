package ru.kata.spring.boot_security.demo.forms;

import lombok.Data;

@Data
public class UserForms {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String password;
}
