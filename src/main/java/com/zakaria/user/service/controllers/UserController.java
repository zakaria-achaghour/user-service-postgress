package com.zakaria.user.service.controllers;

import com.zakaria.user.service.models.User;
import com.zakaria.user.service.requests.UserRequest;
import com.zakaria.user.service.services.UserGroupService;
import com.zakaria.user.service.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    UserGroupService userGroupService;

    @GetMapping()
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        User User = service.get(id);
        if (User == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(User, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request,user);
        user.setGroup(userGroupService.get(request.getUserGroupId()));
         User user1 =   service.add(user);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@Valid @RequestBody UserRequest request,@PathVariable UUID id) {
        User user = new User();
        BeanUtils.copyProperties(request,user);
        user.setGroup(userGroupService.get(request.getUserGroupId()));
        User user1 =   service.edit(id,user);
        if(user1 == null) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        boolean check =  service.delete(id);
        if(check){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
