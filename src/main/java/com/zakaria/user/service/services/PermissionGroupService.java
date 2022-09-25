package com.zakaria.user.service.services;

import com.zakaria.user.service.models.PermissionGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
public interface PermissionGroupService {
    List<PermissionGroup> getAll();
    PermissionGroup get(UUID id);
    PermissionGroup add(PermissionGroup permissionGroup);
    PermissionGroup edit(UUID id, PermissionGroup permissionGroup);
    boolean delete(UUID id);
}
