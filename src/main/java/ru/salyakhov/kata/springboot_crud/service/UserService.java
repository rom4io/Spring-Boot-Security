package ru.salyakhov.kata.springboot_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.salyakhov.kata.springboot_crud.dao.UserRepository;
import ru.salyakhov.kata.springboot_crud.model.Role;
import ru.salyakhov.kata.springboot_crud.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    public User saveUser(User user) {
        user.setRoles(Collections.singleton(new Role(1L)));
        return userRepository.save(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public List<User> saveList(List<User> users) {
        return userRepository.saveAll(users);
    }
}
