package com.training.auth.service.impl;

import com.training.auth.entity.User;
import com.training.auth.exception.UserNotExistException;
import com.training.auth.repo.UserRepository;
import com.training.auth.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User add(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user,Long id) throws UserNotExistException {
        if(this.userRepository.existsById(id)){
            return this.userRepository.save(user);
        }
        throw new UserNotExistException("cannot find userId :"+id);
    }

    @Override
    public void delete(Long id) throws UserNotExistException {
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
            return;
        }
        throw new UserNotExistException("cannot find userId :"+id);
    }
}
