package com.zakaria.user.service.repositories;

import com.zakaria.user.service.models.PermissionGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PermissionGroupRepository extends CrudRepository<PermissionGroup, UUID> {
}
