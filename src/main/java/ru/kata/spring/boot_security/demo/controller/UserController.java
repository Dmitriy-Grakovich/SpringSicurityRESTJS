package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.forms.UserDTO;
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
    public String index(Principal principal, Model model) {
        User userAdmin = userService.getUserByEmail(principal.getName());
        List<User> users = userService.allUser();
        model.addAttribute("userAdmin", userAdmin);
        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("userUser", user);
        return "user";
    }

    @GetMapping("/admin/new")
    public String newUser() {
        return "newUser";
    }

    @PostMapping("/admin")
    public String creatUser(UserDTO userDTO, BindingResult bindingResult, @RequestParam("role") String role) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        userService.save(userDTO, role);
        return "redirect:/admin";
    }

//    @GetMapping("admin/user/{id}")
//    public String  show(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "user";
//    }


    @PostMapping("/{id}")
    public String update(UserDTO userDTO, BindingResult bindingResult, @RequestParam("role") String role) {
        userService.update(userDTO, role);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/{id}")
    public String deleteUser(@RequestParam("userId") String Id) {

        userService.delete(Long.parseLong(Id));
        return "redirect:/admin";
    }
}
