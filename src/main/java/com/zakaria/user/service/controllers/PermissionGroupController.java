package com.zakaria.user.service.controllers;

import com.zakaria.user.service.models.PermissionGroup;
import com.zakaria.user.service.requests.PermissionGroupRequest;
import com.zakaria.user.service.services.PermissionGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/permissions")
public class PermissionGroupController {
    @Autowired
    PermissionGroupService service;

    @GetMapping()
    public List<PermissionGroup> getAllPermissionGroups() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PermissionGroup> getRole(@PathVariable UUID id) {
        PermissionGroup permissionGroup = service.get(id);
        if (permissionGroup == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(permissionGroup, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<PermissionGroup> addPermissionGroup(@Valid @RequestBody PermissionGroupRequest request) {
        PermissionGroup permissionGroup = new PermissionGroup();
        String permissions = String.join(",", request.getPermissions());
        BeanUtils.copyProperties(request,permissionGroup);
        permissionGroup.setPermissions(permissions);

        PermissionGroup permissionGroup1 = service.add(permissionGroup);
        return  new ResponseEntity<>(permissionGroup1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PermissionGroup> editPermissionGroup(@Valid @RequestBody PermissionGroupRequest request,@PathVariable UUID id) {
        PermissionGroup permissionGroup = new PermissionGroup();
        String permissions = String.join(",", request.getPermissions());
        BeanUtils.copyProperties(request,permissionGroup);
        permissionGroup.setPermissions(permissions);
        PermissionGroup permissionGroup1 = service.edit(id,permissionGroup);
        if (permissionGroup1 == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(permissionGroup1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerimissionGroup(@PathVariable UUID id) {
        boolean check =  service.delete(id);
        if(check){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
