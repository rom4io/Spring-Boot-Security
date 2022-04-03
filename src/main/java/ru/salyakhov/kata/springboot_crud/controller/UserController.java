package ru.salyakhov.kata.springboot_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.salyakhov.kata.springboot_crud.model.User;
import ru.salyakhov.kata.springboot_crud.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> firstListUsers = new ArrayList<>();
    {
        firstListUsers.add(new User("Alexey", "Alexeev", 21));
        firstListUsers.add(new User("Petr", "Petrov", 27));
        firstListUsers.add(new User("Milana", "Milanova", 30));
        firstListUsers.add(new User("Sidr", "Sidorov", 35));
        firstListUsers.add(new User("Ivan", "Ivanov", 41));
    }
    boolean usersExists = false;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String findAll(Model model){
        if(!usersExists){
            userService.saveList(firstListUsers);
            usersExists = true;
        }
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/users/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/create")
    public String createUserForm(User user){
        return "users-create";
    }

    @PostMapping("/users/create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/users/update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

}
