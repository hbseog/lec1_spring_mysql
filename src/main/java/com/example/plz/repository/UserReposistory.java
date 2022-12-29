package com.example.plz.repository;

import com.example.plz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposistory  extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
