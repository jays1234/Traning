package com.training.auth.controller;

import com.training.auth.dto.UserResponse;
import com.training.auth.entity.User;
import com.training.auth.exception.UserNotExist;
import com.training.auth.repo.UserRepository;
import com.training.auth.util.Status;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public UserResponse<Iterable<User>> findAll(){
        Iterable<User> userList = this.userRepository.findAll();
        return new UserResponse<>(Status.SUCCESS,userList);
    }

    @PostMapping
    public UserResponse<User> add(@RequestBody User user){
        this.userRepository.save(user);
        return new UserResponse<>(Status.SUCCESS,user);
    }

    @PutMapping("/{id}")
    public UserResponse<User> update(@PathVariable Long id,@RequestBody User user) throws UserNotExist {
        if(this.userRepository.existsById(id)){
            this.userRepository.save(user);
            return new UserResponse<>(Status.SUCCESS,user);
        }
        throw new UserNotExist("cannot find userId :"+id);
    }

    @DeleteMapping("/{id}")
    public UserResponse<String> delete(@PathVariable Long id) throws UserNotExist {
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
            return new UserResponse<String>(Status.SUCCESS,"");
        }
        throw new UserNotExist("cannot find userId :"+id);
    }

    @ExceptionHandler(UserNotExist.class)
    public UserResponse<String> userNotExist(){
        return new UserResponse<String>(Status.FAILED,"");
    }

}
