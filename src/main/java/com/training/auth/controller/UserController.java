package com.training.auth.controller;

import com.training.auth.dto.UserResponse;
import com.training.auth.entity.User;
import com.training.auth.exception.UserNotExistException;
import com.training.auth.service.UserService;
import com.training.auth.util.Status;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponse<Iterable<User>> findAll(){
        Iterable<User> userList = this.userService.findAll();
        return new UserResponse<>(Status.SUCCESS,userList);
    }

    @PostMapping
    public UserResponse<User> add(@RequestBody User user){
        this.userService.add(user);
        return new UserResponse<>(Status.SUCCESS,user);
    }

    @PutMapping("/{id}")
    public UserResponse<User> update(@PathVariable Long id,@RequestBody User user) throws UserNotExistException {
        user = this.userService.update(user,id);
        return new UserResponse<>(Status.SUCCESS,user);
    }

    @DeleteMapping("/{id}")
    public UserResponse<String> delete(@PathVariable Long id) throws UserNotExistException {
        this.userService.delete(id);
        return new UserResponse<>(Status.SUCCESS,"");
    }

    @ExceptionHandler(Exception.class)
    public UserResponse<String> userNotExist(Exception ex){
        return new UserResponse<>(Status.FAILED,ex.getMessage());
    }

}
