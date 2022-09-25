package com.zakaria.user.service.repositories;

import com.zakaria.user.service.models.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserGroupRepository extends CrudRepository<UserGroup, UUID> {
}

