package com.zakaria.user.service.controllers;

import com.zakaria.user.service.models.PermissionGroup;
import com.zakaria.user.service.models.UserGroup;
import com.zakaria.user.service.requests.PermissionGroupRequest;
import com.zakaria.user.service.requests.UserGroupRequest;
import com.zakaria.user.service.services.PermissionGroupService;
import com.zakaria.user.service.services.UserGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-groups")
public class UserGroupController {

    @Autowired
    UserGroupService service;
    @Autowired
    PermissionGroupService permissionGroupService;

    @GetMapping()
    public List<UserGroup> getAllUserGroups() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGroup> getUserGroup(@PathVariable UUID id) {
        UserGroup userGroup = service.get(id);
        if (userGroup == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(userGroup, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserGroup> addUserGroup(@Valid @RequestBody UserGroupRequest request) {

        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(request,userGroup);
        ArrayList<PermissionGroup> permissionGroups = new ArrayList<>();
        for (int i = 0; i < request.getPermissionGroups().size(); i++) {
            UUID permissionGroupId = (request.getPermissionGroups()).get(i);
            PermissionGroup permissionGroup = permissionGroupService.get(permissionGroupId);
            permissionGroups.add(permissionGroup);
        }
        userGroup.setPermissionGroups(permissionGroups);

      UserGroup userGroup1 =   service.add(userGroup);
        return  new ResponseEntity<>(userGroup1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserGroup> editUserGroup(@Valid @RequestBody UserGroupRequest request,@PathVariable UUID id) {
        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(request,userGroup);
        ArrayList<PermissionGroup> permissionGroups = new ArrayList<>();
        for (int i = 0; i < request.getPermissionGroups().size(); i++) {
            UUID permissionGroupId = (request.getPermissionGroups()).get(i);
            PermissionGroup permissionGroup = permissionGroupService.get(permissionGroupId);
            permissionGroups.add(permissionGroup);
        }
        userGroup.setPermissionGroups(permissionGroups);

        UserGroup userGroup1 =   service.edit(id,userGroup);
        if(userGroup1 == null) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(userGroup1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserGroup(@PathVariable UUID id) {
        boolean check =  service.delete(id);
        if(check){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
