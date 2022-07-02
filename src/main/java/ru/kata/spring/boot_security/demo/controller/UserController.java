package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.forms.UserForms;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(Model model) {
        List<User> users = userService.allUser();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/new")
    public String newUser() {
        return "newUser";
    }

    @PostMapping("/admin")
    public String creatUser(UserForms userForms, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        userService.save(userForms);
        return "redirect:/admin";
    }

    @GetMapping("admin/user/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userUpdate";
    }


    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam("name") String name,
                         @RequestParam("lastName") String lastName, @RequestParam("age") Integer age) {
        userService.update(id, name, lastName, age);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/{id}")
    public String deleteUser(@PathVariable("id") Long Id) {
        userService.delete(Id);
        return "redirect:/admin";
    }
}
