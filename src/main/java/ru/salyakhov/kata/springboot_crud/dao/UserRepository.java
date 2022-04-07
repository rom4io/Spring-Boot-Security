package ru.salyakhov.kata.springboot_crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salyakhov.kata.springboot_crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
