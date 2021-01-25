package com.xboot.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xboot.repository.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    Page<User> findList(Pageable pageable);
    User findById(long id);
    User findByUserName(String userName);
    void deleteById(Long id);
    List<User> findByNickname(String nickname);

}