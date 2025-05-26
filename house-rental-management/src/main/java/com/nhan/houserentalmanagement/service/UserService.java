package com.nhan.houserentalmanagement.service;

import com.nhan.houserentalmanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    void deleteById(Long id);
}
