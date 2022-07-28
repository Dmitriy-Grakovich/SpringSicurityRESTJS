package ru.kata.spring.boot_security.demo.service;




import ru.kata.spring.boot_security.demo.forms.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> allUser();

    User getUserById(long id);

    void update(UserDTO userDTO);

    void delete(Long id);

    User getUserByName(String name);

    void save(UserDTO userDTO);

    User getUserByEmail(String name);
}
