package com.zakaria.user.service.services;

import com.zakaria.user.service.models.UserGroup;

import java.util.List;
import java.util.UUID;

public interface UserGroupService {
    List<UserGroup> getAll();
    UserGroup get(UUID id);
    UserGroup add(UserGroup userGroup);
    UserGroup edit(UUID id, UserGroup userGroup);
    boolean delete(UUID id);
}
