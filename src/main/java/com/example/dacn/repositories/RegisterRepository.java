package com.example.dacn.repositories;

import com.example.dacn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<User, Integer> {
}
