package com.xboot.repository.db2;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xboot.repository.model.User;


public interface UserTest2Repository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}