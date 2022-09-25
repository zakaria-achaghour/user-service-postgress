package com.zakaria.user.service.services.impl;

import com.zakaria.user.service.models.User;
import com.zakaria.user.service.repositories.UserRepository;
import com.zakaria.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(u -> {
            if(u.getDeletedAt() == null){
                users.add(u);
            }
        });
        return users;
    }

    @Override
    public User get(UUID id) {
        User User= userRepository.findById(id).orElse(null);

        if (User.getDeletedAt() == null){
            return User;
        }
        return  null;
    }

    @Override
    public User add(User User) {

        return userRepository.save(User);
    }

    @Override
    public User edit(UUID id, User User) {
        User dao = userRepository.findById(id).orElse(null);
        if(dao!=null){
            if(dao.getDeletedAt() == null){
                dao.setName(User.getName());
                dao.setGroup(User.getGroup());
                return userRepository.save(dao);
            }
        }
        return null;
    }



    @Override
    public boolean delete(UUID id) {
        User User =  userRepository.findById(id).orElse(null);
        if(User != null){
            User.setDeletedAt(new Date());
            userRepository.save(User);
            return true;
        }
        return false;

    }

}
