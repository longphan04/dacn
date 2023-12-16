package com.example.dacn.services;

import com.example.dacn.dtos.CategoryDTO;
import com.example.dacn.dtos.LoginDTO;
import com.example.dacn.dtos.UserDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.entities.User;
import com.example.dacn.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public UserDTO login(LoginDTO loginDTO) {
        User user = loginRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        return convertToUserDTO(user);
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        List<CategoryDTO> categoryList = new ArrayList<>();
        for (Category category : user.getCategoryList()) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getId());
            categoryDTO.setCategoryName(category.getName());
            categoryDTO.setCategoryType(category.getType());
            categoryList.add(categoryDTO);
        }
        userDTO.setCategoryList(categoryList);
        return userDTO;
    }

    public UserDTO getUserById(Integer id) {
        Optional<User> user = loginRepository.findById(id);
        return user.map(this::convertToUserDTO).orElse(null);
    }
}
