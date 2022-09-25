package com.zakaria.user.service.repositories;

import com.zakaria.user.service.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
