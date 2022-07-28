package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.forms.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addNewUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseEntity<>("User с почтой " + userDTO.getEmail() + " добавлен", HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return new ResponseEntity<>("User с id " + userDTO.getId() + "обновлен", HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.delete(id);
        return new ResponseEntity<>("User с номером " + id + " удален из базы.", HttpStatus.OK);
    }
}
