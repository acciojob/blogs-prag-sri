package com.driver.services;

import com.driver.RequestDTO.UserRequestDTO;
import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    @Autowired
    UserRequestDTO userRequestDTO;

    public void createUser(UserRequestDTO userRequestDTO){
        User user= new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        userRepository3.save(user);
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId);
    }

    public void updateUser(User user){
        userRepository3.save(user);
    }

    public User findUserByUsername(String username){
        for(User user: userRepository3.findAll())
        {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
