package com.example.dacn.repositories;

import com.example.dacn.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c.* from categories c inner join users_categories uc on c.id = uc.category_id inner join users u on u.id = uc.user_id where u.username = ?1", nativeQuery = true)
    List<Category> findAllByUser(@Param("username") String username);
}
