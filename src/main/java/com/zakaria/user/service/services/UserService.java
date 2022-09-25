package com.zakaria.user.service.services;

import com.zakaria.user.service.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();
    User get(UUID id);
    User add(User user);
    User edit(UUID id, User user);
    boolean delete(UUID id);
}
