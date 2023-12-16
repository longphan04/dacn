package com.example.dacn.repositories;

import com.example.dacn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}
