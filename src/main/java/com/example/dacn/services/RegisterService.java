package com.example.dacn.services;

import com.example.dacn.dtos.RegisterDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.entities.User;
import com.example.dacn.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public void save(RegisterDTO registerDTO) {
        //TODO: xử lý validation

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());

        List<Category> categoryList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Category category = new Category();
            category.setId(i);
            categoryList.add(category);
        }
        for (int i = 7; i <= 11; i++) {
            Category category = new Category();
            category.setId(i);
            categoryList.add(category);
        }
        user.setCategoryList(categoryList);
        registerRepository.save(user);
    }
}
