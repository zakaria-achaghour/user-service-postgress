package com.zakaria.user.service.services.impl;

import com.zakaria.user.service.models.PermissionGroup;
import com.zakaria.user.service.repositories.PermissionGroupRepository;
import com.zakaria.user.service.services.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {
    @Autowired
    PermissionGroupRepository repository;

    @Override
    public List<PermissionGroup> getAll() {
        List<PermissionGroup> permissionGroups = new ArrayList<>();
        repository.findAll().forEach(pg -> {
            if(pg.getDeletedAt() == null){
                permissionGroups.add(pg);
            }
        });
        return permissionGroups;
    }

    @Override
    public PermissionGroup get(UUID id) {
        PermissionGroup permissionGroup = repository.findById(id).orElse(null);

        if (permissionGroup.getDeletedAt() == null){
            return permissionGroup;
        }
        return  null;
    }

    @Override
    public PermissionGroup add(PermissionGroup permissionGroup) {
       return repository.save(permissionGroup);
    }

    @Override
    public PermissionGroup edit(UUID id, PermissionGroup permissionGroup) {
        PermissionGroup dao = repository.findById(id).orElse(null);
        if(dao!=null){
            if(dao.getDeletedAt() == null){
                dao.setName(permissionGroup.getName());
                dao.setPermissions(permissionGroup.getPermissions());
                return repository.save(dao);
            }
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        PermissionGroup permissionGroup =  repository.findById(id).orElse(null);
        if(permissionGroup != null){
            permissionGroup.setDeletedAt(new Date());
            repository.save(permissionGroup);
            return true;
        }
        return false;
    }
}
