package com.training.auth.service;

import com.training.auth.entity.User;
import com.training.auth.exception.UserNotExistException;

public interface UserService {

    Iterable<User> findAll();

    User add(User user);

    User update(User user,Long id) throws UserNotExistException;

    void delete(Long id) throws UserNotExistException;
}
