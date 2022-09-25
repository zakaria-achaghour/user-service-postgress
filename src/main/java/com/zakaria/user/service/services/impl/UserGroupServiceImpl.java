package com.zakaria.user.service.services.impl;

import com.zakaria.user.service.models.PermissionGroup;
import com.zakaria.user.service.models.UserGroup;
import com.zakaria.user.service.repositories.PermissionGroupRepository;
import com.zakaria.user.service.repositories.UserGroupRepository;
import com.zakaria.user.service.requests.UserGroupRequest;
import com.zakaria.user.service.services.UserGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    UserGroupRepository userGroupRepository;


    @Override
    public List<UserGroup> getAll() {
        List<UserGroup> userGroups = new ArrayList<>();
        userGroupRepository.findAll().forEach(ug -> {
            if(ug.getDeletedAt() == null){
                userGroups.add(ug);
            }
        });
        return userGroups;
    }

    @Override
    public UserGroup get(UUID id) {
        UserGroup  userGroup= userGroupRepository.findById(id).orElse(null);

        if (userGroup.getDeletedAt() == null){
            return userGroup;
        }
        return  null;
    }

    @Override
    public UserGroup add(UserGroup userGroup) {

        return userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroup edit(UUID id, UserGroup userGroup) {
        UserGroup dao = userGroupRepository.findById(id).orElse(null);
        if(dao!=null){
            if(dao.getDeletedAt() == null){
                dao.setName(userGroup.getName());
                dao.setPermissionGroups(userGroup.getPermissionGroups());
                return userGroupRepository.save(dao);
            }
        }
        return null;
    }



    @Override
    public boolean delete(UUID id) {
        UserGroup userGroup =  userGroupRepository.findById(id).orElse(null);
        if(userGroup != null){
            userGroup.setDeletedAt(new Date());
            userGroupRepository.save(userGroup);
            return true;
        }
        return false;

    }


}
