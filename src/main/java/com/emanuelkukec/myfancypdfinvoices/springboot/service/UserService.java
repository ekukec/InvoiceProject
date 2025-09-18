package com.emanuelkukec.myfancypdfinvoices.springboot.service;

import com.emanuelkukec.myfancypdfinvoices.springboot.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {
    public User findById(String id){
        String randomName = UUID.randomUUID().toString();

        return new User(id, randomName);
    }
}
